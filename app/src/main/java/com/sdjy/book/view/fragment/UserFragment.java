package com.sdjy.book.view.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdjy.book.R;
import com.sdjy.book.app.BaseFragment;
import com.sdjy.book.view.activity.SettingsActivity;

public class UserFragment extends BaseFragment {

    @SuppressLint("StaticFieldLeak")
    private static UserFragment INSTANCE;

    private ImageView commContriIv;
    private ImageView settingIv;
    private ImageView profileIv;
    private RecyclerView recyclerView;
    private TextView nameTv;
    private TextView infoTv;

    public UserFragment() {

    }

    public static UserFragment newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserFragment();
        }
        return INSTANCE;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView(View view) {
        commContriIv = $(view, R.id.comm_contri_iv);
        settingIv = $(view, R.id.setting_iv);
        profileIv = $(view, R.id.profile_civ);
        recyclerView = $(view, R.id.list_view);
        nameTv = $(view, R.id.name_tv);
        infoTv = $(view, R.id.info_tv);
    }

    @Override
    protected void doBusiness(Context mContext, Activity activity) {

    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.comm_contri_iv:
                break;
            case R.id.setting_iv:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case R.id.profile_civ:
                break;
        }
    }

    @Override
    protected void setListener() {
        commContriIv.setOnClickListener(this);
        settingIv.setOnClickListener(this);
        profileIv.setOnClickListener(this);
    }
}
