package com.sdjy.book.mvp.presenter.impl;

import android.content.Context;

import com.sdjy.book.mvp.model.RegisterModel;
import com.sdjy.book.mvp.presenter.IBase;
import com.sdjy.book.view.IRefresh;

/**
 * Created by 李竹楠 on 2018/3/16.
 * 用户注册presenter
 */

public class RegisterPresenter implements IBase<String> {

    private RegisterModel registerModel;
    private IRefresh<String> iRefresh;

    public RegisterPresenter(IRefresh<String> iRefresh) {
        this.iRefresh = iRefresh;
        this.registerModel = new RegisterModel();
    }

    public void regist(Context context, String username, String password, String phone) {
        registerModel.regist(this, context, username, password, phone);
    }

    @Override
    public void onLoading(int process) {

    }

    @Override
    public void onLoaded() {

    }

    @Override
    public void onSuccess(String s) {
        //判断注册状态
    }

    @Override
    public void onFailed(String failed) {

    }
}
