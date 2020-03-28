package com.yuan.music_x.square;

import com.yuan.music_x.api.GetSongInfo;
import com.yuan.music_x.api.GetWebContent;
import com.yuan.music_x.bean.SongInfo;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * yuan
 * 2020/3/2
 **/
public class SquareModel {

    private final int TIMEOUT = 5000;

    private final String WEBCONTECT = "https://kugou.com";
    private final String SONGINFO = "https://wwwapi.kugou.com/";

    private volatile static SquareModel instance;

    private Retrofit webContentRetrofit, songInfoRetrofit;

    private OkHttpClient okHttpClient;

    private GetWebContent getWebContent;
    private GetSongInfo getSongInfo;

    private SquareModel() {
        super();

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .callTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();

        webContentRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(WEBCONTECT)
                .build();

        songInfoRetrofit = new Retrofit.Builder()
//                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SONGINFO)
                .build();

        getWebContent = webContentRetrofit.create(GetWebContent.class);
        getSongInfo = songInfoRetrofit.create(GetSongInfo.class);

    }

    static SquareModel getInstance() {
        if (instance == null) {
            synchronized (SquareModel.class) {
                if(instance == null) {
                    instance = new SquareModel();
                }
            }
        }
        return instance;
    }

    Observable<String> getWebContent() {
        
        return getWebContent.getWebContent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    Observable<SongInfo> getSongInfo(String hash) {
        return getSongInfo.getSongInfo("play/getdata", hash, "5507bf2f3d26695801aa47395721b2ef")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
