package com.sdjy.book.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sdjy.book.R;
import com.sdjy.book.adapter.ContributeInfoAdapter;
import com.sdjy.book.app.BaseActivity;
import com.sdjy.book.app.BookApplication;
import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.entity.ContributeInfo;
import com.sdjy.book.mvp.http.base.ResponseHttp;
import com.sdjy.book.mvp.presenter.impl.ContributePresenter;
import com.sdjy.book.view.IRefresh;

import java.util.ArrayList;
import java.util.List;

public class ContributeActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        IRefresh<ContributeInfo>, ContributeInfoAdapter.OnItemClickListener {

    private Toolbar toolbar;
    private TextView titleTv;
    private RecyclerView booksListRv;
    private SwipeRefreshLayout booksSrl;
    private SharedPreferences sharedPreferences;
    private ContributeInfoAdapter contributeInfoAdapter;
    private List<ContributeInfo.BooksArrayBean> booksArrayBeans = new ArrayList<>();
    private ContributePresenter contributePresenter = new ContributePresenter(this);

    @Override
    protected void initParms(Bundle parms) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_contribute;
    }

    @Override
    protected void initView(View view) {
        toolbar = $(R.id.toolbar);
        titleTv = $(R.id.title_tv);
        booksListRv = $(R.id.books_list_rv);
        booksSrl = $(R.id.books_srl);
        /*
        * 设置toolbar
        * */
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("");
        titleTv.setText(getResources().getString(R.string.community_contri));
        setSupportActionBar(toolbar);
        booksListRv.setLayoutManager(new LinearLayoutManager(this));
        booksSrl.setColorSchemeColors(Color.RED, Color.BLUE);
        contributeInfoAdapter = new ContributeInfoAdapter(this, booksArrayBeans);
    }

    @Override
    protected void doBusiness(Context mContext) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BookApplication.getContext());
        booksListRv.setAdapter(contributeInfoAdapter);
        contributePresenter.contributeInfo(this, sharedPreferences.getString(Constant.TOKEN, ""), 1, 20);
    }

    @Override
    protected void widgetClick(View view) {

    }

    @Override
    protected void setListener() {
        contributeInfoAdapter.setOnItemClickListener(this);
        booksSrl.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        contributePresenter.contributeInfo(this, sharedPreferences.getString(Constant.TOKEN, ""), 1, 20);
    }

    @Override
    public void onSuccess(ContributeInfo o) {
        contributeInfoAdapter.updateListView(o.getBooksArray());
    }

    @Override
    public void onFiled(String s) {
        booksSrl.setRefreshing(false);
    }

    @Override
    public void onLoading(int process) {

    }

    @Override
    public void onLoaded() {
        booksSrl.setRefreshing(false);
    }

    @Override
    public void OnItemClickListener(View view, int position) {

    }
}
