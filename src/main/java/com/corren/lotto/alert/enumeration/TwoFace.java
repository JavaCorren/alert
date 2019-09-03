package com.corren.lotto.alert.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ZhangGR
 * @create on 2019-09-02
 * @description 双面玩法枚举
 */
@AllArgsConstructor
@Getter
public enum TwoFace {

    // 单
    ODD("odd"),

    // 双
    EVEN("even"),


    // 大
    LARGE("large"),

    // 小
    SMALL("small"),


    // 质
    PRIME("prime"),

    // 复
    COMPOSITE("composite");

    private String name;

}
