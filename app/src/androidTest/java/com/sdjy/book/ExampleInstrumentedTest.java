package com.sdjy.book;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.reflect.TypeToken;
import com.sdjy.book.mvp.entity.BooksInfo;
import com.sdjy.book.mvp.http.base.ResponseHttp;
import com.sdjy.book.util.JsonParseUtil;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        String s = "{'resultData':[{},{}],'isOk':true,'logMsg':'查询成功'}";

        JSONObject jsonObject = new JSONObject(s);
        String data = jsonObject.getString("resultData");
        boolean isOk = jsonObject.getBoolean("isOk");
        String logMsg = jsonObject.getString("logMsg");
        JSONObject jsonObject1 = new JSONObject(data);
        ResponseHttp<List<BooksInfo.BooksArrayBean>> responseHttp = new ResponseHttp<>();
        responseHttp.setLogMsg(logMsg);
        responseHttp.setOk(isOk);
        Type type = new TypeToken<ResponseHttp<List<BooksInfo.BooksArrayBean>>>() {
        }.getType();
        ResponseHttp<List<BooksInfo.BooksArrayBean>> dataHttp = JsonParseUtil.getGson().fromJson(jsonObject1.getString("booksArray"), type);
        responseHttp.setResultData(dataHttp.getResultData());

        System.out.println("s:" + responseHttp.getResultData());
    }
}
