package com.corren.lotto.alert.util;

import com.corren.lotto.alert.enumeration.Lottery;
import com.corren.lotto.alert.enumeration.LottoNature;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangGR
 * @create on 2019-09-03
 * @description 大小长龙统计工具类
 */
public class LargeSmallMeasureUtil {

    private static final String LARGE = "large";
    private static final String SMALL = "small";

    /**
     * 大小长龙频度更新
     * @param target
     * @param lastTarget
     * @param frequencyExtremeMap
     * @param realTimeExtremeMap
     * @param nature
     */
    public static void measureExtremeFrequency(int target, Integer lastTarget, Map<String, Map<Integer, Integer>> frequencyExtremeMap, Map<String, Integer> realTimeExtremeMap, LottoNature nature) {

        Map<Integer, Integer> largeExtremeMap = frequencyExtremeMap.get(LARGE);
        Map<Integer, Integer> smallExtremeMap = frequencyExtremeMap.get(SMALL);

        if (null == largeExtremeMap) largeExtremeMap = new HashMap<>();
        if (null == smallExtremeMap) smallExtremeMap = new HashMap<>();

        int[] candidateNumberArray = nature.getCandidateNumberArray();
        int reference = candidateNumberArray[candidateNumberArray.length / 2];

        // 说明大小长龙切换: 大 -> 小
        if (lastTarget >= reference && target < reference) {
            Integer smallExtreme = realTimeExtremeMap.get(SMALL);

            Integer smallExtremeFrequencyCount = largeExtremeMap.getOrDefault(smallExtreme, 0);
            smallExtremeMap.put(smallExtreme, ++smallExtremeFrequencyCount);

            frequencyExtremeMap.put(SMALL, smallExtremeMap);

            // 说明大小长龙切换: 小 -> 大
        } else if (lastTarget < reference && target >= reference) {
            Integer largeExtreme = realTimeExtremeMap.get(LARGE);

            Integer largeExtremeFrequencyCount = smallExtremeMap.getOrDefault(largeExtreme, 0);
            largeExtremeMap.put(largeExtreme, ++largeExtremeFrequencyCount);

            frequencyExtremeMap.put(LARGE, largeExtremeMap);
        }
    }

    public static void measureRealTimeCount(int target, Map<String, Integer> realTimeExtremeMap, LottoNature nature) {

        int[] candidateNumberArray = nature.getCandidateNumberArray();
        int reference = candidateNumberArray[candidateNumberArray.length / 2];

        // 大
        if (target >= reference) {

            realTimeExtremeMap.put(LARGE, 0);
            Integer smallCount = realTimeExtremeMap.getOrDefault(SMALL, 0);
            realTimeExtremeMap.put(SMALL, ++smallCount);

            // 小
        } else {

            realTimeExtremeMap.put(SMALL, 0);
            Integer largeCount = realTimeExtremeMap.getOrDefault(LARGE, 0);
            realTimeExtremeMap.put(LARGE, ++largeCount);
        }
    }
}
