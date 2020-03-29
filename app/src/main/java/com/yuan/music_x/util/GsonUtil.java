package com.yuan.music_x.util;

/**
 * yuan
 * 2020/2/27
 **/

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * 字符串和Java类的转换工具
 */
public class GsonUtil {

    private static Gson createGson() {
        Gson gson = new Gson();
        return gson;
    }

    public static String toJson(Object object) {
        return createGson().toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        try {
            return createGson().fromJson(json, tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
