package com.yuan.music_x;

import android.app.Application;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

import com.yuan.music_x.util.SharePreferenceUtil;

/**
 * yuan
 * 2020/3/7
 **/
public class APP extends Application {
    private String TAG = this.getClass().getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        if (SharePreferenceUtil.getInstance(getApplicationContext()).getMode() == 1) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        else if (SharePreferenceUtil.getInstance(getApplicationContext()).getMode() == 2) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }
}
