package com.corren.lotto.alert.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author ZhangGR
 * created on 2019/9/2
 * @description
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public String handle() {

        return "error";
    }



}
