package com.sdjy.book.mvp.entity.net;

import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.http.base.BaseEnetity;

/**
 * Created by 李竹楠 on 2018/3/16.
 * 普通方式登录
 */

public class LoginEnetity extends BaseEnetity {

    private String name;
    private String pswd;

    public LoginEnetity() {
    }

    public LoginEnetity(String name, String pswd) {
        this.name = name;
        this.pswd = pswd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    @Override
    public String getRuqestURL() {
        return Constant.LOGIN_;
    }
}
