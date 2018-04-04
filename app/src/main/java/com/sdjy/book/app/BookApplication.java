package com.sdjy.book.app;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.BuildConfig;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.sdjy.book.mvp.http.FastHttp;
import com.sdjy.book.receiver.NetWorkStateReceiver;


/**
 * Created by 李竹楠 on 2018/3/6.
 * 全局application
 */

public class BookApplication extends Application {

    private static BookApplication bookApplication;

    public BookApplication() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        bookApplication = this;
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
        registerNetWorkListener();
        /*
        * 绑定服务器地址
        * */
        FastHttp.bindServer(Constant.HOST,Constant.PORT);
    }

    /**
     * 获取全局context
     *
     * @return context
     */
    public static Context getContext() {
        return bookApplication.getApplicationContext();
    }

    /**
     * 注册网络监听
     */
    private void registerNetWorkListener() {
        NetWorkStateReceiver mReceiver = new NetWorkStateReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, filter);
    }
}
