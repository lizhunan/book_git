package com.sdjy.book.mvp.model;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sdjy.book.R;
import com.sdjy.book.mvp.entity.BooksInfo;
import com.sdjy.book.mvp.entity.User;
import com.sdjy.book.mvp.entity.net.LoginEnetity;
import com.sdjy.book.mvp.http.FastHttp;
import com.sdjy.book.mvp.http.OnSubscriberListener;
import com.sdjy.book.mvp.http.base.HttpType;
import com.sdjy.book.mvp.http.base.ResponseHttp;
import com.sdjy.book.mvp.http.base.ResponseHttpBy;
import com.sdjy.book.mvp.presenter.IBase;
import com.sdjy.book.util.JsonParseUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 李竹楠 on 2018/3/22.
 * 普通方法登录model
 */

public class LoginModel {

    public void login(final IBase<ResponseHttpBy<User>> iBase, final Context context, final String username, String password) {
        iBase.onLoading(0);
        FastHttp.SEND(HttpType.POST, context, new LoginEnetity(username, password), new OnSubscriberListener() {
            @Override
            public void onCompleted() {
                iBase.onLoaded();
            }

            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(Object o) {
                Type type = new TypeToken<ResponseHttpBy<User>>() {
                }.getType();
                ResponseHttpBy<User> userResponseHttp = JsonParseUtil.getGson().fromJson(o.toString(), type);
                iBase.onSuccess(userResponseHttp);
            }

            @Override
            public void onFault(String s) {
                iBase.onFailed(context.getString(R.string.net_error));
            }
        });
    }
}
