package com.yuan.music_x.util;

import android.app.Activity;
import android.content.Intent;

import com.yuan.music_x.main.MainActivity;

/**
 * yuan
 * 2020/2/21
 **/
public class ActivityStarter {
    private static ActivityStarter instance;

    public static ActivityStarter getInstance() {
        if (instance == null) {
            synchronized (ActivityStarter.class) {
                if (instance == null) {
                    instance = new ActivityStarter();
                }
            }
        }
        return instance;
    }

    public void startMainActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    public void startLoginActivity(Activity activity) {

    }
}
