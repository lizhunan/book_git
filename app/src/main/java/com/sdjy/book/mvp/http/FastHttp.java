package com.sdjy.book.mvp.http;

import android.content.Context;
import android.util.Log;

import com.sdjy.book.mvp.http.base.BaseEnetity;
import com.sdjy.book.mvp.http.base.HttpType;

/**
 * Created by 李竹楠 on 2018/3/13.
 * 自动构建请求对象，可自定义
 */

public class FastHttp {

    private static final String TAG = "FastHttp";
    private static String ADDRESS = "http://127.0.0.1";
    private static boolean useCache = false;

    /**
     * 绑定服务器
     *
     * @param host 服务器IP
     * @param port 服务器端口
     */
    public static void bindServer(String host, int port) {
        Log.d("bindServer", "port:" + port);
        if (!host.endsWith("/")) {
            host = host + ":" + port;
            ADDRESS = host + "/";
        }
        Log.d("bindServer", ADDRESS);
    }

    /**
     * 绑定服务器，默认端口80
     *
     * @param host 服务器IP
     */
    public static void bindServer(String host) {
        if (!host.endsWith("/")) {
            ADDRESS = host + "/";
        }
    }

    /**
     * 是否使用缓存
     *
     * @param cache
     */
    public static void setUseCache(boolean cache) {
        useCache = cache;
    }


    /**
     * 请求分流
     *
     * @param type                 请求类型
     * @param context              上下文对象
     * @param baseEntity           entity类
     * @param onSubscriberListener 回调
     */
    public static void SEND(HttpType type, Context context, BaseEnetity baseEntity, OnSubscriberListener onSubscriberListener) {
        if (type.toString().equals(HttpType.GET.toString())) {
            HttpMothed.getInstance(ADDRESS).GET(baseEntity, context, onSubscriberListener);
        } else if (type.toString().equals(HttpType.POST.toString())) {
            HttpMothed.getInstance(ADDRESS).POST(baseEntity, context, onSubscriberListener);
        } else if (type.toString().equals(HttpType.POST_JSON.toString())) {
            HttpMothed.getInstance(ADDRESS).POST_JSON(baseEntity, context, onSubscriberListener);
        } else {
            Log.i(TAG, "Type unknown");
        }
    }
}
