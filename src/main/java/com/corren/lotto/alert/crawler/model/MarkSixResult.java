package com.corren.lotto.alert.crawler.model;

import lombok.Data;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @author ZhangGR
 * @create on 2019-08-25
 * @description
 */
@Data
@TargetUrl("http://www.nfd.com.tw/house/year/F2002.htm")
public class MarkSixResult {

    // 年份
    private Integer year;

    // 期数
    private Integer times;

    // 球1
    private Integer n1;

    // 球2
    private Integer n2;

    // 球3
    private Integer n3;

    // 球4
    private Integer n4;

    // 球5
    private Integer n5;

    // 球6
    private Integer n6;

    // 特球
    private Integer s;

}
