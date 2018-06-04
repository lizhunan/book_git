package com.sdjy.book.mvp.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.sdjy.book.mvp.entity.BooksInfo;
import com.sdjy.book.mvp.http.base.ResponseHttp;
import com.sdjy.book.mvp.model.BooksInfoModel;
import com.sdjy.book.mvp.presenter.IBase;
import com.sdjy.book.util.JsonParseUtil;
import com.sdjy.book.view.IRefresh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李竹楠 on 2018/5/9.
 * 获取所有书籍列表
 */

public class BooksInfoPresenter implements IBase<ResponseHttp<BooksInfo>> {

    private BooksInfoModel booksInfoModel;
    private IRefresh<BooksInfo> iRefresh;

    public BooksInfoPresenter(IRefresh<BooksInfo> iRefresh) {
        this.iRefresh = iRefresh;
        this.booksInfoModel = new BooksInfoModel();
    }

    public void getInfo(Context context, String actionSession, int currPage, int rowPage) {
        String json = "";
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("currPage", currPage);
            jsonObject.put("rowPage", rowPage);
            jsonObject.put("actionSession", actionSession);
            json = URLEncoder.encode(jsonObject.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        booksInfoModel.getBooksInfo(this, context, json);
    }

    @Override
    public void onLoading(int process) {
        iRefresh.onLoading(process);
    }

    @Override
    public void onLoaded() {
        iRefresh.onLoaded();
    }

    @Override
    public void onSuccess(ResponseHttp<BooksInfo> booksInfoResponseHttp) {
        //新方法
        iRefresh.onSuccess(booksInfoResponseHttp.getResultData());
        //旧方法
        /* BooksInfo booksInfo = new BooksInfo();
        List<BooksInfo.BooksArrayBean> beans = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(booksInfoResponseHttp.getResultData().toString());
            String booksJson = jsonObject.getString("booksArray");
            JSONArray jsonArray = new JSONArray(booksJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject book = jsonArray.getJSONObject(i);
                beans.add(new BooksInfo.BooksArrayBean(book.getString("bookNo"), book.getString("athor"),
                        book.getInt("price"), book.getString("bookName"), book.getString("publishing")));
            }
            booksInfo.setBooksArray(beans);
            iRefresh.onSuccess(booksInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void onFailed(String failed) {
        iRefresh.onFiled(failed);
    }
}
