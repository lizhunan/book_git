package com.sdjy.book.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sdjy.book.R;
import com.sdjy.book.app.BaseActivity;
import com.sdjy.book.app.BookApplication;
import com.sdjy.book.app.Constant;

public class SplashActivity extends BaseActivity {

    private SharedPreferences settingsPreference;
    private SharedPreferences.Editor editor;

    @Override
    protected void initParms(Bundle parms) {

    }

    @Override
    protected int bindLayout() {
        setSetStatusBar(true);
        setmAllowFullScreen(true);
        return R.layout.activity_splash;
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void initView(View view) {
        settingsPreference = PreferenceManager.getDefaultSharedPreferences(BookApplication.getContext());
        editor = settingsPreference.edit();
    }

    @Override
    protected void doBusiness(Context mContext) {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String token = settingsPreference.getString(Constant.TOKEN, "");
        if (token.equals("")) {
            startActivity(LoginByPhoneActivity.class);
        } else {
            startActivity(MainActivity.class);
        }
        finish();
    }

    @Override
    protected void widgetClick(View view) {

    }

    @Override
    protected void setListener() {

    }
}
