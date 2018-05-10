package com.sdjy.book.mvp.http.base;

/**
 * Created by 李竹楠 on 2018/3/21.
 * 返回值实体
 */

@Deprecated
public class ResponseHttpBy<T> {

    private int status;
    private String desc;
    private T data;

    public ResponseHttpBy() {
    }

    public ResponseHttpBy(int status, String desc, T data) {
        this.status = status;
        this.desc = desc;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
