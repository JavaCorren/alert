package com.corren.lotto.alert.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangGR
 * @create on 2019-09-03
 * @description 单双长龙统计工具类
 */
public class OddEvenMeasureUtil {

    private static final String ODD = "odd";
    private static final String EVEN = "even";

    /**
     * 更新单、双频度记录
     * @param target 当前开出值
     * @param lastTarget 上期开出值
     * @param frequencyExtremeMap 极值数据频度表
     * @param realTimeExtremeMap  实时极值数据表
     */
    public static void measureExtremeFrequency(int target, Integer lastTarget, Map<String, Map<Integer, Integer>> frequencyExtremeMap, Map<String, Integer> realTimeExtremeMap) {

        Map<Integer, Integer> oddExtremeMap = frequencyExtremeMap.get(ODD);
        Map<Integer, Integer> evenExtremeMap = frequencyExtremeMap.get(EVEN);

        if (null == oddExtremeMap) {
            oddExtremeMap = new HashMap<>();
        }

        if (null == evenExtremeMap) {
            evenExtremeMap = new HashMap<>();
        }

        // 说明单双长龙切换: 单 -> 双
        if (lastTarget % 2 == 1 && target % 2 == 0) {
            Integer evenExtreme = realTimeExtremeMap.get(EVEN);

            Integer evenExtremeFrequencyCount = evenExtremeMap.getOrDefault(evenExtreme, 0);
            evenExtremeMap.put(evenExtreme, ++evenExtremeFrequencyCount);

            frequencyExtremeMap.put(EVEN, evenExtremeMap);

            // 说明单双长龙切换: 双 -> 单
        } else if (lastTarget % 2 == 0 && target % 2 == 1) {
            Integer oddExtreme = realTimeExtremeMap.get(ODD);

            Integer oddExtremeFrequencyCount = oddExtremeMap.getOrDefault(oddExtreme, 0);
            oddExtremeMap.put(oddExtreme, ++oddExtremeFrequencyCount);

            frequencyExtremeMap.put(ODD, oddExtremeMap);
        }
    }

    /**
     * 单、双遗漏
     * @param target
     * @param realTimeExtremeMap
     */
    public static void measureRealTimeCount(int target, Map<String, Integer> realTimeExtremeMap) {

        // 单
        if (target % 2 == 1) {

            realTimeExtremeMap.put(ODD, 0);
            Integer oddCount = realTimeExtremeMap.getOrDefault(EVEN, 0);
            realTimeExtremeMap.put(EVEN, ++oddCount);

            // 双
        } else {

            realTimeExtremeMap.put(EVEN, 0);
            Integer evenCount = realTimeExtremeMap.getOrDefault(ODD, 0);
            realTimeExtremeMap.put(ODD, ++evenCount);
        }
    }

}
