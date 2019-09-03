package com.corren.lotto.alert.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author ZhangGR
 * created on 2019/9/1
 * @description 彩票属性枚举类
 **/
@AllArgsConstructor
@Getter
public enum LottoNature {

    // 六合彩
    MARK_SIX(1,
            new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                      11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                      21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                      31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                      41, 42, 43, 44, 45, 46, 47, 48, 49},
            7, 1, 49, false,
            new LottoRule[]{LottoRule.ODD_EVEN, LottoRule.LARGE_SMALL, LottoRule.DRAGON_TIGER}
            ),

    // 北京PK10
    BEIJING_PK10(2,
            new int[]{1, 2, 3, 4, 5 ,6, 7, 8, 9, 10},
            10, 1, 10, false,
            new LottoRule[]{LottoRule.ODD_EVEN, LottoRule.LARGE_SMALL, LottoRule.DRAGON_TIGER}
    ),

    // 福彩3D
    WELFARE_LOTTO_PICK_3 (3,
            new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            3, 0, 9, true,
            new LottoRule[]{LottoRule.ODD_EVEN, LottoRule.LARGE_SMALL, LottoRule.DRAGON_TIGER}
    ),

    // 体彩排列3
    SPORTS_LOTTO_PICK_3 (4,
            new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            3, 0, 9, true,
            new LottoRule[]{LottoRule.ODD_EVEN, LottoRule.LARGE_SMALL, LottoRule.DRAGON_TIGER}
    ),

    // 马耳他基诺彩
    MALTA_KENO(5,
            new int[]{ 1,  2,  3,  4,  5,  6,  7,  8,  9, 10,
                      11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                      21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                      31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                      41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
                      51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
                      61, 62, 63, 64, 65, 66, 67, 68, 69, 70,
                      71, 72, 73, 74, 75, 76, 77, 78, 79, 80
            }, 20, 1, 80, false,
            new LottoRule[]{LottoRule.ODD_EVEN, LottoRule.LARGE_SMALL, LottoRule.DRAGON_TIGER}
    ),

    // 重庆时时彩
    CHONGQING_LOTTO (6,
            new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            3, 0, 9, true,
            new LottoRule[]{LottoRule.ODD_EVEN, LottoRule.LARGE_SMALL, LottoRule.DRAGON_TIGER}
    );


    private int value;

    // 开奖号码候选数字数组
    private int[] candidateNumberArray;

    // 开奖号码数量
    private int drawnNumberCount;

    // 最小号码
    private int minimumCandidateNumber ;

    // 最大号码
    private int maximumCandidateNumber ;

    // 允许候选号码重复
    private boolean allowRepeatedCandidateNumbers;

    // 彩种玩法列表
    private LottoRule[] rules;

    /**
     * 根据value获取对应的配置枚举类
     * @param value
     * @return
     */
    public static LottoNature getByValue(int value) {
       return Arrays.stream(LottoNature.values())
                .filter(s -> s.getValue() == value)
                .findAny()
                .orElse(null);
    }

}
