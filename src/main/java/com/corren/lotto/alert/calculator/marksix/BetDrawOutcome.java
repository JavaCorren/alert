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
    private BigDecimal stopLoss;

    // 起始下注额
    private BigDecimal minBetAmount;

    // 下注方式
    private BetType betType;

    // 盈利额
    private BigDecimal profits;

    // 备注
    private String remark;
}
