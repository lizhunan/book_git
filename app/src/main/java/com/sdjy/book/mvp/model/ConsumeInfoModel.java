package com.sdjy.book.mvp.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.sdjy.book.R;
import com.sdjy.book.mvp.entity.ConsumeInfo;
import com.sdjy.book.mvp.entity.net.ConsumeEnetity;
import com.sdjy.book.mvp.http.FastHttp;
import com.sdjy.book.mvp.http.OnSubscriberListener;
import com.sdjy.book.mvp.http.base.HttpType;
import com.sdjy.book.mvp.http.base.ResponseHttp;
import com.sdjy.book.mvp.presenter.IBase;
import com.sdjy.book.util.JsonParseUtil;

import java.lang.reflect.Type;

/**
 * Created by 李竹楠 on 2018/6/3.
 * 换购记录
 */

public class ConsumeInfoModel {

    public void consumeInfo(final IBase<ResponseHttp<ConsumeInfo>> iBase, final Context context, String json) {
        iBase.onLoading(0);
        FastHttp.SEND(HttpType.POST, context, new ConsumeEnetity(json), new OnSubscriberListener() {
            @Override
            public void onCompleted() {
                iBase.onLoaded();
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Object o) {
                Log.d("ConsumeInfoModel", "o;" + o.toString());
                Type type = new TypeToken<ResponseHttp<ConsumeInfo>>() {
                }.getType();
                ResponseHttp<ConsumeInfo> responseHttp = JsonParseUtil.getGson().fromJson(o.toString(), type);
                Log.d("booksinfo", "s1;" + responseHttp.getLogMsg());
                Log.d("booksinfo", "s2;" + responseHttp.isOk());
                Log.d("booksinfo", "s3;" + responseHttp.getResultData());
                iBase.onSuccess(responseHttp);
            }

            @Override
            public void onFault(String s) {
                iBase.onFailed(context.getString(R.string.net_error));
            }
        });
    }
}
