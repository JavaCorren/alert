package com.corren.lotto.alert.calculator.marksix;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangGR
 * @create on 2019-08-26
 * @description
 */
@Data
public class LottoNumberExtremeCollector{

    // 位置
    private Integer position;

    // 上期数字（用于确定是否长龙发生切换）
    private Integer lastTarget;

    // 单双最近极值
    private Map<String, Long> countMap;

    // 极值频率表
    private Map<Long, Long> frequencyMap;

    private Long max = 0L;

    public LottoNumberExtremeCollector(Integer position) {

        this.position = position;
        this.countMap = new HashMap<>(2);
        countMap.put("even", 0L);
        countMap.put("odd", 0L);

        this.frequencyMap = new HashMap<>();
    }

    /**
     * count the extremes
     * 极值统计
     * @param target
     * @return
     */
    public LottoNumberExtremeCollector doCount(int target){

        updateFrequentMap(target);
        updateExtremeCountMap(target);

        this.lastTarget = target;

        return this;
    }

    /**
     * update the extreme count map
     * 更新极值表
     * @param target
     */
    private void updateExtremeCountMap(Integer target) {

        if (target % 2 == 0) {
            countMap.put("even", 0L);
            Long oddCount = countMap.get("odd");
            countMap.put("odd", ++oddCount);
            if (oddCount > max) max = oddCount;
        } else {
            countMap.put("odd", 0L);
            Long evenCount = countMap.get("even");
            countMap.put("even", ++evenCount);

            if (evenCount > max) max = evenCount;
        }

    }

    /**
     * update the frequency count map
     * 更新频谱表
     * @param target
     */
    private void updateFrequentMap(Integer target) {

        // 值不存在，不作处理
        if (null == target) return;

        // 长龙未现，不作处理
        if (null == lastTarget) return;

        // 发生长龙切换，作频谱统计
        if (target % 2 == 0 && lastTarget % 2 == 1) {

            Long evenExtreme = countMap.get("even");
            Long evenExtremeCount = frequencyMap.getOrDefault(evenExtreme, 0L);
            frequencyMap.put(evenExtreme, ++evenExtremeCount);

        } else if(target % 2 == 1 && lastTarget % 2 == 0) {

            Long oddExtreme = countMap.get("odd");
            Long oddExtremeCount = frequencyMap.getOrDefault(oddExtreme, 0L);
            frequencyMap.put(oddExtreme, ++oddExtremeCount);
        }
    }
}
