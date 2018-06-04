package com.sdjy.book.mvp.entity.net;

import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.http.base.BaseEnetity;

/**
 * Created by 李竹楠 on 2018/6/4.
 * 贡献记录接口
 */

public class ContributeEnetitiy extends BaseEnetity {
    private String json;

    public ContributeEnetitiy() {
    }

    public ContributeEnetitiy(String json) {
        this.json = json;
    }

    @Override
    public String getRuqestURL() {
        return Constant.CONTRIBUTE_INFO_;
    }
}
