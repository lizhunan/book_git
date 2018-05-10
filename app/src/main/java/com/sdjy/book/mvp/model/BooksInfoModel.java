package com.sdjy.book.mvp.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.sdjy.book.R;
import com.sdjy.book.mvp.entity.BooksInfo;
import com.sdjy.book.mvp.entity.net.GetBooksInfoEnetity;
import com.sdjy.book.mvp.http.FastHttp;
import com.sdjy.book.mvp.http.OnSubscriberListener;
import com.sdjy.book.mvp.http.base.HttpType;
import com.sdjy.book.mvp.http.base.ResponseHttp;
import com.sdjy.book.mvp.presenter.IBase;
import com.sdjy.book.util.JsonParseUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 李竹楠 on 2018/5/9.
 * 返回所有书籍信息
 */

public class BooksInfoModel {

    public void getBooksInfo(final IBase<ResponseHttp<String>> iBase, final Context context, String json) {
        iBase.onLoading(0);
        FastHttp.SEND(HttpType.POST, context, new GetBooksInfoEnetity(json), new OnSubscriberListener() {
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
                Type type = new TypeToken<ResponseHttp<String>>() {
                }.getType();
                ResponseHttp<String> responseHttp = JsonParseUtil.getGson().fromJson(o.toString(), type);
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
