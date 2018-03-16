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
    public static final String HOST = "https://api.douban.com/v2/movie/";
    /**
     * 端口号
     */
    public static final int PORT = 80;

    /**
     * 注册接口
     */
    public static final String REGISTER_ = "register";


    /*
    *
    * 服务器常量----------------------------------------------------------
    * */

    /**
     * 日志全局标记
     */
    public static final String GLOBAL_TAG = "BOOK_SYSTEM";
    /**
     * token有效性验证
     */
    public static String TOKEN = "";

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
