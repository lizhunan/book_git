package com.sdjy.book.ui;

/**
 * Created by 李竹楠 on 2018/3/14.
 * 显示进度条接口
 */

public interface OnProgressListener {
    /**
     * 隐藏progress
     */
    void onCancelProgress();

    /**
     * 显示progress
     */
    void onStartProgress();
}
