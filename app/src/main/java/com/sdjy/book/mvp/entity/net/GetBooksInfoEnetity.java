package com.sdjy.book.mvp.entity.net;

import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.http.base.BaseEnetity;

/**
 * Created by 李竹楠 on 2018/5/9.
 * 读取所有书籍信息
 */

public class GetBooksInfoEnetity extends BaseEnetity {

    private String json;

    public GetBooksInfoEnetity() {
    }

    public GetBooksInfoEnetity(String json) {
        this.json = json;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String getRuqestURL() {
        return Constant.GET_BOOKS_INFO_;
    }
}
