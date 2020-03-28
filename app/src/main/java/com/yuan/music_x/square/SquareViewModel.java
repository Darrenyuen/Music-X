package com.yuan.music_x.square;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yuan.music_x.bean.NewSong;
import com.yuan.music_x.bean.RMSong;
import com.yuan.music_x.bean.SongInfo;
import com.yuan.music_x.util.GsonUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * yuan
 * 2020/3/2
 **/
public class SquareViewModel extends AndroidViewModel {

    private final String TAG = this.getClass().getSimpleName();

    private SquareModel squareModel;

    private List<RMSong> rmSongList;
    private List<NewSong> newSongList;

    private MutableLiveData<List<RMSong>> rmSongLiveList;
    private MutableLiveData<List<NewSong>> newSongLiveList;
    private MutableLiveData<SongInfo> songInfoMutableLiveData;

    private Document document;

    public SquareViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "SquareViewModel: ");
        this.squareModel = SquareModel.getInstance();
        rmSongList = new LinkedList<>();
        newSongList = new LinkedList<>();
        rmSongLiveList = new MutableLiveData<>();
        newSongLiveList = new MutableLiveData<>();
        songInfoMutableLiveData = new MutableLiveData<>();
    }

    // TODO: 2020/3/2 一次性爬取所需的信息
    void getWebContent() {
        rmSongList.clear();
        newSongList.clear();
        squareModel.getWebContent()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        document = Jsoup.parseBodyFragment(s);
                        //推荐歌单
                        Elements elementsOfRMSongs = document.getElementsByAttributeValue("class", "itemM selectSongList").first().child(1).children();
//                        Log.d(TAG, "onNext: " + elementsOfRMSongs.size());
                        for (Element element : elementsOfRMSongs) {
//                            Log.d(TAG, "onNext: " + element.children().size());
//                            Log.d(TAG, "onNext: " + element.child(0).text());
//                            Log.d(TAG, "onNext: " + element.child(1).attr("_src"));
//                            Log.d(TAG, "onNext: " + element.child(2).child(0).attr("href"));
//                            Log.d(TAG, "onNext: " + element.child(4).child(0).text());
//                            Log.d(TAG, "onNext: " + element.child(4).child(1).text());
                            rmSongList.add(new RMSong(element.child(0).text(), element.child(1).attr("_src"), element.child(2).child(0).attr("href"),
                                    element.child(4).child(0).text(), element.child(4).child(1).text()));
                        }

                        //新歌首发
                        Elements elementsOfNewSongs = document.getElementsByAttributeValue("class", "itemM newSongList").first().child(1).child(0).children();
//                        Log.d(TAG, "onNext: " + elementsOfNewSongs.size()); //0：华语 1：欧美 2：韩国 3：日本
                        for (Element element : elementsOfNewSongs) {
                            for (Element element1 : element.children()) {
//                                Log.d(TAG, "onNext: " + element1.attr("data"));
                                newSongList.add(GsonUtil.fromJson(element1.attr("data"), NewSong.class));
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                        rmSongLiveList.setValue(rmSongList);
                        newSongLiveList.setValue(newSongList);
                    }
                });
    }

    void getSongInfo(String hash) {
        squareModel.getSongInfo(hash)
                .subscribe(new Observer<SongInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SongInfo songInfo) {
                        songInfoMutableLiveData.setValue(songInfo);
                        Log.d(TAG, "onNext: " + songInfo.data.playUrl);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    LiveData<List<RMSong>> getRMSongs() {
        return rmSongLiveList;
    }

    MutableLiveData<List<NewSong>> getLiveNewSongs() {
        return newSongLiveList;
    }

    MutableLiveData<SongInfo> getSongInfo() {
        Log.d(TAG, "getSongInfo1: ");
        return songInfoMutableLiveData;
    }

}
