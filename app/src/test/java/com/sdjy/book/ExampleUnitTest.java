package com.sdjy.book;

import com.google.gson.reflect.TypeToken;
import com.sdjy.book.mvp.entity.BooksInfo;
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
      //  String s = "{'resultData':'{'booksArray':[{'bookNo':'b00001','athor':'夏冰冰','price':0.0,'bookName':'Java入门基础','publishing':'交通学院'},{'bookNo':'b00003','athor':'（美）Brian W. Kernighan,（美）Dennis M. Ritchie','price':30.0,'bookName':'C程序设计语言','publishing':'机械工业出版社'},{'bookNo':'b00006','athor':'','price':45.0,'bookName':'测试','publishing':''},{'bookNo':'b00007','athor':'','price':45.0,'bookName':'Orac1','publishing':''},{'bookNo':'b00008','athor':'','price':45.0,'bookName':'2Orac1','publishing':''},{'bookNo':'b00009','image':'b00009.jpg','athor':'崔庆才','price':99.0,'bookName':'Python 3网络爬虫开发实战','publishing':'人民邮电出版社'}]}','isOk':true,'logMsg':'查询成功'}";
        //String s = "{\"resultData\":\"\",\"isOk\":true,\"logMsg\":\"查询成功\"}";
        //String s = "{\"resultData\":\"[{\\\"bookNo\\\":\\\"b00001\\\",\\\"athor\\\":\\\"夏冰冰\\\",\\\"price\\\":0.0,\\\"bookName\\\":\\\"Java入门基础\\\",\\\"publishing\\\":\\\"交通学院\\\"},{\\\"bookNo\\\":\\\"b00003\\\",\\\"athor\\\":\\\"（美）Brian W. Kernighan,（美）Dennis M. Ritchie\\\",\\\"price\\\":30.0,\\\"bookName\\\":\\\"C程序设计语言\\\",\\\"publishing\\\":\\\"机械工业出版社\\\"},{\\\"bookNo\\\":\\\"b00006\\\",\\\"athor\\\":\\\"\\\",\\\"price\\\":45.0,\\\"bookName\\\":\\\"测试\\\",\\\"publishing\\\":\\\"\\\"},{\\\"bookNo\\\":\\\"b00007\\\",\\\"athor\\\":\\\"\\\",\\\"price\\\":45.0,\\\"bookName\\\":\\\"Orac1\\\",\\\"publishing\\\":\\\"\\\"},{\\\"bookNo\\\":\\\"b00008\\\",\\\"athor\\\":\\\"\\\",\\\"price\\\":45.0,\\\"bookName\\\":\\\"2Orac1\\\",\\\"publishing\\\":\\\"\\\"},{\\\"bookNo\\\":\\\"b00009\\\",\\\"image\\\":\\\"b00009.jpg\\\",\\\"athor\\\":\\\"崔庆才\\\",\\\"price\\\":99.0,\\\"bookName\\\":\\\"Python 3网络爬虫开发实战\\\",\\\"publishing\\\":\\\"人民邮电出版社\\\"}]\",\"isOk\":true,\"logMsg\":\"查询成功\"}";
        String s = "{'resultData':[{},{}],'isOk':true,'logMsg':'查询成功'}";

       /* JSONObject jsonObject = new JSONObject(s);
        String data = jsonObject.getString("resultData");
        boolean isOk = jsonObject.getBoolean("isOk");
        String logMsg = jsonObject.getString("logMsg");
        JSONObject jsonObject1 = new JSONObject(data);
        ResponseHttp<List<BooksInfo.BooksArrayBean>> responseHttp = new ResponseHttp<>();
        responseHttp.setLogMsg(logMsg);
        responseHttp.setOk(isOk);*/
        Type type = new TypeToken<ResponseHttp<String>>() {
        }.getType();
        ResponseHttp<String> dataHttp = JsonParseUtil.getGson().fromJson(s, type);

        System.out.println("s:" + dataHttp.getResultData());
    }
}