package com.sdjy.book.view.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdjy.book.R;
import com.sdjy.book.adapter.ConsumeInfoAdapter;
import com.sdjy.book.app.BaseFragment;
import com.sdjy.book.app.BookApplication;
import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.entity.ConsumeInfo;
import com.sdjy.book.mvp.http.FastHttp;
import com.sdjy.book.mvp.http.OnSubscriberListener;
import com.sdjy.book.mvp.http.base.HttpType;
import com.sdjy.book.mvp.presenter.impl.ConsumeInfoPresenter;
import com.sdjy.book.view.IRefresh;
import com.sdjy.book.view.activity.BookChangeActivity;
import com.sdjy.book.view.activity.ContributeActivity;
import com.sdjy.book.view.activity.LoginByPhoneActivity;
import com.sdjy.book.view.activity.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends BaseFragment implements IRefresh<ConsumeInfo>,
        SwipeRefreshLayout.OnRefreshListener, ConsumeInfoAdapter.OnItemClickListener {

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
    private SwipeRefreshLayout consumeInfoSr;
    private String figureurl = "";
    private SharedPreferences sharedPreferences;
    private ConsumeInfoAdapter consumeInfoAdapter;
    private ConsumeInfoPresenter consumeInfoPresenter = new ConsumeInfoPresenter(this);
    private List<ConsumeInfo.ExchangeArrayBean> exchangeArrayBeanList = new ArrayList<>();

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
        consumeInfoSr = $(view, R.id.consumeinfo_srl);

        consumeInfoSr.setColorSchemeColors(Color.RED, Color.BLUE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        consumeInfoAdapter = new ConsumeInfoAdapter(getContext(), exchangeArrayBeanList);
    }

    @Override
    protected void doBusiness(Context mContext, Activity activity) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BookApplication.getContext());
        figureurl = sharedPreferences.getString(Constant.PROFILE, "");
        recyclerView.setAdapter(consumeInfoAdapter);
        consumeInfoPresenter.consume(getContext(), sharedPreferences.getString(Constant.TOKEN, ""), 1, 20);
        if (!figureurl.equals("")) {
            Uri parse = Uri.parse(figureurl);
            // profileIv.setImageBitmap(parse);
        }
    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.comm_contri_iv:
                startActivity(new Intent(getActivity(), ContributeActivity.class));
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
        consumeInfoSr.setOnRefreshListener(this);
        commContriIv.setOnClickListener(this);
        settingIv.setOnClickListener(this);
        profileIv.setOnClickListener(this);
        userInfoCl.setOnClickListener(this);
        booksChIv.setOnClickListener(this);
        consumeInfoAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onSuccess(ConsumeInfo consumeInfo) {
        consumeInfoAdapter.updateListView(consumeInfo.getExchangeArray());
    }

    @Override
    public void onFiled(String s) {
        consumeInfoSr.setRefreshing(false);
    }

    @Override
    public void onLoading(int process) {

    }

    @Override
    public void onLoaded() {
        consumeInfoSr.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        consumeInfoPresenter.consume(getContext(), sharedPreferences.getString(Constant.TOKEN, ""), 1, 20);
    }

    @Override
    public void OnItemClickListener(View view, int position) {

    }
}
