package com.sdjy.book.mvp.http;

import android.content.Context;
import android.content.Entity;
import android.util.Log;


import com.orhanobut.logger.Logger;
import com.sdjy.book.app.BookApplication;
import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.http.base.BaseEnetity;
import com.sdjy.book.mvp.serivce.BaseAPI;
import com.sdjy.book.mvp.serivce.HttpAPI;
import com.sdjy.book.receiver.NetWorkStateReceiver;
import com.sdjy.book.util.JsonParseUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 李竹楠 on 2018/3/13.
 * retrofit http 管理类
 */

public class HttpMothed {

    private static final String TAG = "FastClient";
    private static HttpMothed INSTANCE;
    private Context context;
    private MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");
    /**
     * 连接超时时间
     */
    private static final int DEFAULT_CONNECT_TIMEOUT = 30;
    /**
     * 写超时时间
     */
    private static final int DEFAULT_WRITE_TIMEOUT = 30;
    /**
     * 读超时时间
     */
    private static final int DEFAULT_READ_TIMEOUT = 30;
    /**
     * 请求接口
     */
    private BaseAPI baseAPI = null;
    /**
     * 请求失败重连次数
     */
    private int RETRY_COUNT = 0;
    private OkHttpClient.Builder okHttpBuilder;
    private Retrofit retrofit;

    public HttpMothed(String URL) {
        okHttpBuilder = new OkHttpClient.Builder();
       /*
       * 设置缓存
       * */
        File cacheFile = new File(BookApplication.getContext().getExternalCacheDir(), Constant.CACHE_NAME);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (NetWorkStateReceiver.isNoAvailable) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetWorkStateReceiver.isNoAvailable) {
                    int maxAge = 0;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader(Constant.CACHE_NAME)// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader(Constant.CACHE_NAME)
                            .build();
                }
                return response;
            }
        };
        okHttpBuilder.cache(cache).addInterceptor(cacheInterceptor);
        /*
        * 设置头信息
        * */
      /*  Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .addHeader("Accept-Encoding", "gzip")
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        .method(originalRequest.method(), originalRequest.body());
                requestBuilder.addHeader("Authorization", "Bearer " + Constant.TOKEN);//添加请求头信息，服务器进行token有效性验证
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        okHttpBuilder.addInterceptor(headerInterceptor);*/
        okHttpBuilder.addNetworkInterceptor(
                new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS));
        /*
        * 设置超时和重新连接
        * */
        okHttpBuilder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
        //错误重连
        okHttpBuilder.retryOnConnectionFailure(true);
        retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .baseUrl("http://192.168.43.190:8080/user/")
                .build();
        baseAPI = retrofit.create(BaseAPI.class);
    }

    //获取单例
    public static HttpMothed getInstance(String url) {
        if (INSTANCE == null) {
            INSTANCE = new HttpMothed(url);
        }
        return INSTANCE;
    }

    /**
     * 获取retrofit
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }


    /**
     * 更改服务器地址
     *
     * @param HOST 服务器地址
     * @param PORT 端口号
     */
    public void changeBaseUrl(String HOST, String PORT) {
        retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.HOST + Constant.PORT)
                .build();
        baseAPI = retrofit.create(BaseAPI.class);
    }

    /**
     * 获取httpService
     */
    public BaseAPI getHttpService() {
        return baseAPI;
    }

    /**
     * 线程转换
     */
    final Observable.Transformer schedulersTransformer = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    ;
        }
    };

    /**
     * POST请求
     *
     * @param entity               继承与BaseEntity的实体类
     * @param context              上下文对象
     * @param onSubscriberListener 回调
     */
    public void POST(BaseEnetity entity, Context context, OnSubscriberListener onSubscriberListener) {
        baseAPI._POST(entity.getRuqestURL(), fiterURLFromRequestParams(entity.getMapEnticty()))
                .compose(schedulersTransformer)
                .subscribe(new Subscriber(onSubscriberListener, context));
    }

    public void POST_JSON(BaseEnetity enetity,Context context,OnSubscriberListener onSubscriberListener){
        String json = JsonParseUtil.modeToJson(enetity);
        RequestBody body = RequestBody.create(jsonMediaType, fiterURLFromJSON(json));
        baseAPI
                ._POST_JSON(enetity.getRuqestURL(), body)
                .compose(schedulersTransformer)
                .subscribe(new Subscriber(onSubscriberListener, context));
    }

    /**
     * GET请求
     *
     * @param entity               继承与BaseEntity的实体类
     * @param context              上下文对象
     * @param onSubscriberListener 回调
     */
    public void GET(BaseEnetity entity, Context context, OnSubscriberListener onSubscriberListener) {
        baseAPI
                ._GET(entity.getRuqestURL(), fiterURLFromRequestParams(entity.getMapEnticty()))
                .compose(schedulersTransformer)
                .subscribe(new Subscriber(onSubscriberListener, context));
    }

    /**
     * 查找url
     *
     * @param params
     * @return
     */
    private static Map<String, Object> fiterURLFromRequestParams(Map<String, Object> params) {
        if (params.containsKey("ruqestURL"))
            params.remove("ruqestURL");
        return params;
    }

    private static String fiterURLFromJSON(String params) {
        try {
            JSONObject jsonObject = new JSONObject(params);
            if (jsonObject.has("ruqestURL"))
                jsonObject.remove("ruqestURL");
            return jsonObject.toString();
        } catch (JSONException e) {
            return "";
        }
    }

    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
        @Override
        public T call(HttpResult<T> tHttpResult) {
            if (tHttpResult.getResultCode() != 0) {
                Log.d("httpResultFunc", "error");
            }
            return tHttpResult.getData();
        }
    }

}
