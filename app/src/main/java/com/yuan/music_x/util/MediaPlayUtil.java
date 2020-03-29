package com.yuan.music_x.util;

import android.media.MediaPlayer;
import android.util.Log;

import com.yuan.music_x.event.MusicPauseEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

/**
 * yuan
 * 2020/3/6
 **/
public class MediaPlayUtil implements MediaPlayer.OnCompletionListener {

    private String TAG = this.getClass().getSimpleName();

    private static MediaPlayUtil instance;
    private static MediaPlayer mediaPlayer;


    private MediaPlayUtil() {
        super();
    }

    public static MediaPlayUtil getInstance() {
        if (instance == null) {
            synchronized (MediaPlayUtil.class) {
                if (instance == null) {
                    if (mediaPlayer == null) mediaPlayer = new MediaPlayer();
                    instance = new MediaPlayUtil();
                }
            }
        }
        return instance;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void start(String string) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(string);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    public void changeMusic(int position) {

    }

}
