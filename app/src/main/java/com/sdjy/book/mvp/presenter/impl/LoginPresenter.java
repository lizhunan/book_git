package com.sdjy.book.mvp.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.entity.User;
import com.sdjy.book.mvp.http.base.ResponseHttp;
import com.sdjy.book.mvp.http.base.ResponseHttpBy;
import com.sdjy.book.mvp.model.LoginModel;
import com.sdjy.book.mvp.presenter.IBase;
import com.sdjy.book.view.IRefresh;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by 李竹楠 on 2018/3/22.
 * 普通登录presenter
 */

public class LoginPresenter implements IBase<ResponseHttp<User>> {

    private LoginModel loginModel;
    private IRefresh<User> iRefresh;

    public LoginPresenter(IRefresh<User> iRefresh) {
        this.iRefresh = iRefresh;
        this.loginModel = new LoginModel();
    }

    public void login(Context context, String loginName, String username, String password, String loginType,
                      String weiXinToken, String qqToken) {
        JSONObject jsonObject = new JSONObject();
        String json = "";
        try {
            jsonObject.put("loginName", loginName);
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            jsonObject.put("loginType", loginType);
            jsonObject.put("weiXinToken", weiXinToken);
            jsonObject.put("qqToken", qqToken);
            Log.d("loginJson","json:"+jsonObject.toString());
            json = URLEncoder.encode(jsonObject.toString(), "UTF-8");
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        loginModel.login(this, context, json);
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
    public void onSuccess(ResponseHttp<User> userResponseHttp) {
        if(userResponseHttp.isOk()){
            iRefresh.onSuccess(userResponseHttp.getResultData());
        }else{
            iRefresh.onFiled(userResponseHttp.getLogMsg());
        }
    }

    @Override
    public void onFailed(String failed) {
        iRefresh.onFiled(failed);
    }
}
