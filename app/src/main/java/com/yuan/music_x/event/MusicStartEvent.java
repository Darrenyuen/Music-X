package com.yuan.music_x.event;

import com.yuan.music_x.bean.SongInfo;

/**
 * yuan
 * 2020/2/27
 **/
public class MusicStartEvent {
    SongInfo songInfo;

    public MusicStartEvent(SongInfo songInfo) {
        this.songInfo = songInfo;
    }

    public SongInfo getSongInfo() {
        return songInfo;
    }

    public void setSongInfo(SongInfo songInfo) {
        this.songInfo = songInfo;
    }
}
