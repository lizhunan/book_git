package com.sdjy.book.mvp.entity;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by 李竹楠 on 2018/5/17.
 * 用户动态
 */

public class UserDyn {

    private Bitmap image;
    private String info;

    public UserDyn() {
    }

    public UserDyn(Bitmap image, String info) {
        this.image = image;
        this.info = info;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
