package com.corren.lotto.alert.controller;

import com.corren.lotto.alert.entity.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangGR
 * created on 2019/9/3
 * @description
 **/
@RestController
public class DataCollectController {

    @GetMapping("/data/crawl")
    @ResponseBody
    public Response collect(@RequestParam("name") String name) {
        return null;
    }

}
