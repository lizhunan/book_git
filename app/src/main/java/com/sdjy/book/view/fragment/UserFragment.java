package com.sdjy.book.view.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdjy.book.R;
import com.sdjy.book.app.BaseFragment;
import com.sdjy.book.app.BookApplication;
import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.http.FastHttp;
import com.sdjy.book.mvp.http.OnSubscriberListener;
import com.sdjy.book.mvp.http.base.HttpType;
import com.sdjy.book.view.activity.BookChangeActivity;
import com.sdjy.book.view.activity.LoginByPhoneActivity;
import com.sdjy.book.view.activity.SettingsActivity;

public class UserFragment extends BaseFragment {

    @SuppressLint("StaticFieldLeak")
    private static UserFragment INSTANCE;

    private ImageView commContriIv;
    private ImageView settingIv;
    private ImageView profileIv;
    private ImageView booksChIv;
    private RecyclerView recyclerView;
    private TextView nameTv;
    private TextView infoTv;
    private ConstraintLayout userInfoCl;
    private String figureurl = "";
    private SharedPreferences sharedPreferences;

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
        booksChIv = $(view, R.id.book_change_iv);
        recyclerView = $(view, R.id.list_view);
        nameTv = $(view, R.id.name_tv);
        infoTv = $(view, R.id.info_tv);
        userInfoCl = $(view, R.id.userinfo_cl);
    }

    @Override
    protected void doBusiness(Context mContext, Activity activity) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BookApplication.getContext());
        figureurl = sharedPreferences.getString(Constant.PROFILE,"");
        if (!figureurl.equals("")) {
            Uri parse = Uri.parse(figureurl);
           // profileIv.setImageBitmap(parse);
        }
    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.comm_contri_iv:
                startActivity(new Intent(getActivity(), LoginByPhoneActivity.class));
                break;
            case R.id.setting_iv:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case R.id.profile_civ:
                break;
            case R.id.userinfo_cl:
                break;
            case R.id.book_change_iv:
                startActivity(new Intent(getActivity(), BookChangeActivity.class));
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
         /*
        * 在这里进行更新数据
        * fragment显示时刷新数据
        * */
        if (hidden) {
            //fragment隐藏时调用
        } else {
            //fragment显示时调用
        }
    }

    @Override
    protected void setListener() {
        commContriIv.setOnClickListener(this);
        settingIv.setOnClickListener(this);
        profileIv.setOnClickListener(this);
        userInfoCl.setOnClickListener(this);
        booksChIv.setOnClickListener(this);
    }
}
