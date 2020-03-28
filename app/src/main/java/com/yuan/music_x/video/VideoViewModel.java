package com.yuan.music_x.video;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.yuan.music_x.bean.VideoData;
import com.yuan.music_x.bean.VideoInfo;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * yuan
 * 2020/3/28
 **/
public class VideoViewModel extends AndroidViewModel {

    VideoModel videoModel;

    private MutableLiveData<VideoData> videoDataMutableLiveData;
    private MutableLiveData<VideoInfo> videoInfoMutableLiveData;

    public VideoViewModel(@NonNull Application application) {
        super(application);
        videoModel = VideoModel.getInstance();
        videoDataMutableLiveData = new MutableLiveData<>();
        videoInfoMutableLiveData = new MutableLiveData<>();
    }

    void getVideoData(String lists) {
        videoModel.getVideoData(lists)
                .subscribe(new Subscriber<VideoData>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(VideoData videoData) {
                        videoDataMutableLiveData.setValue(videoData);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    void getVideoInfo(String videoId) {
        videoModel.getVideoInfo(videoId)
                .subscribe(new Subscriber<VideoInfo>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(VideoInfo videoInfo) {
                        videoInfoMutableLiveData.setValue(videoInfo);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    MutableLiveData<VideoData> getVideoData() {
        return videoDataMutableLiveData;
    }

    MutableLiveData<VideoInfo> getVideoInfo() {
        return videoInfoMutableLiveData;
    }
}
