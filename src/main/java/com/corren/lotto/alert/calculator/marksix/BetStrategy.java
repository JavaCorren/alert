package com.corren.lotto.alert.calculator.marksix;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ZhangGR
 * @create on 2019-08-27
 * @description 下注策略类
 */
@Data
public class BetStrategy {

    // 固定收益率
    private Double fixedReturnRate;

    // 止损金额
    private BigDecimal stopLoss;

    // 起始金额
    private BigDecimal startAmount;

    // 起追阈值
    private Long valve;

    // 赔率
    private Double odds;

    // 返点率
    private Double rebateRate;

    // 严格模式: true 如本期下注成功成本超止损点则放弃，false 则继续下注至本期结束
    private Boolean strict;

    /**
     * 初始化一个常规策略
     * @return
     */
    public static BetStrategy normalStrategy() {

        BetStrategy strategy = new BetStrategy();

        strategy.setFixedReturnRate(0.24);
        strategy.setStartAmount(BigDecimal.valueOf(100));
        strategy.setStopLoss(BigDecimal.valueOf(10000));
        strategy.setOdds(1.96);
        strategy.setValve(22L);
        strategy.setRebateRate(0.01);
        strategy.setStrict(true);

        return strategy;
    }

    public List<BetOutcome> doBet(LottoNumberExtremeCollector lottoNumberExtremeCollector) {

        Map<Long, Long> frequencyMap = lottoNumberExtremeCollector.getFrequencyMap();

        if (CollectionUtils.isEmpty(frequencyMap)) return null;

        else {
            return frequencyMap.entrySet().stream()
                    .map(s -> {
                        final BetOutcome apply = frequencyBetAnalyzer.apply(s);
                        if (null != apply) {
                            apply.setExtremeCollector(lottoNumberExtremeCollector);
                            return apply;
                        } else return apply;
                    })
                    .filter(s -> null != s)
                    .collect(Collectors.toList());
        }
    }

    /**
     * 下注算子
     */
    private Function<Map.Entry<Long,Long>, BetOutcome> frequencyBetAnalyzer = entry -> {

        // 当前长龙值
        Long frequency = entry.getKey();

        // 若未达到阈值，直接归零，等于没有下注。
        if (frequency <= valve) return null;

        BetOutcome outcome = new BetOutcome();
        Long times = frequency - valve;
        // 成本
        BigDecimal cost;
        // 盈利
        BigDecimal profit;
        // 派彩
        BigDecimal bonus;
        // 返水
        BigDecimal rebate = BigDecimal.ZERO;
        // 坚持
        boolean over = false;

        // 根据策略统计每期下注金额
        if (times == 1) {//一买就中的情形
            cost = startAmount;
            bonus = startAmount.multiply(BigDecimal.valueOf(odds));
            profit = bonus.subtract(cost);
        } else {//非一买即中的情形

            //从第二期开始就要考虑固定盈利率
            cost = startAmount;

            BigDecimal bet = BigDecimal.ZERO;

            for (int i = 1; i < times; i++) {

                // 从第二期开始需要计算单期下注额度, 计算公式 s = c*(1 + r)/(m - 1 - r), c代表截止上期的成本, r代表固定的盈利率， m代表赔率
                bet = cost.multiply(
                                BigDecimal.ONE.add(BigDecimal.valueOf(fixedReturnRate))
                        ).
                        divide(
                                BigDecimal.valueOf(odds).subtract(BigDecimal.ONE).subtract(BigDecimal.valueOf(fixedReturnRate)), RoundingMode.HALF_UP
                        ).setScale(0, BigDecimal.ROUND_HALF_UP);

                // 判断本期加注后是否超止损点
                if (cost.add(bet).compareTo(stopLoss) > 0) {

                    //超止损点后看是否开启拼死模式
                    if (strict) {
                        // 保守情形则放弃
                        over = true;
                        times = Long.valueOf(i);
                        break;
                    } else {

                        // 冒死情况就继续
                        cost = cost.add(bet).setScale(0, BigDecimal.ROUND_HALF_UP);

                        if (i < times - 1) {// 此时如果未到最后一期那么也停止
                            over = true;
                            times = Long.valueOf(i);
                            break;
                        }
                    }
                } else {
                    cost = cost.add(bet).setScale(0, BigDecimal.ROUND_HALF_UP);
                }
            }

            if (over) {
                bet = BigDecimal.ZERO;
            }

            final Long repeats = entry.getValue();

            cost = cost.multiply(BigDecimal.valueOf(repeats));
            bonus = bet.multiply(BigDecimal.valueOf(odds)).multiply(BigDecimal.valueOf(repeats)).setScale(2, BigDecimal.ROUND_HALF_UP);
            rebate = cost.multiply(BigDecimal.valueOf(rebateRate)).setScale(0, BigDecimal.ROUND_HALF_UP);
            profit = bonus.subtract(cost);
            times = times * repeats;
        }


        outcome.setCost(cost);
        outcome.setProfit(profit);
        outcome.setRebate(rebate);
        outcome.setBonus(bonus);
        outcome.setBetTimes(times);

        return outcome;
    };
}