package com.sdjy.book.mvp.presenter.impl;

import android.content.Context;

import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.entity.User;
import com.sdjy.book.mvp.http.base.ResponseHttpBy;
import com.sdjy.book.mvp.model.LoginModel;
import com.sdjy.book.mvp.presenter.IBase;
import com.sdjy.book.view.IRefresh;

/**
 * Created by 李竹楠 on 2018/3/22.
 * 普通登录presenter
 */

public class LoginPresenter implements IBase<ResponseHttpBy<User>> {

    private LoginModel loginModel;
    private IRefresh<User> iRefresh;

    public LoginPresenter(IRefresh<User> iRefresh) {
        this.iRefresh = iRefresh;
        this.loginModel = new LoginModel();
    }

    public void login(Context context, String username, String password) {
        loginModel.login(this, context, username, password);
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
    public void onSuccess(ResponseHttpBy<User> userResponseHttp) {
        switch (userResponseHttp.getStatus()) {
            case Constant.OK_CODE:
                iRefresh.onSuccess(userResponseHttp.getData());
                break;
            case Constant.CLIENT_ERR_CODE:
                iRefresh.onFiled(userResponseHttp.getDesc());
                break;
            case Constant.SERVER_ERR_CODE:
                iRefresh.onFiled(userResponseHttp.getDesc());
                break;
        }
    }

    @Override
    public void onFailed(String failed) {
        iRefresh.onFiled(failed);
    }
}
