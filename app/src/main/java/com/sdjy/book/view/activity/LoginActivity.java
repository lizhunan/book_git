package com.sdjy.book.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sdjy.book.R;
import com.sdjy.book.app.BaseActivity;
import com.sdjy.book.app.BookApplication;
import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.entity.User;
import com.sdjy.book.mvp.presenter.impl.LoginPresenter;
import com.sdjy.book.view.IRefresh;

public class LoginActivity extends BaseActivity implements IRefresh<User> {

    private EditText usernameEt;
    private EditText passwordEt;
    private TextView forgetPwdTv;
    private Button loginBtn;
    private ProgressBar progressBar;
    private ConstraintLayout content;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private LoginPresenter loginPresenter = new LoginPresenter(this);

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
        progressBar = $(R.id.progressBar);
        content = $(R.id.content);
    }

    @Override
    protected void doBusiness(Context mContext) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BookApplication.getContext());
        editor = sharedPreferences.edit();
    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.forget_pwd_tv:
                break;
            case R.id.login_btn:
                loginPresenter.login(LoginActivity.this, usernameEt.getText().toString(), passwordEt.getText().toString());
                break;
        }
    }

    @Override
    protected void setListener() {
        forgetPwdTv.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onSuccess(User user) {
        editor.putString(Constant.USERNAME, user.getName());
        editor.putString(Constant.PASSWORD, user.getPswd());
        editor.putString(Constant.PHONE, user.getPhone());
        editor.putString(Constant.QQ, user.getQqid());
        editor.putString(Constant.WX, user.getWxid());
        editor.putString(Constant.TOKEN, user.getToken());
        editor.commit();
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void onFiled(String s) {
        Snackbar.make(content,s,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onLoading(int process) {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaded() {
        progressBar.setVisibility(View.GONE);
    }
}
