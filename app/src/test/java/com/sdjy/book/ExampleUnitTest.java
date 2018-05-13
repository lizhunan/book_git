package com.sdjy.book;

import com.google.gson.reflect.TypeToken;
import com.sdjy.book.mvp.entity.BooksInfo;
import com.sdjy.book.mvp.entity.User;
import com.sdjy.book.mvp.http.base.ResponseHttp;
import com.sdjy.book.mvp.http.base.ResponseHttpBy;
import com.sdjy.book.util.JsonParseUtil;

import org.json.JSONObject;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        // String s = "{\"resultData\": {\"loginname\":\"\",\"username\":\"username\",\"age\":12,\"sex\":\"sex\",\"userType\":\"userType\",\"points\":1,\"lastloginDate\":\"lastloginDate\",\"actionSession\":\"actionSession\"}, \"logMsg\": \"用户名或密码错误\",\"isOk\": true}";
        String s = "{\"resultData\":{'loginname':'','username':'username','age':12,'sex':'sex','userType':'userType','points':1,'lastloginDate':'lastloginDate','actionSession':'actionSession'},\"isOk\":true,\"logMsg\":\"用户名或密码错误\"}";
        Type type = new TypeToken<ResponseHttp<User>>() {
        }.getType();
        ResponseHttp<User> dataHttp = JsonParseUtil.getGson().fromJson(s, type);

        System.out.println("s:" + dataHttp.getResultData().getAge());
    }
}