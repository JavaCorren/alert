package com.corren.lotto.alert.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ZhangGR
 * created on 2019/9/3
 * @description
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class DataCollectServiceTest {

    @Autowired
    private DataCollectService dataCollectService;

    /**
     * @verifies test
     * @see DataCollectService#collect(String)
     */
    @Test
    public void collect_shouldTest() throws Exception {
        dataCollectService.collect("");
    }
}
