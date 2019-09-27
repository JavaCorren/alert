package com.corren.lotto.alert.service;

import com.corren.lotto.alert.enumeration.LottoNature;

/**
 * @author ZhangGR
 * created on 2019/9/1
 * @description 模拟开奖接口
 **/
public interface DrawService {

    Integer[] draw(LottoNature nature);
}
