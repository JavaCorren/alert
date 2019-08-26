package com.corren.lotto.alert.calculator.marksix;

import com.corren.lotto.alert.enumeration.BetType;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhangGR
 * @create on 2019-08-26
 * @description 下注结果
 */
@Data
public class BetDrawOutcome {

    // 赔率
    private Double odds;

    // 止损点
    private Long stopLoss;

    // 起始下注额
    private Long minBetAmount;

    // 下注方式
    private BetType betType;

    // 下注阈值
    private Integer valve;

    // 盈利额
    private BigDecimal profits;

    // 备注
    private String remark;

    // 下注次数
    private Long betTimes;
}
