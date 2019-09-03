package com.corren.lotto.alert.service;

import com.alibaba.fastjson.JSON;
import com.corren.lotto.alert.entity.ExtremeCollector;
import com.corren.lotto.alert.enumeration.Lottery;
import com.corren.lotto.alert.enumeration.LottoNature;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ZhangGR
 * created on 2019/9/1
 * @description 抽奖抽象实现类
 **/
public class DefaultDrawService implements DrawService{

    protected Random random = new Random();

    /**
     * 抽奖具体实现（纯随机）
     * @return
     */
    @Override
    public Integer[] draw(LottoNature lottoNature) {

        int[] candidateNumberArray = lottoNature.getCandidateNumberArray();
        int drawnNumberCount = lottoNature.getDrawnNumberCount();
        int maximumCandidateNumber = lottoNature.getMaximumCandidateNumber();
        int minimumCandidateNumber = lottoNature.getMinimumCandidateNumber();
        boolean allowRepeatedCandidateNumbers = lottoNature.isAllowRepeatedCandidateNumbers();

        List<Integer> indexList = new ArrayList<>(drawnNumberCount);

        do {

            // 抽取随机号位
            int index = random.nextInt(maximumCandidateNumber - minimumCandidateNumber + 1);

            // 根据彩种特性判断是否需要重新抽号
            if (!allowRepeatedCandidateNumbers && indexList.contains(index)) {
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

    public static void main(String[] args) {
        DrawService drawService = new DefaultDrawService();
        List<Integer[]> resultList = new ArrayList<>();
        for (int i = 0; i < 179 * 365; i++) {
            resultList.add(drawService.draw(LottoNature.MARK_SIX));
        }

        DecimalFormat decimalFormat = new DecimalFormat("00");
        resultList.stream().forEach(s -> {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < s.length; i++) {
                stringBuilder.append(decimalFormat.format(s[i]));
                if (i + 1 < s.length) {
                    stringBuilder.append(",");
                }
            }

            System.out.println(stringBuilder.toString());
        });

        int count = LottoNature.MARK_SIX.getDrawnNumberCount();

        List<ExtremeCollector> collectorList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            ExtremeCollector collector = new ExtremeCollector(Lottery.MARK_SIX, i);
            collectorList.add(collector);
        }

        MeasureService measureService = new TwoFaceMeasureService();
        for (Integer[] result : resultList) {
            for (int i = 0; i < count; i++) {
                measureService.measure(result[i], collectorList.get(i));
            }
        }

        System.out.println(JSON.toJSON(collectorList));
    }
}
