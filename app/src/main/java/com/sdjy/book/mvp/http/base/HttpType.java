package com.sdjy.book.mvp.http.base;

/**
 * Created by 李竹楠 on 2018/3/15.
 * 定义请求资源方式
 */

public enum HttpType {

    //默认是键值
    GET("GET"),
    POST("POST"),
    GET_JSON("GET_JSON"),
    POST_JSON("POST_JSON");

    private final String value;

    HttpType(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
