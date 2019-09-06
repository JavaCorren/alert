package com.corren.lotto.alert.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.corren.lotto.alert.entity.Response;
import com.corren.lotto.alert.retrofit.LottoDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author ZhangGR
 * created on 2019/9/3
 * @description 数据采集服务实现类
 **/
@Service
public class DataCollectService {

    @Value("${lottofetish.uid}")
    private String uid;

    @Value("${lottofetish.token}")
    private String token;

    @Value("${lottofetish.format}")
    private String format;

    @Autowired
    private LottoDataSource lottoDataSource;

    /**
     * @should test
     * @param name
     * @throws IOException
     */
    public void collect(String name) throws IOException {

    }

    public static void main(String[] args) {
        String s = "\"{\"classInfo\":{\"className\":\"xxx\",\"counselorId\":32248,\"coursePackageId\":7,\"termId\":10175,\"classopenTime\":1568563200000,\"status\":\"PUBLISHED\",\"tag\":0,\"specialClass\":0},\"course\":{\"courseTreeTemplateId\":40}}\"";
        Object parse = JSONObject.parse(s);
        System.out.println(JSON.toJSON(parse));
    }

}
