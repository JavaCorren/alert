package com.corren.lotto.alert.service;

import com.corren.lotto.alert.entity.ExtremeCollector;

import java.util.List;

/**
 * @author ZhangGR
 * @create on 2019-09-02
 * @description 极值度量服务
 */
public interface MeasureService {

    void measure(int target, ExtremeCollector extremeCollector);
}
