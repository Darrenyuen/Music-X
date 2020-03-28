package com.yuan.music_x.bean;

import com.google.gson.annotations.SerializedName;

/**
 * yuan
 * 2020/3/28
 **/
public class VideoInfo {

    /**
     * code : 0
     * msg : 成功
     * time : 1585395442
     * data : {"id":1354604691799877686,"title":"心有独钟","actor":"Se喵喵姐","director":"Se喵喵姐","actorUserId":1466755463,"actorKugouId":1466755463,"directorUserId":0,"directorKugouId":1466755463,"duration":60,"addTime":1585238399,"hashValue":"9a0ff9c89f25dda0f15f58396dd69f92.mp4","imgUrl":"https://fximgbssdl.kugou.com/0314258888fc6be577badb213fb53b91.jpg","videoUrl":"https://fxmvbssdlbig.kugou.com/2003281937/z2kGcvzNUR3S3k0ZD1H6GQ/1585481842/9a0ff9c89f25dda0f15f58396dd69f92.mp4","layout":1,"videoUrlVertical":"","verticalLayout":1}
     */

    @SerializedName("code")
    public int code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("time")
    public int time;
    @SerializedName("data")
    public DataBean data;

    public int getCode() {
        return code;
    }

    public static class DataBean {
        /**
         * id : 1354604691799877686
         * title : 心有独钟
         * actor : Se喵喵姐
         * director : Se喵喵姐
         * actorUserId : 1466755463
         * actorKugouId : 1466755463
         * directorUserId : 0
         * directorKugouId : 1466755463
         * duration : 60
         * addTime : 1585238399
         * hashValue : 9a0ff9c89f25dda0f15f58396dd69f92.mp4
         * imgUrl : https://fximgbssdl.kugou.com/0314258888fc6be577badb213fb53b91.jpg
         * videoUrl : https://fxmvbssdlbig.kugou.com/2003281937/z2kGcvzNUR3S3k0ZD1H6GQ/1585481842/9a0ff9c89f25dda0f15f58396dd69f92.mp4
         * layout : 1
         * videoUrlVertical :
         * verticalLayout : 1
         */

        @SerializedName("id")
        public long id;
        @SerializedName("title")
        public String title;
        @SerializedName("actor")
        public String actor;
        @SerializedName("director")
        public String director;
        @SerializedName("actorUserId")
        public int actorUserId;
        @SerializedName("actorKugouId")
        public int actorKugouId;
        @SerializedName("directorUserId")
        public int directorUserId;
        @SerializedName("directorKugouId")
        public int directorKugouId;
        @SerializedName("duration")
        public int duration;
        @SerializedName("addTime")
        public int addTime;
        @SerializedName("hashValue")
        public String hashValue;
        @SerializedName("imgUrl")
        public String imgUrl;
        @SerializedName("videoUrl")
        public String videoUrl;
        @SerializedName("layout")
        public int layout;
        @SerializedName("videoUrlVertical")
        public String videoUrlVertical;
        @SerializedName("verticalLayout")
        public int verticalLayout;

        public String getVideoUrl() {
            return videoUrl;
        }
    }
}
