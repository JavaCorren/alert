package com.corren.lotto.alert.calculator.marksix;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangGR
 * @create on 2019-08-26
 * @description Lotto extreme collector 极值收集器
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LottoExtremeCollector {

    private List<LottoNumberExtremeCollector> collectors;

    // 位置
    public static LottoExtremeCollector doCollect(List<Integer[]> results) {

        if (CollectionUtils.isEmpty(results)) return null;

        List<LottoNumberExtremeCollector> collectors = new ArrayList<>(7);

        for (int i = 0; i < 7; i++) {
            collectors.add(new LottoNumberExtremeCollector(i));
        }

        for (Integer[] result : results) {
            for (int j = 0; j < result.length; j++) {
                collectors.get(j).doCount(result[j]);
            }
        }

        LottoExtremeCollector collector = new LottoExtremeCollector(collectors);

        return collector;
    }
}
