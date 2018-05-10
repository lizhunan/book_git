package com.sdjy.book.mvp.entity.net;

import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.http.base.BaseEnetity;

/**
 * Created by 李竹楠 on 2018/4/20.
 * 扫码投放
 */

public class StartInputEnetity extends BaseEnetity {

    private String json;

    public StartInputEnetity() {
    }

    public StartInputEnetity(String json) {
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
        return Constant.START_INPUT_;
    }
}
