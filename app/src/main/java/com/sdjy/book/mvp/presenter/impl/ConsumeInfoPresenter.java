package com.sdjy.book.mvp.presenter.impl;

import android.content.Context;

import com.sdjy.book.mvp.entity.ConsumeInfo;
import com.sdjy.book.mvp.http.base.ResponseHttp;
import com.sdjy.book.mvp.model.ConsumeInfoModel;
import com.sdjy.book.mvp.presenter.IBase;
import com.sdjy.book.view.IRefresh;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by 李竹楠 on 2018/6/3.
 * 换购记录
 */

public class ConsumeInfoPresenter implements IBase<ResponseHttp<ConsumeInfo>> {

    private ConsumeInfoModel consumeInfoModel;
    private IRefresh<ConsumeInfo> infoIRefresh;

    public ConsumeInfoPresenter(IRefresh<ConsumeInfo> infoIRefresh) {
        this.infoIRefresh = infoIRefresh;
        this.consumeInfoModel = new ConsumeInfoModel();
    }

    public void consume(Context context, String actionSession, int currPage, int rowPage) {
        String json = "";
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("currPage", currPage);
            jsonObject.put("rowPage", rowPage);
            jsonObject.put("actionSession", actionSession);
            json = URLEncoder.encode(jsonObject.toString(), "UTF-8");
        } catch (UnsupportedEncodingException | JSONException e) {
            e.printStackTrace();
        }
        consumeInfoModel.consumeInfo(this, context, json);
    }

    @Override
    public void onLoading(int process) {
        infoIRefresh.onLoading(process);
    }

    @Override
    public void onLoaded() {
        infoIRefresh.onLoaded();
    }

    @Override
    public void onSuccess(ResponseHttp<ConsumeInfo> consumeInfo) {
        infoIRefresh.onSuccess(consumeInfo.getResultData());
    }

    @Override
    public void onFailed(String failed) {
        infoIRefresh.onFiled(failed);
    }
}
