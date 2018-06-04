package com.sdjy.book;

import com.google.gson.reflect.TypeToken;
import com.sdjy.book.mvp.entity.BooksInfo;
import com.sdjy.book.mvp.entity.User;
import com.sdjy.book.mvp.http.base.ResponseHttp;
import com.sdjy.book.mvp.http.base.ResponseHttpBy;
import com.sdjy.book.util.JsonParseUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        String s = "{\"resultData\":{\"booksArray\":[{\"bookNo\":\"b00001\",\"image\":\"\",\"athor\":\"夏冰冰\",\"price\":0.0,\"bookName\":\"Java入门基础\",\"publishing\":\"交通学院\",\"points\":1},{\"bookNo\":\"b00003\",\"image\":\"\",\"athor\":\"（美）Brian W. Kernighan,（美）Dennis M. Ritchie\",\"price\":30.0,\"bookName\":\"C程序设计语言\",\"publishing\":\"机械工业出版社\",\"points\":1},{\"bookNo\":\"b00006\",\"image\":\"\",\"athor\":\"\",\"price\":45.0,\"bookName\":\"测试\",\"publishing\":\"\",\"points\":1},{\"bookNo\":\"b00007\",\"image\":\"\",\"athor\":\"\",\"price\":45.0,\"bookName\":\"Orac1\",\"publishing\":\"\",\"points\":1},{\"bookNo\":\"b00008\",\"image\":\"\",\"athor\":\"\",\"price\":45.0,\"bookName\":\"2Orac1\",\"publishing\":\"\",\"points\":1},{\"bookNo\":\"b00009\",\"image\":\"b00009.jpg\",\"athor\":\"崔庆才\",\"price\":99.0,\"bookName\":\"Python 3网络爬虫开发实战\",\"publishing\":\"人民邮电出版社\",\"points\":1},{\"bookNo\":\"b000010\",\"image\":\"b00009.jpg\",\"athor\":\"崔庆才\",\"price\":99.0,\"bookName\":\"python 3网络爬虫开发实战\",\"publishing\":\"人民邮电出版社\",\"points\":1}]},\"isOk\":true,\"logMsg\":\"查询成功\"}";
        Type type = new TypeToken<ResponseHttp<BooksInfo>>() {
        }.getType();
        ResponseHttp<BooksInfo> dataHttp = JsonParseUtil.getGson().fromJson(s, type);
        System.out.println("s:" + dataHttp.getLogMsg());
        System.out.println("s:" + dataHttp.getResultData().getBooksArray().get(0).getBookName());
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonArray = jsonObject.getJSONObject("booksArray");
        System.out.println("s:" + jsonArray);
        for (int i = 0; i < jsonArray.length(); i++) {
           // System.out.println("s:" + jsonArray.get.getLogMsg());
        }
    }
}