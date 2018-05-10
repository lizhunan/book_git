package com.sdjy.book.app;

import android.annotation.SuppressLint;

/**
 * Created by 李竹楠 on 2018/3/6.
 * 常量池
 */

public final class Constant {

    /*
    *
    * 服务器常量----------------------------------------------------------
    * */
    /**
     * 服务器地址
     */
    public static final String HOST = "http://192.168.43.190";
    /**
     * 端口号
     */
    public static final int PORT = 8080;

    /**
     * 注册接口
     */
    public static final String REGISTER_ = "user/re";
    /**
     * 普通登录接口
     */
    public static final String LOGIN_ = "user/lo";

    public static final int OK_CODE = 1000;
    public static final int CLIENT_ERR_CODE = 1001;
    public static final int SERVER_ERR_CODE = 1002;

    /*
    *
    * 服务器常量----------------------------------------------------------
    * */

    /**
     * 日志全局标记
     */
    public static final String GLOBAL_TAG = "BOOK_SYSTEM";

    /*
    *
    * 用户常量----------------------------------------------------------
    * */

    public static String USERNAME = "";
    public static String PASSWORD = "";
    public static String PHONE = "";
    public static String QQ = "";
    public static String WX = "";
    /**
     * token有效性验证
     */
    public static String TOKEN = "";

    /*
    *
    * 用户常量----------------------------------------------------------
    * */

    /*
    *
    * 全局常量------------------------------------------------------------
    * */
    /**
     * 地图缓存
     */
    @SuppressLint("SdCardPath")
    public static final String MAP_CACHE = "/mnt/sdcard/amp22/";
    /**
     * 缓存地址
     */
    public static final String CACHE_NAME = "cache";
    /*
    *
    * 全局常量------------------------------------------------------------
    * */

    /*
    * Preference Key------------------------------------------------------
    * */
    public static final String LOGOUT_PRE_KEY = "pref_key_logout_settings";
    public static final String CACHE_PRE_KEY = "pref_key_clearcache_settings";
    public static final String SWIPRENETMODE_PRE_KEY = "pref_key_netmode_settings";
     /*
    * Preference Key-------------------------------------------------------
    * */
}
