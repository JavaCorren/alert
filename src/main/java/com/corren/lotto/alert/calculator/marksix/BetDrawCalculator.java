package com.corren.lotto.alert.calculator.marksix;

import com.corren.lotto.alert.enumeration.BetType;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @author ZhangGR
 * @create on 2019-08-26
 * @description
 */
@Data
public class BetDrawCalculator {

    /**
     * 计算投注结果（悲观估算）
     * @param lottoNumberExtremeCollector
     * @param odds
     * @param stopLoss
     * @param minBetAmount
     * @param betType
     * @param valve
     * @return
     */
    public static BetDrawOutcome doBet(
            LottoNumberExtremeCollector lottoNumberExtremeCollector,
            Double odds,
            Long stopLoss,
            Long minBetAmount,
            BetType betType,
            Integer valve
    ) {

        if (null == lottoNumberExtremeCollector) return null;

        BetDrawOutcome outcome = new BetDrawOutcome();
        outcome.setBetType(betType);
        outcome.setOdds(odds);
        outcome.setStopLoss(stopLoss);
        outcome.setValve(valve);
        outcome.setMinBetAmount(minBetAmount);

        if (null == betType) {
            outcome.setRemark("未指定下注方式");
        }

        if (odds <= 1) {
            outcome.setRemark("赔率不能不大于1");
        }

        if (null == minBetAmount || minBetAmount.intValue() < 2) {
            outcome.setRemark("最小下注金额不正确");
        }

        if (null == stopLoss || stopLoss.intValue() <= minBetAmount.intValue()) {
            outcome.setRemark("止损点设置不正确");
        }

        if (null == valve || valve < 1) {
            outcome.setRemark("阈值设置错误");
        }

        final Map<Long, Long> frequencyMap = lottoNumberExtremeCollector.getFrequencyMap();
        if (CollectionUtils.isEmpty(frequencyMap)) {
            outcome.setRemark("没有频谱表，无法计算");
        }

        // 遍历频谱表
        final Long profit = frequencyMap.entrySet().stream().map(s -> {
            final Long extremeCount = s.getKey();
            final Long times = s.getValue();

            // 频谱键大于阈值代表有下注
            if (extremeCount > valve) {

                Long y = extremeCount - valve;
                Long x = 2L;

                Double pow = Math.pow(x.doubleValue(), y.doubleValue());
                Long lastBet = pow.longValue();

                Long cost = sum(2L, lastBet, 2L);

                if (cost > stopLoss) {
                    return stopLoss * (-1) * times; // 这里大致按照止损点计算，实际可能小于止损点就停止下注
                } else {
                    // 计算盈利
                    Double bonus = lastBet * odds;
                    Long bonusLong = bonus.longValue();
                    return (bonusLong - cost) * times;
                }

            } else { // 否则没有下注
                return 0L;
            }
        }).reduce((a, b) -> (a + b)).get();

        if (profit > 0) {
            outcome.setRemark("有利可图");
        } else {
            outcome.setRemark("亏");
        }

        outcome.setProfits(profit);

        return outcome;
    }

    /**
     * 等比数列求和计算总下注额度
     * @param start
     * @param end
     * @param ratio
     * @return
     */
    private static Long sum(Long start, Long end, Long ratio) {
       return (start - end * ratio) / (1 - ratio);
    }

}