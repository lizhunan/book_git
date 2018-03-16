package com.sdjy.book.view.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sdjy.book.R;
import com.sdjy.book.app.BaseActivity;

public class LoginActivity extends BaseActivity {

    private EditText usernameEt;
    private EditText passwordEt;
    private TextView forgetPwdTv;
    private Button loginBtn;

    @Override
    protected void initParms(Bundle parms) {

    }

    @Override
    protected int bindLayout() {
        setSetStatusBar(true);
        return R.layout.activity_login2;
    }

    @Override
    protected void initView(View view) {
        usernameEt = $(R.id.username_et);
        passwordEt = $(R.id.password_et);
        forgetPwdTv = $(R.id.forget_pwd_tv);
        loginBtn = $(R.id.login_btn);
    }

    @Override
    protected void doBusiness(Context mContext) {

    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.forget_pwd_tv:
                break;
            case R.id.login_btn:
                break;
        }
    }

    @Override
    protected void setListener() {
        forgetPwdTv.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }
}
