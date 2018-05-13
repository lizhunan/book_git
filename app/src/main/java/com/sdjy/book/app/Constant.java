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
    //public static final String HOST = "http://192.168.43.190";
    //public static final String HOST = "http://192.168.43.20";
    public static final String HOST = "http://ctwteam.club";
    public static final String IMAGE_HOST = HOST + "/recyleBook/image";
    /**
     * 端口号
     */
    public static final int PORT = 8080;
    //public static final int PORT = 8090;
    /**
     * 注册接口
     */
    public static final String REGISTER_ = "user/re";
    /**
     * 普通登录接口
     */
    public static final String LOGIN_ = "recyleBook/apiByForm/userLogin";

    /**
     * 扫码投放接口
     */
    public static final String START_INPUT_ = "recyleBook/apiByForm/startInput";
    /**
     * 返回单个书籍详细信息
     */
    public static final String BOOK_INFO_ = "recyleBook/apiByForm/bookInfo";
    /**
     * 读取所有书籍信息
     */
    public static final String GET_BOOKS_INFO_ = "recyleBook/apiByForm/getBooksInfo";

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

    public static String USERNAME = "USERNAME";
    public static String LOGINNAME = "LOGINNAME";
    public static String PHONE = "PHONE";
    public static String AGE = "AGE";
    public static String SEX = "SEX";
    public static String USERTYPE = "USERTYPE";
    public static String QQ = "WX";
    public static String WX = "WX";
    public static String TOKEN = "TOKEN";
    public static String POINTS = "POINTS";
    public static String LASTLOGINDATE = "LASTLOGINDATE";
    public static String PROFILE = "PROFILE";


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
