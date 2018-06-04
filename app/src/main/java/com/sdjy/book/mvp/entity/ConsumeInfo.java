package com.sdjy.book.mvp.entity;

import java.util.List;

/**
 * Created by 李竹楠 on 2018/5/17.
 * 用户动态
 */

public class ConsumeInfo {


    private List<ExchangeArrayBean> exchangeArray;

    public List<ExchangeArrayBean> getExchangeArray() {
        return exchangeArray;
    }

    public void setExchangeArray(List<ExchangeArrayBean> exchangeArray) {
        this.exchangeArray = exchangeArray;
    }

    public static class ExchangeArrayBean {

        private String exchangeId;
        private String createTime;
        private int points;

        public String getExchangeId() {
            return exchangeId;
        }

        public void setExchangeId(String exchangeId) {
            this.exchangeId = exchangeId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }
    }
}
