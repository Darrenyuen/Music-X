package com.yuan.music_x.main;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.yuan.music_x.mime.MineFragment;
import com.yuan.music_x.video.VideoFragment;
import com.yuan.music_x.R;
import com.yuan.music_x.search.SearchActivity;
import com.yuan.music_x.base.BaseActivity;
import com.yuan.music_x.square.SquareFragment;
import com.yuan.music_x.adapter.MainViewPagerAdapter;
import com.yuan.music_x.util.SharePreferenceUtil;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{

//    @BindView(R.id.drawLayout)

    DrawerLayout drawerLayout;
    TabLayout tabLayout;
    ImageView drawerImageView, searchImageView;
    ViewPager mainViewPager;
    LinearLayout nightModeLayout;
    TextView modeTextView;
    List<Fragment> fragmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabLayout = findViewById(R.id.tabLayout);
        drawerLayout = findViewById(R.id.drawLayout);
        drawerImageView = findViewById(R.id.drawNavImageView);
        searchImageView = findViewById(R.id.searchImageView);
        mainViewPager = findViewById(R.id.mainViewPager);
        nightModeLayout = findViewById(R.id.night_mode);
        modeTextView = findViewById(R.id.nightModeTextView);
        if (SharePreferenceUtil.getInstance(this).getMode() == 1) modeTextView.setText(R.string.night_mode);
        else if (SharePreferenceUtil.getInstance(this).getMode() == 2) modeTextView.setText(R.string.day_mode);
        drawerLayout.setOnClickListener(this);
        drawerImageView.setOnClickListener(this);
        searchImageView.setOnClickListener(this);
        nightModeLayout.setOnClickListener(this);
        fragmentList = new LinkedList<>();
        fragmentList.add(new MineFragment());
        fragmentList.add(new SquareFragment());
        fragmentList.add(new VideoFragment());
        mainViewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT, fragmentList));
        mainViewPager.setCurrentItem(1);
        tabLayout.setupWithViewPager(mainViewPager);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    //    @OnClick({R.id.drawNavImageView, R.id.searchImageView, R.id.navigation, R.id.setting, R.id.exit})
    public void onClick(@NotNull View view) {
        switch (view.getId()) {
            case R.id.drawNavImageView:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.searchImageView:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.night_mode:
                //获取当前的模式，设置相反的模式，这里只使用了，夜间和非夜间模式
                int currentMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if (currentMode != Configuration.UI_MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharePreferenceUtil.getInstance(this).saveMode(2);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharePreferenceUtil.getInstance(this).saveMode(1);
                }

                recreate();//需要recreate才能生效,需要对数据进行保存
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //相当于点击home键
            Intent intent= new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
