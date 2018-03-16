package com.sdjy.book.mvp.model;

import android.content.Context;

import com.sdjy.book.mvp.entity.net.RegisterEnetity;
import com.sdjy.book.mvp.http.FastHttp;
import com.sdjy.book.mvp.http.OnSubscriberListener;
import com.sdjy.book.mvp.http.base.HttpType;
import com.sdjy.book.mvp.presenter.IBase;

/**
 * Created by 李竹楠 on 2018/3/16.
 * 注册model
 */

public class RegisterModel {

    public void regist(final IBase<String> iBase, Context context, String username, String password, String phone) {
        iBase.onLoading(0);
            FastHttp.SEND(HttpType.POST_JSON, context, new RegisterEnetity("{\"name\":\"zxdzxd\"}"), new OnSubscriberListener() {
            @Override
            public void onCompleted() {
                iBase.onLoaded();
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String content) {
                iBase.onSuccess(content);
            }

            @Override
            public void onFault(String s) {
                iBase.onFailed(s);
            }
        });
    }
}
