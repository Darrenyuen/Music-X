package com.yuan.music_x.bean;

/**
 * yuan
 * 2020/3/4
 **/
public class RMSong {

    private String num;
    private String imageURL;
    private String href;
    private String songListName;
    private String songListSinger;

    public RMSong(String num, String imageURL, String href, String songListName, String songListSinger) {
        super();
        this.num = num;
        this.imageURL = imageURL;
        this.href = href;
        this.songListName = songListName;
        this.songListSinger = songListSinger;
    }

    public String getNum() {
        return num;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getHref() {
        return href;
    }

    public String getSongListName() {
        return songListName;
    }

    public String getSongListSinger() {
        return songListSinger;
    }
}
