package com.sdjy.book.view.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.integration.android.IntentIntegrator;
import com.sdjy.book.R;
import com.sdjy.book.app.BaseFragment;
import com.sdjy.book.view.activity.ScanningActivity;

public class ScanningFragment extends BaseFragment {

    @SuppressLint("StaticFieldLeak")
    private static ScanningFragment INSTANCE;
    private FloatingActionButton scanningFab;
    private Activity activity;

    public ScanningFragment() {

    }

    public static ScanningFragment newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScanningFragment();
        }
        return INSTANCE;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_scanning;
    }

    @Override
    protected void initView(View view) {
        scanningFab = $(view, R.id.scanning_fab);
    }

    @Override
    protected void doBusiness(Context mContext, Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.scanning_fab:
                new IntentIntegrator(activity)
                        .setPrompt(getResources().getString(R.string.scanning_info))
                        .setOrientationLocked(false)
                        .setCaptureActivity(ScanningActivity.class) // 设置自定义的activity是CustomActivity
                        .initiateScan(); // 初始化扫描
                break;
        }
    }

    @Override
    protected void setListener() {
        scanningFab.setOnClickListener(this);
    }
}
