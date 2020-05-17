package com.yuan.music_x.adapter;

import android.content.pm.LauncherApps;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.yuan.music_x.widget.bannerview.LoadToView;

import java.util.List;

/**
 * yuan
 * 2020/3/5
 **/
public class BannerAdapter extends PagerAdapter {

    private String TAG = this.getClass().getSimpleName();

    private List<String> imageURLList;
    private List<ImageView> imageViewList;
    private LoadToView loadToView;

    public BannerAdapter(List<String> imageURLList, List<ImageView> imageViewList) {
        super();
        this.imageURLList = imageURLList;
        this.imageViewList = imageViewList;
    }

    public void setLoadToView(LoadToView loadToView) {
        this.loadToView = loadToView;
    }

    @Override
    public int getCount() {
        return imageURLList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = imageViewList.get(position);
        if (imageView.getDrawable() == null) {
            loadToView.loadToView(imageURLList.get(position), imageViewList.get(position));
        }
        container.addView(imageView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
