package com.sdjy.book.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.sdjy.book.R;
import com.sdjy.book.app.BaseActivity;
import com.sdjy.book.app.BookApplication;
import com.sdjy.book.mvp.entity.net.Login;
import com.sdjy.book.mvp.presenter.impl.RegisterPresenter;
import com.sdjy.book.view.IRefresh;

public class RegisterActivity extends BaseActivity implements IRefresh<String> {

    private EditText usernameEt;
    private EditText passwordEt;
    private EditText phoneEt;
    private Button registerBan;
    private ImageView profileIv;
    private RegisterPresenter registerPresenter = new RegisterPresenter(this);

    @Override
    protected void initParms(Bundle parms) {

    }

    @Override
    protected int bindLayout() {
        setSetStatusBar(true);
        return R.layout.activity_register;
    }

    @Override
    protected void initView(View view) {
        usernameEt = $(R.id.username_et);
        passwordEt = $(R.id.password_et);
        phoneEt = $(R.id.phone_et);
        registerBan = $(R.id.register_btn);
        profileIv = $(R.id.profile_civ);
    }

    @Override
    protected void doBusiness(Context mContext) {

    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.register_btn:
                registerPresenter.regist(BookApplication.getContext(), usernameEt.getText().toString(),
                        passwordEt.getText().toString(), phoneEt.getText().toString());
                break;
            case R.id.profile_civ:
                break;
        }
    }

    @Override
    protected void setListener() {
        registerBan.setOnClickListener(this);
        profileIv.setOnClickListener(this);
    }

    @Override
    public void onSuccess(String s) {
        Log.d("onsuccess", "ss:" + s);
    }

    @Override
    public void onFiled(String s) {

    }

    @Override
    public void onLoading(int process) {

    }

    @Override
    public void onLoaded() {

    }
}
