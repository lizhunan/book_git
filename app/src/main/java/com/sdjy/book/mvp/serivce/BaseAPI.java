package com.sdjy.book.mvp.serivce;

import com.sdjy.book.mvp.http.HttpResult;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by 李竹楠 on 2018/3/13.
 * 默认基础的api，方式：get，post，download，upload
 */

public interface BaseAPI {

    /**
     * 普通get请求
     *
     * @param path 请求地址
     * @param map  请求参数
     * @return 观察者泛型
     */
    @GET("{path}")
    Observable<ResponseBody> _GET(
            @Path(value = "path", encoded = true) String path,
            @QueryMap Map<String, Object> map);

    /**
     * 普通post请求
     *
     * @param path 请求地址
     * @param map  请求参数
     * @return 观察者泛型
     */
    @POST("{path}")
    Observable<ResponseBody> _POST(
            @Path(value = "path", encoded = true) String path,
            @QueryMap Map<String, Object> map);

    /**
     * post方式请求json数据
     *
     * @param path  请求地址
     * @param route 请求体
     * @return 观察者泛型
     */
    @POST("{path}")
    Observable<ResponseBody> _POST_JSON(
            @Path(value = "path", encoded = true) String path,
            @Body RequestBody route);

    /**
     * get方式请求json数据
     *
     * @param path  请求地址
     * @param route 请求体
     * @return 观察者泛型
     */
    @GET("{path}")
    Observable<ResponseBody> _GET_JSON(
            @Path(value = "path", encoded = true) String path,
            @Body RequestBody route);
}
