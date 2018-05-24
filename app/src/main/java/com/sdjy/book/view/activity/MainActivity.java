package com.sdjy.book.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.sdjy.book.R;
import com.sdjy.book.app.BaseActivity;
import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.presenter.impl.StartInputPresenter;
import com.sdjy.book.view.IRefresh;
import com.sdjy.book.view.fragment.MapFragment;
import com.sdjy.book.view.fragment.ScanningFragment;
import com.sdjy.book.view.fragment.UserFragment;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener, IRefresh<Boolean> {

    private BottomNavigationView navigation;
    private MapFragment mapFragment;
    private ScanningFragment scanningFragment;
    private UserFragment userFragment;
    private Toolbar toolbar;
    private TextView titleTv;
    private StartInputPresenter startInputPresenter = new StartInputPresenter(this);

    @Override
    protected void initParms(Bundle parms) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(View view) {
        navigation = $(R.id.navigation);
        toolbar = $(R.id.toolbar);
        titleTv = $(R.id.title_tv);
        /*
        * 设置toolbar
        * */
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("");
        titleTv.setText(getResources().getString(R.string.title_map));
        setSupportActionBar(toolbar);
        /*
        * 打开默认的fragment
        * */
        mapFragment = MapFragment.newInstance();
        scanningFragment = ScanningFragment.newInstance();
        userFragment = UserFragment.newInstance();
        switchFragment(mapFragment, R.id.content).commit();
    }

    @Override
    protected void doBusiness(Context mContext) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d("doBussiness", "con::" + preferences.getBoolean(Constant.SWIPRENETMODE_PRE_KEY, false));
    }

    @Override
    protected void widgetClick(View view) {

    }

    @Override
    protected void setListener() {
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info_menu:
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 通过 onActivityResult的方法获取 扫描回来的值
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
            } else {
                String ScanResult = intentResult.getContents();
                startInputPresenter.start(this, ScanResult, "aaa");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_map:
                titleTv.setText(getResources().getString(R.string.title_map));
                switchFragment(mapFragment, R.id.content).commit();
                return true;
            case R.id.navigation_scanning:
                titleTv.setText(getResources().getString(R.string.title_scanning));
                switchFragment(scanningFragment, R.id.content).commit();
                return true;
            case R.id.navigation_account:
                titleTv.setText(getResources().getString(R.string.title_account));
                switchFragment(userFragment, R.id.content).commit();
                return true;
        }
        return false;
    }

    @Override
    public void onSuccess(Boolean boo) {
        scanningFragment.onFragmentListener(boo);
        Toast.makeText(MainActivity.this,getResources().getString(R.string.bin_success),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFiled(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoading(int process) {

    }

    @Override
    public void onLoaded() {

    }
}
