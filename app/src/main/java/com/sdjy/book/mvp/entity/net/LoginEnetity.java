package com.sdjy.book.mvp.entity.net;

import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.http.base.BaseEnetity;

/**
 * Created by 李竹楠 on 2018/3/16.
 * 普通方式登录
 */

public class LoginEnetity extends BaseEnetity {

    private String json;

    public LoginEnetity() {
    }

    public LoginEnetity(String json) {
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
        return Constant.LOGIN_;
    }
}
