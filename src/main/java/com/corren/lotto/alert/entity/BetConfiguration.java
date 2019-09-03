package com.corren.lotto.alert.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhangGR
 * @create on 2019-09-02
 * @description 下注配置
 */
@Data
public class BetConfiguration {

    // 起始下注额度
    private BigDecimal initial;

    // 止损点金额数
    private BigDecimal stopNow;

    // 期望回报率
    private Double returnRate;

    // 下注赔率
    private Double odds;

}
