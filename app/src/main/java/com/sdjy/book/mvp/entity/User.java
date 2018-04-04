package com.sdjy.book.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 李竹楠 on 2018/3/21.
 * 用户实体类
 */

public class User implements Parcelable{

    private int id;
    private int registertype;
    private String name;
    private String token;
    private String phone;
    private String wxid;
    private String qqid;
    private String pswd;

    protected User(Parcel in) {
        id = in.readInt();
        registertype = in.readInt();
        name = in.readString();
        token = in.readString();
        phone = in.readString();
        pswd = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRegistertype() {
        return registertype;
    }

    public void setRegistertype(int registertype) {
        this.registertype = registertype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    public String getQqid() {
        return qqid;
    }

    public void setQqid(String qqid) {
        this.qqid = qqid;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(registertype);
        parcel.writeString(name);
        parcel.writeString(token);
        parcel.writeString(phone);
        parcel.writeString(pswd);
    }
}
