package com.sdjy.book.mvp.http;

/**
 * Created by 李竹楠 on 2018/3/14.
 * 订阅处理接口
 */

public interface OnSubscriberListener {

    /**
     * 回调结束
     */
    void onCompleted();

    /**
     * 回调开始
     */
    void onStart();

    /**
     * 成功
     *
     * @param o 返回对象
     */
    void onSuccess(Object o);

    /**
     * 失败
     *
     * @param s 失败code
     */
    void onFault(String s);
}
