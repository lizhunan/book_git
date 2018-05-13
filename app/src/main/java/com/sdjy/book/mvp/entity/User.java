package com.sdjy.book.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 李竹楠 on 2018/3/21.
 * 用户实体类
 */

public class User implements Parcelable {

    private int age;
    private int points;
    private String loginname;
    private String username;
    private String sex;
    private String userType;
    private String lastloginDate;
    private String actionSession;

    public User() {
    }

    public User(int age, int points, String loginname, String username, String sex,
                String userType, String lastloginDate, String actionSession) {
        this.age = age;
        this.points = points;
        this.loginname = loginname;
        this.username = username;
        this.sex = sex;
        this.userType = userType;
        this.lastloginDate = lastloginDate;
        this.actionSession = actionSession;
    }

    protected User(Parcel in) {
        age = in.readInt();
        points = in.readInt();
        loginname = in.readString();
        username = in.readString();
        sex = in.readString();
        userType = in.readString();
        lastloginDate = in.readString();
        actionSession = in.readString();
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLastloginDate() {
        return lastloginDate;
    }

    public void setLastloginDate(String lastloginDate) {
        this.lastloginDate = lastloginDate;
    }

    public String getActionSession() {
        return actionSession;
    }

    public void setActionSession(String actionSession) {
        this.actionSession = actionSession;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(age);
        parcel.writeInt(points);
        parcel.writeString(loginname);
        parcel.writeString(username);
        parcel.writeString(sex);
        parcel.writeString(userType);
        parcel.writeString(lastloginDate);
        parcel.writeString(actionSession);
    }
}
