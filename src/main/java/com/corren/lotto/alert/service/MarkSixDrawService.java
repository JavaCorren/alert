package com.corren.lotto.alert.service;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * @author ZhangGR
 * created on 2019/9/1
 * @description
 **/
@Setter
@Getter
public final class MarkSixDrawService extends AbstractDrawService {

    // 抽奖号码候选数组
    protected int[] candidateNumberArray = {
            1, 2, 3, 4, 5, 6, 7, 8, 9,
            10,11,12,13,14,15,16,17,18,19,
            20,21,22,23,24,25,26,27,28,29,
            30,31,32,33,34,35,36,37,38,39,
            40,41,42,43,44,45,46,47,48,49
    };

    // 最小号码
    protected int minimumCandidateNumber = 1;

    // 最大号码
    protected int maximumCandidateNumber = 49;

    // 开奖号码数量
    protected int drawnNumberCount = 7;


    public static void main(String[] args) {
        AbstractDrawService drawService = new MarkSixDrawService();
        for (int i = 0; i < 100; i++) {
            System.out.println(JSON.toJSON(drawService.draw()));
        }
    }
}
