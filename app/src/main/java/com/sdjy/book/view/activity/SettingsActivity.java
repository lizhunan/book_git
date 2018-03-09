package com.sdjy.book.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sdjy.book.R;
import com.sdjy.book.app.BaseActivity;
import com.sdjy.book.view.fragment.SettingsFragment;

public class SettingsActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView titleTv;

    @Override
    protected void initParms(Bundle parms) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_settings;
    }

    @Override
    protected void initView(View view) {
        toolbar = $(R.id.toolbar);
        titleTv = $(R.id.title_tv);
        /*
        * 设置toolbar
        * */
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("");
        titleTv.setText(getResources().getString(R.string.setting));
        setSupportActionBar(toolbar);
        PreferenceManager.setDefaultValues(this, R.xml.pref_settings, false);
        getFragmentManager().beginTransaction()
                .replace(R.id.content, new SettingsFragment())
                .commit();
    }

    @Override
    protected void doBusiness(Context mContext) {

    }

    @Override
    protected void widgetClick(View view) {

    }

    @Override
    protected void setListener() {

    }

}
