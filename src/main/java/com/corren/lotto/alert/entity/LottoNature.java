package com.corren.lotto.alert.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ZhangGR
 * created on 2019/9/1
 * @description 下注配置枚举类
 **/
@AllArgsConstructor
@Getter
public enum LottoNature {

    // 六合彩
    MARK_SIX(
            new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49},
            7, 1, 49, false
            ),

    // 北京PK10
    BEIJING_PK10(
            new int[]{1, 2, 3, 4, 5 ,6, 7, 8, 9, 10},
            10, 1, 10, false
    ),

    // 福彩3D
    WELFARE_LOTTO_PICK_3 (
            new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            3, 0, 9, true
    ),

    // 体彩排列3
    SPORTS_LOTTO_PICK_3 (
            new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            3, 0, 9, true
    ),

    // 马耳他基诺彩
    MALTA_KENO(
            new int[]{ 1,  2,  3,  4,  5,  6,  7,  8,  9, 10,
                      11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                      21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                      31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                      41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
                      51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
                      61, 62, 63, 64, 65, 66, 67, 68, 69, 70,
                      71, 72, 73, 74, 75, 76, 77, 78, 79, 80
            }, 20, 1, 80, false
    );


    // 开奖号码候选数字数组
    private int[] candidateNumberArray;

    // 开奖号码数量
    private int drawnNumberCount;

    // 最小号码
    private int minimumCandidateNumber ;

    // 最大号码
    private int maximumCandidateNumber ;

    // 允许后续号码重复
    private boolean allowRepeatedCandidateNumbers;


}
