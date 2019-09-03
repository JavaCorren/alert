package com.corren.lotto.alert.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ZhangGR
 * @create on 2019-08-22
 * @description
 */
@Controller
public class HelloController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        throw new NullPointerException();
    }

}
