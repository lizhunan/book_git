package com.sdjy.book.mvp.entity.net;

import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.http.base.BaseEnetity;

/**
 * Created by 李竹楠 on 2018/5/9.
 * 返回单个书籍详细信息接口
 */

public class BookInfoEnetity extends BaseEnetity {

    private String json;

    public BookInfoEnetity() {
    }

    public BookInfoEnetity(String json) {
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
        return Constant.BOOK_INFO_;
    }
}
