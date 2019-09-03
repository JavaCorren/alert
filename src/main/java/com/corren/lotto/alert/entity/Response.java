package com.corren.lotto.alert.entity;

import lombok.Data;

/**
 * @author ZhangGR
 * created on 2019/9/2
 * @description
 **/
@Data
public class Response<T> {

    private int code;
    private String msg;
    private T data;

    public static Response success() {
        Response response = new Response();
        response.setMsg("执行成功");
        return response;
    }

    public Response<T> success(T data) {
        Response response = new Response();
        response.setMsg("执行成功");
        response.setData(data);
        return response;
    }
}
