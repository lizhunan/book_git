package com.sdjy.book.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.orhanobut.logger.Logger;
import com.sdjy.book.R;
import com.sdjy.book.app.BaseActivity;
import com.sdjy.book.view.fragment.MapFragment;
import com.sdjy.book.view.fragment.ScanningFragment;
import com.sdjy.book.view.fragment.UserFragment;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigation;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private MapFragment mapFragment;
    private ScanningFragment scanningFragment;
    private UserFragment userFragment;
    private Toolbar toolbar;
    private TextView titleTv;

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
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        mapFragment = MapFragment.newInstance();
        fragmentTransaction.replace(R.id.content, mapFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void doBusiness(Context mContext) {

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
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (item.getItemId()) {
            case R.id.navigation_map:
                titleTv.setText(getResources().getString(R.string.title_map));
                mapFragment = MapFragment.newInstance();
                fragmentTransaction.replace(R.id.content, mapFragment);
                fragmentTransaction.commit();
                return true;
            case R.id.navigation_scanning:
                titleTv.setText(getResources().getString(R.string.title_scanning));
                scanningFragment = ScanningFragment.newInstance();
                fragmentTransaction.replace(R.id.content, scanningFragment);
                fragmentTransaction.commit();
                return true;
            case R.id.navigation_account:
                titleTv.setText(getResources().getString(R.string.title_account));
                userFragment = UserFragment.newInstance();
                fragmentTransaction.replace(R.id.content, userFragment);
                fragmentTransaction.commit();
                return true;
        }
        return false;
    }
}
