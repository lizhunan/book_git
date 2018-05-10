package com.sdjy.book.mvp.entity;

import java.util.List;

/**
 * Created by 李竹楠 on 2018/5/10.
 * 所有书籍列表实体
 */

public class BooksInfo {


    private List<BooksArrayBean> booksArray;

    public BooksInfo() {
    }

    public List<BooksArrayBean> getBooksArray() {
        return booksArray;
    }

    public void setBooksArray(List<BooksArrayBean> booksArray) {
        this.booksArray = booksArray;
    }

    public static class BooksArrayBean {

        private String bookNo;
        private String athor;
        private int price;
        private String bookName;
        private String publishing;
        private String image;

        public BooksArrayBean() {
        }

        public BooksArrayBean(String bookNo, String athor, int price, String bookName, String publishing, String image) {
            this.bookNo = bookNo;
            this.athor = athor;
            this.price = price;
            this.bookName = bookName;
            this.publishing = publishing;
            this.image = image;
        }

        public BooksArrayBean(String bookNo, String athor, int price, String bookName, String publishing) {
            this.bookNo = bookNo;
            this.athor = athor;
            this.price = price;
            this.bookName = bookName;
            this.publishing = publishing;
        }

        public String getBookNo() {
            return bookNo;
        }

        public void setBookNo(String bookNo) {
            this.bookNo = bookNo;
        }

        public String getAthor() {
            return athor;
        }

        public void setAthor(String athor) {
            this.athor = athor;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
