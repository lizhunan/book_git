package com.sdjy.book.mvp.entity.net;

import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.http.base.BaseEnetity;

/**
 * Created by 换购记录接口 on 2018/6/3.
 * 李竹楠
 */

public class ConsumeEnetity extends BaseEnetity{

    private String json;

    public ConsumeEnetity() {
    }

    public ConsumeEnetity(String json) {
        this.json = json;
    }

    @Override
    public String getRuqestURL() {
        return Constant.BUY_INFO_;
    }
}
