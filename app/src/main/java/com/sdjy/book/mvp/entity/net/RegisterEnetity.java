package com.sdjy.book.mvp.entity.net;

import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.http.base.BaseEnetity;

/**
 * Created by 李竹楠 on 2018/3/16.
 * 注册请求实体
 */

public class RegisterEnetity extends BaseEnetity {

    private String param;

    public RegisterEnetity() {

    }

    public RegisterEnetity(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String getRuqestURL() {
        return Constant.REGISTER_;
    }
}
