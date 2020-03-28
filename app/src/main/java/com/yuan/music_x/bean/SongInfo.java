package com.yuan.music_x.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * yuan
 * 2020/2/27
 **/
public class SongInfo {
    /**
     * status : 1
     * err_code : 0
     * data : {"hash":"C5C6815B8F51D164859740F90D899E33","timelength":202475,"filesize":3240121,"audio_name":"Gaho - 시작","have_album":1,"album_name":"이태원 클라쓰 OST Part.2 (梨泰院Class OST Part.2)","album_id":"36151595","img":"http://imge.kugou.com/stdmusic/20200201/20200201164809215909.jpg","have_mv":0,"video_id":0,"author_name":"Gaho","song_name":"시작","lyrics":"﻿歌词","author_id":"776646","privilege":8,"privilege2":"1000","play_url":"https://webfs.yun.kugou.com/202003052132/064af3239518521bc0eba0d8314fa4b1/G192/M05/02/17/AA4DAF41PkyAV7m6ADFwuRfDeYU121.mp3","authors":[{"author_id":"776646","is_publish":"1","sizable_avatar":"http://singerimg.kugou.com/uploadpic/softhead/{size}/20190930/20190930172129718.jpg","author_name":"Gaho","avatar":"http://singerimg.kugou.com/uploadpic/softhead/400/20190930/20190930172129718.jpg"}],"is_free_part":0,"bitrate":128,"audio_id":"67272194","play_backup_url":"https://webfs.cloud.kugou.com/202003052132/f7580631570de875788ad19a04b53b28/G192/M05/02/17/AA4DAF41PkyAV7m6ADFwuRfDeYU121.mp3"}
     */

    @SerializedName("status")
    public int status;
    @SerializedName("err_code")
    public int errCode;
    @SerializedName("data")
    public DataBean data;

    public static class DataBean {
        /**
         * hash : C5C6815B8F51D164859740F90D899E33
         * timelength : 202475
         * filesize : 3240121
         * audio_name : Gaho - 시작
         * have_album : 1
         * album_name : 이태원 클라쓰 OST Part.2 (梨泰院Class OST Part.2)
         * album_id : 36151595
         * img : http://imge.kugou.com/stdmusic/20200201/20200201164809215909.jpg
         * have_mv : 0
         * video_id : 0
         * author_name : Gaho
         * song_name : 시작
         * lyrics : ﻿歌词
         * author_id : 776646
         * privilege : 8
         * privilege2 : 1000
         * play_url : https://webfs.yun.kugou.com/202003052132/064af3239518521bc0eba0d8314fa4b1/G192/M05/02/17/AA4DAF41PkyAV7m6ADFwuRfDeYU121.mp3
         * authors : [{"author_id":"776646","is_publish":"1","sizable_avatar":"http://singerimg.kugou.com/uploadpic/softhead/{size}/20190930/20190930172129718.jpg","author_name":"Gaho","avatar":"http://singerimg.kugou.com/uploadpic/softhead/400/20190930/20190930172129718.jpg"}]
         * is_free_part : 0
         * bitrate : 128
         * audio_id : 67272194
         * play_backup_url : https://webfs.cloud.kugou.com/202003052132/f7580631570de875788ad19a04b53b28/G192/M05/02/17/AA4DAF41PkyAV7m6ADFwuRfDeYU121.mp3
         */

        @SerializedName("hash")
        public String hash;
        @SerializedName("timelength")
        public int timelength;
        @SerializedName("filesize")
        public int filesize;
        @SerializedName("audio_name")
        public String audioName;
        @SerializedName("have_album")
        public int haveAlbum;
        @SerializedName("album_name")
        public String albumName;
        @SerializedName("album_id")
        public String albumId;
        @SerializedName("img")
        public String img;
        @SerializedName("have_mv")
        public int haveMv;
        @SerializedName("video_id")
        public int videoId;
        @SerializedName("author_name")
        public String authorName;
        @SerializedName("song_name")
        public String songName;
        @SerializedName("lyrics")
        public String lyrics;
        @SerializedName("author_id")
        public String authorId;
        @SerializedName("privilege")
        public int privilege;
        @SerializedName("privilege2")
        public String privilege2;
        @SerializedName("play_url")
        public String playUrl;
        @SerializedName("is_free_part")
        public int isFreePart;
        @SerializedName("bitrate")
        public int bitrate;
        @SerializedName("audio_id")
        public String audioId;
        @SerializedName("play_backup_url")
        public String playBackupUrl;
        @SerializedName("authors")
        public List<AuthorsBean> authors;

        public static class AuthorsBean {
            /**
             * author_id : 776646
             * is_publish : 1
             * sizable_avatar : http://singerimg.kugou.com/uploadpic/softhead/{size}/20190930/20190930172129718.jpg
             * author_name : Gaho
             * avatar : http://singerimg.kugou.com/uploadpic/softhead/400/20190930/20190930172129718.jpg
             */

            @SerializedName("author_id")
            public String authorId;
            @SerializedName("is_publish")
            public String isPublish;
            @SerializedName("sizable_avatar")
            public String sizableAvatar;
            @SerializedName("author_name")
            public String authorName;
            @SerializedName("avatar")
            public String avatar;
        }
    }

}
