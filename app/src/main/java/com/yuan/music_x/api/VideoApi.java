package com.yuan.music_x.api;

import com.yuan.music_x.bean.VideoData;
import com.yuan.music_x.bean.VideoInfo;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * yuan
 * 2020/3/28
 **/
public interface VideoApi {

    @GET("VServices/Video.OfflineVideoService.getNewMvList/{lists}")
    Flowable<VideoData> getVideoData(@Path("lists") String lists);

    @GET("https://fx.service.kugou.com/mvcenter/bss/mvInfo?")
    Flowable<VideoInfo> getVideoInfo(@Query("pid") String pid, @Query("videoId") String videoId);
}
