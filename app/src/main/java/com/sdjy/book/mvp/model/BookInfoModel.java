package com.sdjy.book.mvp.model;

import android.content.Context;

import com.sdjy.book.R;
import com.sdjy.book.mvp.entity.BookInfo;
import com.sdjy.book.mvp.entity.net.BookInfoEnetity;
import com.sdjy.book.mvp.http.FastHttp;
import com.sdjy.book.mvp.http.OnSubscriberListener;
import com.sdjy.book.mvp.http.base.HttpType;
import com.sdjy.book.mvp.http.base.ResponseHttp;
import com.sdjy.book.mvp.presenter.IBase;

/**
 * Created by 李竹楠 on 2018/5/9.
 * 返回书籍详细信息model
 */

public class BookInfoModel {

    public void getInfo(final IBase<ResponseHttp<BookInfo>> iBase, final Context context,String json){
        iBase.onLoading(0);
        FastHttp.SEND(HttpType.POST, context, new BookInfoEnetity(json), new OnSubscriberListener() {
            @Override
            public void onCompleted() {
                iBase.onLoaded();
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onFault(String s) {
                iBase.onFailed(context.getString(R.string.net_error));
                iBase.onLoaded();
            }
        });
    }
}
