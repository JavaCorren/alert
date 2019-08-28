package com.corren.lotto.alert.calculator.marksix;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public static void main(String[] args) {

        // simulate ten million draws 模拟一百万组开奖
        List<Integer[]> results = simulateDraws(1000000);

        // count and collect the extremes 收集极值
        LottoExtremeCollector collector = LottoExtremeCollector.doCollect(results);

        // output the outcome 输出结果
        System.out.println(JSON.toJSON(collector));

        LottoNumberExtremeCollector lottoNumberExtremeCollector = collector.getCollectors().get(6);

        BetStrategy strategy = BetStrategy.normalStrategy();
        List<BetOutcome> betOutcomes = strategy.doBet(lottoNumberExtremeCollector);

        System.out.println(JSON.toJSON(betOutcomes));
    }

}
