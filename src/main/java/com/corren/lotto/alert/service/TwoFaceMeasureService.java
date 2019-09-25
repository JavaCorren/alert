package com.corren.lotto.alert.service;

import com.corren.lotto.alert.entity.ExtremeCollector;
import com.corren.lotto.alert.enumeration.Lottery;
import com.corren.lotto.alert.enumeration.LottoNature;
import com.corren.lotto.alert.enumeration.LottoRule;
import com.corren.lotto.alert.util.LargeSmallMeasureUtil;
import com.corren.lotto.alert.util.OddEvenMeasureUtil;

import java.util.Map;

/**
 * @author ZhangGR
 * @create on 2019-09-02
 * @description 双面极值度量服务抽象类
 */
public class TwoFaceMeasureService implements MeasureService{

    @Override
    public void measure(int target, ExtremeCollector extremeCollector) {

        Lottery lottery = extremeCollector.getLottery();
        LottoNature nature = LottoNature.getByValue(lottery.getValue());
        LottoRule[] rules = nature.getRules();
        Map<String, Map<Integer, Integer>> frequencyExtremeMap = extremeCollector.getFrequencyExtremeMap();
        Map<String, Integer> realTimeExtremeMap = extremeCollector.getRealTimeExtremeMap();
        Integer lastTarget = extremeCollector.getLastTarget();

        // 双面极值频度更新
        updateFrequencyMap(rules, realTimeExtremeMap, frequencyExtremeMap, target, lastTarget, nature);

        // 双面极值近期频度更新
        updateRealTimeMap(rules, realTimeExtremeMap, target, nature);

        // 统计结束后将对应位置的上期球号更新
        extremeCollector.setLastTarget(target);
    }

    /**
     * 双面极值频度更新
     * @param rules
     * @param realTimeExtremeMap
     * @param frequencyExtremeMap
     * @param target
     * @param lastTarget
     * @param nature
     */
    private void updateFrequencyMap(LottoRule[] rules, Map<String, Integer> realTimeExtremeMap, Map<String, Map<Integer, Integer>> frequencyExtremeMap, int target, Integer lastTarget, LottoNature nature) {

        // 第一期开奖直接返回
        if (lastTarget == null) return;

        for (LottoRule rule : rules) {
            switch (rule) {
                case ODD_EVEN:
                    OddEvenMeasureUtil.measureExtremeFrequency(target, lastTarget, frequencyExtremeMap, realTimeExtremeMap);
                    break;
                case LARGE_SMALL:
                    LargeSmallMeasureUtil.measureExtremeFrequency(target, lastTarget, frequencyExtremeMap, realTimeExtremeMap, nature);
                    break;
                case DRAGON_TIGER:
                case RANDOM_DRAGON_TIGER:
                case REPEAT_DRAWN_COUNT:
            }
        }
    }

    /**
     * 双面极值近期频度更新
     * @param rules
     * @param realTimeExtremeMap
     * @param target
     * @param nature
     */
    private void updateRealTimeMap(LottoRule[] rules, Map<String, Integer> realTimeExtremeMap, int target, LottoNature nature) {

        for (LottoRule rule : rules) {
            switch (rule) {
                case ODD_EVEN:
                    OddEvenMeasureUtil.measureRealTimeCount(target, realTimeExtremeMap);
                    break;
                case LARGE_SMALL:
                    LargeSmallMeasureUtil.measureRealTimeCount(target, realTimeExtremeMap, nature);
                    break;
                case DRAGON_TIGER:
                case RANDOM_DRAGON_TIGER:
                case REPEAT_DRAWN_COUNT:
            }
        }
    }
}
