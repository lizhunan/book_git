package com.sdjy.book.mvp.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdjy.book.R;
import com.sdjy.book.mvp.entity.User;
import com.sdjy.book.mvp.entity.net.RegisterEnetity;
import com.sdjy.book.mvp.http.FastHttp;
import com.sdjy.book.mvp.http.OnSubscriberListener;
import com.sdjy.book.mvp.http.base.HttpType;
import com.sdjy.book.mvp.http.base.ResponseHttp;
import com.sdjy.book.mvp.presenter.IBase;
import com.sdjy.book.util.JsonParseUtil;

import java.lang.reflect.Type;

/**
 * Created by 李竹楠 on 2018/3/16.
 * 注册model
 */

public class RegisterModel {

    public void regist(final IBase<ResponseHttp<User>> iBase, final Context context, String username, String password, String phone) {
        iBase.onLoading(0);
        FastHttp.SEND(HttpType.POST, context, new RegisterEnetity(username, password, phone), new OnSubscriberListener() {
            @Override
            public void onCompleted() {
                Log.d("sss", "onCompleted");
                iBase.onLoaded();
            }

            @Override
            public void onStart() {
                Log.d("sss", "onStart");
            }

            @Override
            public void onSuccess(Object o) {
                Type type = new TypeToken<ResponseHttp<User>>() {
                }.getType();
                ResponseHttp<User> userResponseHttp = JsonParseUtil.getGson().fromJson(o.toString(), type);
                iBase.onSuccess(userResponseHttp);
            }

            @Override
            public void onFault(String s) {
                Log.d("sss", "onFault");
                iBase.onFailed(context.getString(R.string.net_error));
            }
        });
    }
}
