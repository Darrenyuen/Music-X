package com.yuan.music_x.event;

import com.yuan.music_x.bean.SongInfo;

/**
 * yuan
 * 2020/2/27
 **/
public class MusicPauseEvent {
    SongInfo songInfo;
    public MusicPauseEvent(SongInfo songInfo) {
        this.songInfo = songInfo;
    }

    public SongInfo getSongInfo() {
        return songInfo;
    }

    public void setSongInfo(SongInfo songInfo) {
        this.songInfo = songInfo;
    }
}
