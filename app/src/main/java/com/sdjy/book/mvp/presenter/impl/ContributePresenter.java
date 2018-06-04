package com.sdjy.book.mvp.presenter.impl;

import android.content.Context;

import com.sdjy.book.mvp.entity.ContributeInfo;
import com.sdjy.book.mvp.http.base.ResponseHttp;
import com.sdjy.book.mvp.model.ContributeModel;
import com.sdjy.book.mvp.presenter.IBase;
import com.sdjy.book.view.IRefresh;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by 李竹楠 on 2018/6/4.
 * 贡献记录
 */

public class ContributePresenter implements IBase<ResponseHttp<ContributeInfo>> {

    private ContributeModel contributeModel;
    private IRefresh<ContributeInfo> iRefresh;

    public ContributePresenter(IRefresh<ContributeInfo> iRefresh) {
        this.iRefresh = iRefresh;
        this.contributeModel = new ContributeModel();
    }

    public void contributeInfo(Context context, String actionSession, int currPage, int rowPage){
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
        contributeModel.contributeInfo(this, context, json);
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
    public void onSuccess(ResponseHttp<ContributeInfo> responseHttp) {
        iRefresh.onSuccess(responseHttp.getResultData());
    }

    @Override
    public void onFailed(String failed) {
        iRefresh.onFiled(failed);
    }
}
