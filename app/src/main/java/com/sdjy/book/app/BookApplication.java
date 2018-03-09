package com.sdjy.book.app;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.BuildConfig;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;


/**
 * Created by 李竹楠 on 2018/3/6.
 * 全局application
 */

public class BookApplication extends Application {

    private static BookApplication bookApplication;

    public BookApplication() {

    }

    public static BookApplication newInstance() {
        if (bookApplication == null) {
            bookApplication = new BookApplication();
        }
        return bookApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /*
        * 配置logger日志组件
        * */
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(2)
                .methodOffset(7)
                .tag(Constant.GLOBAL_TAG)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    /**
     * 获取全局context
     *
     * @return context
     */
    public static Context getContext() {
        return bookApplication.getApplicationContext();
    }
}
