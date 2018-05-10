package com.sdjy.book.mvp.presenter;

/**
 * Created by 李竹楠 on 2018/3/16.
 * 基础interface，所有的presenter的接口都继承于base
 */

public interface IBase<T> {

    /**
     * 加载中
     *
     * @param process 加载进度
     */
    void onLoading(int process);

    /**
     * 加载完成
     */
    void onLoaded();

    /**
     * 成功
     *
     * @param t 返回泛型
     */
    void onSuccess(T t);

    /**
     * 失败
     *
     * @param failed 返回失败信息
     */
    void onFailed(String failed);
}
