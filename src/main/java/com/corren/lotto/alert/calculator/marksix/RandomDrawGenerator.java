package com.corren.lotto.alert.calculator.marksix;

import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ZhangGR
 * @create on 2019-08-26
 * @description
 */
public class RandomDrawGenerator {

    // the candidate numbers for mark six
    private static final int MARK_SIX_NUMBER_CANDIDATES[] = {
            1, 2, 3, 4, 5, 6, 7, 8, 9,
        10,11,12,13,14,15,16,17,18,19,
        20,21,22,23,24,25,26,27,28,29,
        30,31,32,33,34,35,36,37,38,39,
        40,41,42,43,44,45,46,47,48,49
    };

    private static final int OFFSET_START_INDEX = 0;
    private static final int OFFSET_LIMIT_INDEX = 49;
    private static final Random random = new Random();

    /**
     * bet draw simulator
     * @param candidates the candidate number array
     * @param offsetStartIndex start index of the array
     * @param offsetLimitIndex limit index of the array
     * @param numberCount the count of numbers drawn in one bet
     * @return
     */
    public static final Integer[] randomDraw(int[] candidates, int offsetStartIndex, int offsetLimitIndex, int numberCount) {

        List<Integer> indexList = new ArrayList<>(numberCount);

        // get a list made up of seven non-repeated numbers
        do {
            int index = random.nextInt(offsetLimitIndex - offsetStartIndex);
            if (indexList.contains(index)) {
                continue;
            }
            indexList.add(index);
        } while (indexList.size() < numberCount);

        Integer[] result = new Integer[numberCount];

        for (int i = 0; i <numberCount; i++) {
            result[i] = candidates[indexList.get(i)];
        }

        return result;
    }


    public static List<Integer[]> simulateDraws(int count) {

        List<Integer[]> results = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            results.add(randomDraw(MARK_SIX_NUMBER_CANDIDATES, OFFSET_START_INDEX, OFFSET_LIMIT_INDEX, 7));
        }

        return results;
    }



    public static void main(String[] args) throws InterruptedException {

        List<BetOutcome> resultList = new ArrayList<>();
        final List<BetOutcome> synchronizedList = Collections.synchronizedList(resultList);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 365; i++) {

//             往线程池提交任务
            executorService.submit(() -> {

                // simulate ten million draws 模拟一百万组开奖
                List<Integer[]> results = simulateDraws(179);

                // count and collect the extremes 收集极值
                LottoExtremeCollector collector = LottoExtremeCollector.doCollect(results);

                List<LottoNumberExtremeCollector> collectors = collector.getCollectors();
                BetStrategy strategy = BetStrategy.normalStrategy();

                for (int j = 0; j <7; j++) {

                    final LottoNumberExtremeCollector numberExtremeCollector = collectors.get(j);
                    List<BetOutcome> betOutcomes = strategy.doBet(numberExtremeCollector);

                    synchronizedList.addAll(betOutcomes);
                }

                System.out.println(String.format("子线程%s任务执行完毕", Thread.currentThread().getName()));
            });

            System.out.println(String.format("主线程第%s个任务提交完毕", i));
        }

        executorService.shutdown();

        while(true) {
            if (executorService.isTerminated()) {

                if (CollectionUtils.isEmpty(synchronizedList)) {
                    System.out.println("没有下注机会");
                    Thread.sleep(500);
                    break;
                }

                final BigDecimal profits = synchronizedList.stream().map(s -> s.getProfit()).reduce((a, b) -> a.add(b)).get();
                System.out.println(JSON.toJSON(synchronizedList));
                System.out.println(String.format("10年出现极值的总盈利: %s", profits));
                Thread.sleep(500);
                break;
            }
        }
    }
}
