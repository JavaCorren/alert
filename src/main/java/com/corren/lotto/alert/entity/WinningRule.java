package com.corren.lotto.alert.entity;

/**
 * @author ZhangGR
 * @create on 2019-09-02
 * @description 中奖规则类
 */
public abstract class WinningRule {

    /**
     * 判断是否中奖
     * @param draw 开奖号码
     * @param bet 下注号码
     * @return
     */
    public abstract boolean isLucky(Integer[] draw, Integer[] bet) ;
}
