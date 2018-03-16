package com.sdjy.book.mvp.entity.net;

import com.sdjy.book.mvp.http.base.BaseEnetity;

/**
 * Created by 李竹楠 on 2018/3/16.
 * 普通方式登录
 */

public class Login extends BaseEnetity{

    private String username;
    private String password;

    public Login() {
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getRuqestURL() {
        return null;
    }
}
