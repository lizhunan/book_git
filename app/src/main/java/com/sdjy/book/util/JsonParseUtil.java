package com.sdjy.book.util;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdjy.book.mvp.http.base.ResponseHttp;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 李竹楠 on 2018/3/16.
 * json，model 转化工具
 */
public class JsonParseUtil {

    private static final String LIST_TAG = "list";
    private static final String OBJ_TAG = "obj";
    private static Gson gson = new Gson();

    /**
     * 实体转化为json
     *
     * @param bean 实体类
     * @return json
     */
    public static <T> String modeToJson(T bean) {
        return gson.toJson(bean);
    }

    /**
     * json转换为实体
     *
     * @param json json
     * @param cls  实体类
     * @param <T>  需要转成的泛型
     * @return 实体类
     */
    public static <T> T jsonToMode(String json, Class<T> cls) {
        return gson.fromJson(json, cls);
    }


    /**
     * json转换list
     *
     * @param json json
     * @param cls  实体类
     * @param <T>  需要专成的泛型
     * @return 实体类
     */
    public static <T> List<T> jsonArrayToList(String json, Class<T> cls) {
        Type type = new TypeToken<ArrayList<T>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static Gson getGson() {
        return gson;
    }

    public static void setGson(Gson gson) {
        JsonParseUtil.gson = gson;
    }
}
