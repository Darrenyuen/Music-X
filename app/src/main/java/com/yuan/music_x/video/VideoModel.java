package com.yuan.music_x.video;

import androidx.lifecycle.ViewModel;

import com.yuan.music_x.api.VideoApi;
import com.yuan.music_x.bean.VideoData;
import com.yuan.music_x.bean.VideoInfo;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * yuan
 * 2020/3/28
 **/
public class VideoModel {

    private volatile static VideoModel instance;

    private VideoApi videoApi;

    private VideoModel() {
        super();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fx.service.kugou.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        videoApi = retrofit.create(VideoApi.class);

    }

    static VideoModel getInstance() {
        if (instance == null) {
            synchronized (ViewModel.class) {
                if (instance == null) {
                    instance = new VideoModel();
                }
            }
        }
        return instance;
    }

    Flowable<VideoData> getVideoData(String lists) {
        return videoApi.getVideoData(lists)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    Flowable<VideoInfo> getVideoInfo(String videoID) {
        return videoApi.getVideoInfo( "6", videoID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
