package com.yuan.music_x.bean;

import com.google.gson.annotations.SerializedName;

/**
 * yuan
 * 2020/3/4
 **/
public class NewSong {
    /**
     * first : 1
     * Hash : 37AD5D3DD6DC940BC85D94C9639FD742
     * time : 03:28
     * timeLen : 208039
     * FileName : 容祖儿 - 那些没说出口的话【完美关系电视剧情感主题曲】
     * Filename : 容祖儿 - 那些没说出口的话【完美关系电视剧情感主题曲】
     * albumId : 36494410
     * privilege : 8
     * size : 3329088
     * songLink : 14po33d6
     * mixsongid : 250729464
     */

    @SerializedName("first")
    public int first;
    @SerializedName("Hash")
    public String Hash;
    @SerializedName("time")
    public String time;
    @SerializedName("timeLen")
    public int timeLen;
    @SerializedName("FileName")
    public String FileName;
    @SerializedName("Filename")
    public String Filename;
    @SerializedName("albumId")
    public int albumId;
    @SerializedName("privilege")
    public int privilege;
    @SerializedName("size")
    public int size;
    @SerializedName("songLink")
    public String songLink;
    @SerializedName("mixsongid")
    public int mixsongid;

    public String getHash() {
        return Hash;
    }

    public String getTime() {
        return time;
    }

    public String getFileName() {
        return FileName;
    }

    //singerName
    public String getFilename() {
        return Filename;
    }

}
