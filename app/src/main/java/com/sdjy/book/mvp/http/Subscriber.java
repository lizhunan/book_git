package com.sdjy.book.mvp.http;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdjy.book.ui.OnProgressListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import rx.functions.Func1;

/**
 * Created by 李竹楠 on 2018/3/13.
 * 重新封装Subscriber类
 */

public class Subscriber<T extends ResponseBody> extends rx.Subscriber<T> {

    private OnSubscriberListener subscriberListener;
    private Context mContext;

    public Subscriber() {
    }

    public Subscriber(OnSubscriberListener subscriberListener, Context mContext) {
        this.subscriberListener = subscriberListener;
        this.mContext = mContext;
    }

    @Override
    public void onStart() {
        subscriberListener.onStart();
    }

    @Override
    public void onCompleted() {
        subscriberListener.onCompleted();
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        subscriberListener.onFault(e.getMessage());
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    @Override
    public void onNext(T t) {
        try {
            String jsonString = new String(t.bytes());

            subscriberListener.onSuccess(jsonString);
        } catch (Exception e) {
            subscriberListener.onFault(e.getMessage());
            e.printStackTrace();
        }
    }

}
