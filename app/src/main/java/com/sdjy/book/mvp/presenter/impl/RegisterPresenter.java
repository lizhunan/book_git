package com.sdjy.book.mvp.presenter.impl;

import android.content.Context;

import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.entity.User;
import com.sdjy.book.mvp.http.base.ResponseHttp;
import com.sdjy.book.mvp.model.RegisterModel;
import com.sdjy.book.mvp.presenter.IBase;
import com.sdjy.book.view.IRefresh;

/**
 * Created by 李竹楠 on 2018/3/16.
 * 用户注册presenter
 */

public class RegisterPresenter implements IBase<ResponseHttp<User>> {

    private RegisterModel registerModel;
    private IRefresh<User> iRefresh;

    public RegisterPresenter(IRefresh<User> iRefresh) {
        this.iRefresh = iRefresh;
        this.registerModel = new RegisterModel();
    }

    public void regist(Context context, String username, String password, String phone) {
        registerModel.regist(this, context, username, password, phone);
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
    public void onSuccess(ResponseHttp<User> s) {
        switch (s.getStatus()) {
            case Constant.OK_CODE:
                iRefresh.onSuccess(s.getData());
                break;
            case Constant.CLIENT_ERR_CODE:
                iRefresh.onFiled(s.getDesc());
                break;
            case Constant.SERVER_ERR_CODE:
                iRefresh.onFiled(s.getDesc());
                break;
        }
    }

    @Override
    public void onFailed(String failed) {

    }
}
