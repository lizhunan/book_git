package com.sdjy.book.mvp.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.sdjy.book.R;
import com.sdjy.book.mvp.entity.net.StartInputEnetity;
import com.sdjy.book.mvp.http.FastHttp;
import com.sdjy.book.mvp.http.OnSubscriberListener;
import com.sdjy.book.mvp.http.base.HttpType;
import com.sdjy.book.mvp.http.base.ResponseHttpBy;
import com.sdjy.book.mvp.http.base.ResponseHttp;
import com.sdjy.book.mvp.presenter.IBase;
import com.sdjy.book.util.JsonParseUtil;

import java.lang.reflect.Type;

/**
 * Created by 李竹楠 on 2018/4/20.
 * 开始投递书籍
 */

public class StartInputModel {

    public void start(final IBase<ResponseHttp<Boolean>> iBase, final Context context, String json) {
        iBase.onLoading(0);
        FastHttp.SEND(HttpType.POST, context, new StartInputEnetity(json), new OnSubscriberListener() {
            @Override
            public void onCompleted() {
                iBase.onLoaded();
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Object o) {
                Log.d("StartMo", "o;" + o.toString());
                Type type = new TypeToken<ResponseHttp<Boolean>>() {
                }.getType();
                ResponseHttp<Boolean> responseHttp = JsonParseUtil.getGson().fromJson(o.toString(), type);
                Log.d("StartMo", "s1;" + responseHttp.getLogMsg());
                Log.d("StartMo", "s2;" + responseHttp.isOk());
                Log.d("StartMo", "s3;" + responseHttp.getResultData());
                iBase.onSuccess(responseHttp);
            }

            @Override
            public void onFault(String s) {
                iBase.onFailed(context.getString(R.string.net_error));
            }
        });
    }

}
