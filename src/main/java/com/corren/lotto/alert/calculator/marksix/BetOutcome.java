package com.corren.lotto.alert.calculator.marksix;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhangGR
 * @create on 2019-08-27
 * @description
 */
@Data
public class BetOutcome {

    // 总下注额度
    private BigDecimal cost;

    // 总利润
    private BigDecimal profit;

    // 下注次数
    private Long betTimes;

    // 返水
    private BigDecimal rebate;

    // 派彩
    private BigDecimal bonus;

    // 是否爆
    private Boolean over;

    private LottoNumberExtremeCollector extremeCollector;
}
