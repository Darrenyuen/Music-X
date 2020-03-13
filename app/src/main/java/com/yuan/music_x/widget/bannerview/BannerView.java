package com.yuan.music_x.widget.bannerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.yuan.music_x.R;
import com.yuan.music_x.adapter.BannerAdapter;
import com.yuan.music_x.adapter.MainViewPagerAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * yuan
 * 2020/2/27
 **/
public class BannerView extends RelativeLayout implements View.OnClickListener {

    private String TAG = this.getClass().getSimpleName();

    private Activity context;
    private ViewPager viewPager;
    private BannerAdapter bannerAdapter;
    private Paint paint;
    private Rect rect;

    public BannerView(@NonNull Context context) {
        this(context, null);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = (Activity) context;
        viewPager = new ViewPager(context);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        rect = new Rect(50,150,100, 200);
    }

    public void setAdapter(BannerAdapter bannerAdapter) {
        this.bannerAdapter = bannerAdapter;
        bannerAdapter.setLoadToView(new LoadToView() {
            @Override
            public void loadToView(String url, ImageView imageView) {
                Glide.with(context).load(url).into(imageView);
            }
        });
        viewPager.setAdapter(bannerAdapter);
        this.addView(viewPager, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initView();
    }

    private void initView() {
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth();
        int count = bannerAdapter.getCount();
        float margin = 10;
        int rY = 350;
        double rX = width / 2.0 - (2 * 30 * count / 2.0 + margin * (count - 1) / 2.0) + 30;

        for (int i = 0; i < count; i++) {
            if (viewPager.getCurrentItem() == i) paint.setColor(context.getResources().getColor(R.color.colorAccent));
            else paint.setColor(context.getResources().getColor(R.color.divide_line));
            if (i != 0) {
                rX += 30 * 2 + margin;
                rect.left = (int) rX;
                canvas.drawRect((int) rX, rY, (int) rX + 30, rY + 20, paint);
            }
            canvas.drawRect((int) rX, rY, (int) rX + 30, rY + 20, paint);
        }
    }

    @Override
    public void onClick(View view) {

    }
}
