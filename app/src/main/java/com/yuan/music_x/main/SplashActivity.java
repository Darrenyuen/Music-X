package com.yuan.music_x.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;

import com.yuan.music_x.R;
import com.yuan.music_x.base.BaseActivity;
import com.yuan.music_x.util.ActivityStarter;

public class SplashActivity extends BaseActivity {

    private String TAG = this.getClass().getSimpleName();

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
        startCountDownTime();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_splash;
    }

    private void startCountDownTime() {
        countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                //防止进入有状态栏的界面时界面发生抖动
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                ActivityStarter.getInstance().startMainActivity(SplashActivity.this);
                SplashActivity.this.finish();
            }
        };
        countDownTimer.start();
    }

    @Override
    public void finish() {
        super.finish();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }
}
