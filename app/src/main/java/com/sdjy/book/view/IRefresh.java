package com.sdjy.book.view;

/**
 * Created by 李竹楠 on 2018/3/16.
 * 更新UI接口
 */

public interface IRefresh<T> {
    /**
     * 成功
     *
     * @param t 返回泛型
     */
    void onSuccess(T t);

    /**
     * 失败
     *
     * @param s 失败返回值
     */
    void onFiled(String s);

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

}
