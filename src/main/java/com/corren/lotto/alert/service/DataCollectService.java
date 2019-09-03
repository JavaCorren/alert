package com.corren.lotto.alert.service;

import com.alibaba.fastjson.JSON;
import com.corren.lotto.alert.entity.Response;
import com.corren.lotto.alert.retrofit.LottoDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;

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

}
