package com.corren.lotto.alert.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ZhangGR
 * @create on 2019-09-02
 * @description 彩种枚举类
 */
@AllArgsConstructor
@Getter
public enum Lottery {

    // 六合彩
    MARK_SIX(1),

    // 北京PK10
    BEIJING_PK10(2),

    // 福彩3D
    WELFARE_LOTTO_PICK_3 (3),

    // 体彩排列3
    SPORTS_LOTTO_PICK_3 (4),

    // 马耳他基诺彩
    MALTA_KENO(5),

    // 重庆时时彩
    CHONGQING_LOTTO (6);

    private int value;
}
