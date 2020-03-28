package com.yuan.music_x.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * yuan
 * 2020/3/2
 **/
public interface GetWebContent {
    @GET("/")
    Observable<String> getWebContent();
}
