package com.sdjy.book.mvp.http.base;

/**
 * Created by 李竹楠 on 2018/3/21.
 * 返回值实体
 */

public class ResponseHttp<T> {

    private boolean isOk;
    private String logMsg;
    private T resultData;

    public ResponseHttp() {
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public String getLogMsg() {
        return logMsg;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }
}
