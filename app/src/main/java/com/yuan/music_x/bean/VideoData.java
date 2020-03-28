package com.yuan.music_x.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * yuan
 * 2020/3/28
 **/
public class VideoData {
    /**
     * servertime : 1585395407
     * callback : []
     * data : {"list":[{"id":"1354604691799877686","title":"心有独钟","actor":"Se喵喵姐","director":"Se喵喵姐","labelids":[],"duration":60,"songid":0,"actordelstatus":0,"directordelstatus":0,"songName":null,"extJson":null,"actorUserId":1466755463,"directorUserId":1466755463,"labelId":null,"addTime":1585238399,"hashValue":"9a0ff9c89f25dda0f15f58396dd69f92.mp4","imgUrl":"http://fximgbssdl.kugou.com/0314258888fc6be577badb213fb53b91.jpg","videoId":"1354604691799877686","playNum":2,"commentNum":0,"praiseNum":0}],"count":50}
     * status : 1
     * errorcode :
     * errorno : 0
     * code : 0
     * msg :
     * time : 1585395407
     * times : 1585395407
     */

    @SerializedName("servertime")
    public int servertime;
    @SerializedName("data")
    public DataBean data;
    @SerializedName("status")
    public int status;
    @SerializedName("errorcode")
    public String errorcode;
    @SerializedName("errorno")
    public int errorno;
    @SerializedName("code")
    public int code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("time")
    public int time;
    @SerializedName("times")
    public int times;
    @SerializedName("callback")
    public List<?> callback;

    public int getCode() {
        return code;
    }

    public static class DataBean {
        /**
         * list : [{"id":"1354604691799877686","title":"心有独钟","actor":"Se喵喵姐","director":"Se喵喵姐","labelids":[],"duration":60,"songid":0,"actordelstatus":0,"directordelstatus":0,"songName":null,"extJson":null,"actorUserId":1466755463,"directorUserId":1466755463,"labelId":null,"addTime":1585238399,"hashValue":"9a0ff9c89f25dda0f15f58396dd69f92.mp4","imgUrl":"http://fximgbssdl.kugou.com/0314258888fc6be577badb213fb53b91.jpg","videoId":"1354604691799877686","playNum":2,"commentNum":0,"praiseNum":0}]
         * count : 50
         */

        @SerializedName("count")
        public int count;
        @SerializedName("list")
        public List<ListBean> list;

        public static class ListBean {
            /**
             * id : 1354604691799877686
             * title : 心有独钟
             * actor : Se喵喵姐
             * director : Se喵喵姐
             * labelids : []
             * duration : 60
             * songid : 0
             * actordelstatus : 0
             * directordelstatus : 0
             * songName : null
             * extJson : null
             * actorUserId : 1466755463
             * directorUserId : 1466755463
             * labelId : null
             * addTime : 1585238399
             * hashValue : 9a0ff9c89f25dda0f15f58396dd69f92.mp4
             * imgUrl : http://fximgbssdl.kugou.com/0314258888fc6be577badb213fb53b91.jpg
             * videoId : 1354604691799877686
             * playNum : 2
             * commentNum : 0
             * praiseNum : 0
             */

            @SerializedName("id")
            public String id;
            @SerializedName("title")
            public String title;
            @SerializedName("actor")
            public String actor;
            @SerializedName("director")
            public String director;
            @SerializedName("duration")
            public int duration;
            @SerializedName("songid")
            public int songid;
            @SerializedName("actordelstatus")
            public int actordelstatus;
            @SerializedName("directordelstatus")
            public int directordelstatus;
            @SerializedName("songName")
            public Object songName;
            @SerializedName("extJson")
            public Object extJson;
            @SerializedName("actorUserId")
            public int actorUserId;
            @SerializedName("directorUserId")
            public int directorUserId;
            @SerializedName("labelId")
            public Object labelId;
            @SerializedName("addTime")
            public int addTime;
            @SerializedName("hashValue")
            public String hashValue;
            @SerializedName("imgUrl")
            public String imgUrl;
            @SerializedName("videoId")
            public String videoId;
            @SerializedName("playNum")
            public int playNum;
            @SerializedName("commentNum")
            public int commentNum;
            @SerializedName("praiseNum")
            public int praiseNum;
            @SerializedName("labelids")
            public List<?> labelids;

            public String getImgUrl() {
                return imgUrl;
            }

            public String getTitle() {
                return title;
            }

            public String getActor() {
                return actor;
            }

            public String getId() {
                return id;
            }
        }
    }
}
