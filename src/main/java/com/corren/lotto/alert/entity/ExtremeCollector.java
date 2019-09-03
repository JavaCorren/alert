package com.corren.lotto.alert.entity;

import com.corren.lotto.alert.enumeration.Lottery;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangGR
 * @create on 2019-09-02
 * @description 极值数据收集器
 */
@Data
public class ExtremeCollector {

    // 彩种
    private Lottery lottery;

    // 统计对象的开出位置
    private Integer position;

    // 上期数字（用于确定是否长龙发生切换）
    private Integer lastTarget;

    // 极值频度统计（对中奖率的分析依据）
    private Map<String, Map<Integer, Integer>> frequencyExtremeMap;

    // 当前极值统计 (对报警提供情报依据)
    private Map<String, Integer> realTimeExtremeMap;

    /**
     * 初始化一个计算元组类
     * @param lottery
     * @param position
     */
    public ExtremeCollector(Lottery lottery, Integer position) {

        this.setLottery(lottery);
        this.setPosition(position);
        realTimeExtremeMap = new HashMap<>();
        frequencyExtremeMap = new HashMap<>();
    }

}
