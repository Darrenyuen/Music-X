package com.yuan.music_x;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.yuan.music_x.util.LogUtil;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    @BindView(R.id.drawLayout)

    DrawerLayout drawerLayout;
    ImageView drawerImageView, searchImageView;

    private long firstTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawLayout);
        drawerImageView = findViewById(R.id.drawNavImageView);
        searchImageView = findViewById(R.id.searchImageView);
        drawerLayout.setOnClickListener(this);
        drawerImageView.setOnClickListener(this);
        searchImageView.setOnClickListener(this);

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
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
            if (System.currentTimeMillis() - firstTime >= 2000) {
                Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
                return true;
            } else {
                finish();
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
