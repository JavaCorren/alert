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

    public static void measureExtremeFrequency(int target, Integer lastTarget, Map<String, Map<Integer, Integer>> frequencyExtremeMap, Map<String, Integer> realTimeExtremeMap) {

        Map<Integer, Integer> frequenceExtremeMap = frequencyExtremeMap.get(REPEAT_LONG);

        if (null == frequenceExtremeMap) {
            frequenceExtremeMap = new HashMap<>();
        }


    }

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
