package com.sdjy.book.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.sdjy.book.R;
import com.sdjy.book.app.BaseActivity;
import com.sdjy.book.app.BookApplication;
import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.entity.User;
import com.sdjy.book.mvp.presenter.impl.RegisterPresenter;
import com.sdjy.book.view.IRefresh;

public class RegisterActivity extends BaseActivity implements IRefresh<User> {

    private EditText usernameEt;
    private EditText passwordEt;
    private EditText phoneEt;
    private Button registerBan;
    private ImageView profileIv;
    private ProgressBar progressBar;
    private ConstraintLayout content;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
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
    public void onSuccess(User user) {
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void onFiled(String s) {
        Snackbar.make(content, s, Snackbar.LENGTH_SHORT).show();
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
