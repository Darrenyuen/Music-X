package com.yuan.music_x.util;

import android.util.Log;

import com.yuan.music_x.BuildConfig;

/**
 * yuan
 * 2020/2/27
 **/
public class LogUtil {
    private static boolean isPrintLog = BuildConfig.DEBUG;

    public static void d(String tag, String msg) {
        if (isPrintLog) Log.d(tag, msg);
    }
}
