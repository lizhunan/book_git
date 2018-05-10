package com.sdjy.book.mvp.presenter.impl;

import android.content.Context;

import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.http.base.ResponseHttp;
import com.sdjy.book.mvp.http.base.ResponseHttpBy;
import com.sdjy.book.mvp.model.StartInputModel;
import com.sdjy.book.mvp.presenter.IBase;
import com.sdjy.book.view.IRefresh;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by 李竹楠 on 2018/4/20.
 * 开始投递书籍
 */

public class StartInputPresenter implements IBase<ResponseHttp<Boolean>> {

    private StartInputModel startInputModel;
    private IRefresh<Boolean> iRefresh;

    public StartInputPresenter(IRefresh<Boolean> iRefresh) {
        this.iRefresh = iRefresh;
        this.startInputModel = new StartInputModel();
    }

    public void start(Context context, String boxBarcode, String actionSession) {
        String json = "";
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("boxBarcode", boxBarcode);
            jsonObject.put("actionSession", actionSession);
            json = URLEncoder.encode(jsonObject.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        startInputModel.start(this, context, json);
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
    public void onSuccess(ResponseHttp<Boolean> booResponseHttp) {
        if (booResponseHttp.isOk()) {
            iRefresh.onSuccess(booResponseHttp.isOk());
        } else {
            onFailed(booResponseHttp.getLogMsg());
        }
    }


    @Override
    public void onFailed(String failed) {
        iRefresh.onFiled(failed);
    }
}
