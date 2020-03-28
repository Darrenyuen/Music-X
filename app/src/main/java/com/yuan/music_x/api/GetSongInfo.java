package com.yuan.music_x.api;

import com.yuan.music_x.bean.SongInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * yuan
 * 2020/3/5
 **/
public interface GetSongInfo {
    @GET("yy/index.php?")
    Observable<SongInfo> getSongInfo(@Query("r") String r, @Query("hash") String hash, @Query("mid") String mid);
}
