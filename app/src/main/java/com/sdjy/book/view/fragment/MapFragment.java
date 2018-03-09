package com.sdjy.book.view.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdjy.book.R;
import com.sdjy.book.app.BaseFragment;


public class MapFragment extends BaseFragment {

    @SuppressLint("StaticFieldLeak")
    private static MapFragment INSTANCE;

    public MapFragment() {

    }

    public static MapFragment newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MapFragment();
        }
        return INSTANCE;
    }


    @Override
    protected int bindLayout() {
        return R.layout.fragment_map;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void doBusiness(Context mContext, Activity activity) {

    }

    @Override
    protected void widgetClick(View view) {

    }

    @Override
    protected void setListener() {

    }
}
