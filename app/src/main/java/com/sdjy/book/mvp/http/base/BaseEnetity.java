package com.sdjy.book.mvp.http.base;

import android.util.Log;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 李竹楠 on 2018/3/15.
 * 定义基础实体类
 */

public abstract class BaseEnetity {
    public BaseEnetity(){}

    /**
     * 获取请求链接
     *
     * @return 请求URL
     */
    public abstract String getRuqestURL();


    /**
     * 返回请求键值对
     * @return map
     */
    public Map<String, Object> getMapEnticty() {
        Class<? extends BaseEnetity> clazz = this.getClass();
        Class<? extends Object> superclass = clazz.getSuperclass();

        Field[] fields = clazz.getDeclaredFields();
        Field[] superFields = superclass.getDeclaredFields();

        if (fields == null || fields.length == 0) {
            return Collections.emptyMap();
        }

        Map<String, Object> params = new HashMap<String, Object>();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                params.put(field.getName(), field.get(this));
                Log.d("baseEn:field",field.getName());
            }

            for (Field superField : superFields) {
                superField.setAccessible(true);
                params.put(superField.getName(), superField.get(this));
                Log.d("baseEn:superField",superField.getName());
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return params;
    }

}
