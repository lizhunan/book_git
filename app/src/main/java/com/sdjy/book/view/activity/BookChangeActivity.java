package com.sdjy.book.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sdjy.book.R;
import com.sdjy.book.adapter.BooksListAdapter;
import com.sdjy.book.app.BaseActivity;
import com.sdjy.book.mvp.entity.BooksInfo;
import com.sdjy.book.mvp.presenter.impl.BooksInfoPresenter;
import com.sdjy.book.view.IRefresh;

import java.util.ArrayList;
import java.util.List;

public class BookChangeActivity extends BaseActivity implements IRefresh<BooksInfo>,
        SwipeRefreshLayout.OnRefreshListener, BooksListAdapter.OnItemClickListener {

    private Toolbar toolbar;
    private TextView titleTv;
    private RecyclerView booksListRv;
    private BooksListAdapter booksListAdapter;
    private SwipeRefreshLayout booksSrl;
    private List<BooksInfo.BooksArrayBean> booksArrayBeans = new ArrayList<>();
    private BooksInfoPresenter booksInfoPresenter = new BooksInfoPresenter(this);

    @Override
    protected void initParms(Bundle parms) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_book_change;
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
        titleTv.setText(getResources().getString(R.string.book_change));
        setSupportActionBar(toolbar);
        booksListRv.setLayoutManager(new LinearLayoutManager(this));
        booksSrl.setColorSchemeColors(Color.RED, Color.BLUE);
        booksListAdapter = new BooksListAdapter(this, booksArrayBeans);
    }

    @Override
    protected void doBusiness(Context mContext) {
        booksListRv.setAdapter(booksListAdapter);
        booksInfoPresenter.getInfo(BookChangeActivity.this, "aaa", 1, 20);
    }

    @Override
    protected void widgetClick(View view) {

    }

    @Override
    protected void setListener() {
        booksSrl.setOnRefreshListener(this);
        booksListAdapter.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.order, menu);
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
    public void onSuccess(BooksInfo info) {
        booksListAdapter.updateListView(info.getBooksArray());
    }

    @Override
    public void onFiled(String s) {

    }

    @Override
    public void onLoading(int process) {

    }

    @Override
    public void onLoaded() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void OnItemClickListener(View view, int position) {

    }
}
