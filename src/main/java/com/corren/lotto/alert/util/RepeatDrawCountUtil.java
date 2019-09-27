package com.corren.lotto.alert.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangGR
 * created on 2019/9/24
 * @description
 **/
public class RepeatDrawCountUtil {

    // 连号遗漏值
    private static final String REPEAT_LONG = "repeatedNumberAbsenceSpan";

    /**
     * 更新极值频谱表
     * @param target
     * @param lastTarget
     * @param frequencyExtremeMap
     * @param realTimeExtremeMap
     */
    public static void measureExtremeFrequency(int target, Integer lastTarget, Map<String, Map<Integer, Integer>> frequencyExtremeMap, Map<String, Integer> realTimeExtremeMap) {

        Map<Integer, Integer> frequentExtremeMap = frequencyExtremeMap.get(REPEAT_LONG);

        if (null == frequentExtremeMap) {
            frequentExtremeMap = new HashMap<>();
        }

        if (lastTarget == null) return;

        if (lastTarget == target) {
            Integer realTimeExtreme = realTimeExtremeMap.get(REPEAT_LONG);
            Integer count = frequentExtremeMap.getOrDefault(realTimeExtreme, 0);
            if (null != count && count > -1) {
                frequentExtremeMap.put(realTimeExtreme, ++count);
                frequencyExtremeMap.put(REPEAT_LONG, frequentExtremeMap);
            }
        }
    }

    /**
     * 更新实时连号极值
     * @param target
     * @param lastTarget
     * @param realTimeExtremeMap
     */
    public static void measureRealTimeExtremeFrequency(int target, Integer lastTarget, Map<String, Integer> realTimeExtremeMap) {

        // 刚开始统计时，由于没有上期值直接赋 -1
        if (lastTarget == null) {
            realTimeExtremeMap.put(REPEAT_LONG, -1);
            return;
        }

        if (target == lastTarget) {
            realTimeExtremeMap.put(REPEAT_LONG, 0);
        } else {
            Integer absenceSpan = realTimeExtremeMap.getOrDefault(REPEAT_LONG, -1);
            if (absenceSpan == -1) {
                absenceSpan += 2;
            } else {
                absenceSpan += 1;
            }

            realTimeExtremeMap.put(REPEAT_LONG, absenceSpan);
        }
    }
}
