package com.corren.lotto.alert.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ZhangGR
 * created on 2019/9/1
 * @description 抽奖抽象实现类
 **/
public abstract class AbstractDrawService implements DrawService{

    // 抽奖号码候选数组
    protected int[] candidateNumberArray;

    // 最小号码
    protected int minimumCandidateNumber;

    // 最大号码
    protected int maximumCandidateNumber;

    // 开奖号码数量
    protected int drawnNumberCount;

    // 随机类
    protected Random random = new Random();

    /**
     * 抽奖具体实现（纯随机）
     * @return
     */
    @Override
    public Integer[] draw() {
        List<Integer> indexList = new ArrayList<>(drawnNumberCount);

        // get a list made up of seven non-repeated numbers
        do {
            int index = random.nextInt(maximumCandidateNumber - minimumCandidateNumber + 1);
            if (indexList.contains(index)) {
                continue;
            }
            indexList.add(index);
        } while (indexList.size() < drawnNumberCount);

        Integer[] result = new Integer[drawnNumberCount];

        for (int i = 0; i <drawnNumberCount; i++) {
            result[i] = candidateNumberArray[indexList.get(i)];
        }

        return result;
    }
}
