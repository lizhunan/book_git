package com.sdjy.book.mvp.entity.net;

import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.http.base.BaseEnetity;

/**
 * Created by 李竹楠 on 2018/3/16.
 * 注册请求实体
 */

public class RegisterEnetity extends BaseEnetity {

    private String username;
    private String pswd;
    private String phone;

    public RegisterEnetity() {

    }

    public RegisterEnetity(String username, String pswd, String phone) {
        this.username = username;
        this.pswd = pswd;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getRuqestURL() {
        return Constant.REGISTER_;
    }
}
