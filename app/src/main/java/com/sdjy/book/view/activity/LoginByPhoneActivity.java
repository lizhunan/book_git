package com.sdjy.book.view.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdjy.book.R;
import com.sdjy.book.app.BaseActivity;

public class LoginByPhoneActivity extends BaseActivity {

    private EditText phoneEt;
    private EditText codeEt;
    private Button codeBtn;
    private Button loginBtn;
    private CheckBox isReadCb;
    private TextView registerTv;
    private TextView loginTv;
    private ImageView qqLoginIv;
    private ImageView wxLoginIv;

    @Override
    protected void initParms(Bundle parms) {

    }

    @Override
    protected int bindLayout() {
        setSetStatusBar(true);
        return R.layout.activity_login;
    }

    @Override
    protected void initView(View view) {
        phoneEt = $(R.id.phone_et);
        codeEt = $(R.id.code_et);
        codeBtn = $(R.id.code_btn);
        loginBtn = $(R.id.login_btn);
        isReadCb = $(R.id.isread_cb);
        registerTv = $(R.id.register_tv);
        loginTv = $(R.id.login_tv);
        qqLoginIv = $(R.id.qqlogin_iv);
        wxLoginIv = $(R.id.wxlogin_iv);
    }

    @Override
    protected void doBusiness(Context mContext) {

    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.code_btn:
                break;
            case R.id.login_btn:
                break;
            case R.id.register_tv:
                startActivity(RegisterActivity.class);
                break;
            case R.id.login_tv:
                startActivity(LoginActivity.class);
                break;
            case R.id.qqlogin_iv:
                break;
            case R.id.wxlogin_iv:
                break;
        }
    }

    @Override
    protected void setListener() {
        codeBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        registerTv.setOnClickListener(this);
        loginTv.setOnClickListener(this);
        qqLoginIv.setOnClickListener(this);
        wxLoginIv.setOnClickListener(this);
    }
}
