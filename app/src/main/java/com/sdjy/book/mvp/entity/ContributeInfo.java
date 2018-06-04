package com.sdjy.book.mvp.entity;

import java.util.List;

/**
 * Created by 李竹楠 on 2018/6/4.
 * 贡献记录
 */

public class ContributeInfo {

    private List<BooksArrayBean> booksArray;

    public List<BooksArrayBean> getBooksArray() {
        return booksArray;
    }

    public void setBooksArray(List<BooksArrayBean> booksArray) {
        this.booksArray = booksArray;
    }

    public static class BooksArrayBean {

        private String bookNo;
        private String image;
        private String athor;
        private String contributeDate;
        private double price;
        private String bookName;
        private String publishing;
        private String status;
        private String toWhere;

        public String getBookNo() {
            return bookNo;
        }

        public void setBookNo(String bookNo) {
            this.bookNo = bookNo;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAthor() {
            return athor;
        }

        public void setAthor(String athor) {
            this.athor = athor;
        }

        public String getContributeDate() {
            return contributeDate;
        }

        public void setContributeDate(String contributeDate) {
            this.contributeDate = contributeDate;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getPublishing() {
            return publishing;
        }

        public void setPublishing(String publishing) {
            this.publishing = publishing;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getToWhere() {
            return toWhere;
        }

        public void setToWhere(String toWhere) {
            this.toWhere = toWhere;
        }
    }
}
