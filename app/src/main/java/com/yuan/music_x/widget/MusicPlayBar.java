package com.yuan.music_x.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuan.music_x.R;
import com.yuan.music_x.event.MusicPauseEvent;
import com.yuan.music_x.event.MusicStartEvent;
import com.yuan.music_x.bean.SongInfo;
import com.yuan.music_x.util.MediaPlayUtil;
import com.yuan.music_x.util.NetWorkUtil;
import com.yuan.music_x.util.SharePreferenceUtil;
import com.yuan.music_x.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * yuan
 * 2020/2/27
 **/
public class MusicPlayBar extends RelativeLayout {

    private String TAG = this.getClass().getSimpleName();

    private Context context;
    private RelativeLayout relativeLayout;
    private CircleImageView circleImageView;
    private ImageView playImageView, controllerImageView;
    private TextView songName, singerName;
    private SeekBar seekBar;

    private int STATE = 1;
    private final int PLAYING_STATE = 0;
    private final int STOPING_STATE = 1;

    private SongInfo currentSongInfo;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPlayMusicEvent(MusicStartEvent event){
        STATE = PLAYING_STATE;
        Log.d(TAG, "onPlayMusicEvent: ");
        SharePreferenceUtil.getInstance(getContext()).saveLatestSong(event.getSongInfo());
        Log.d(TAG, "onPlayMusicEvent: ");
        setSongInfo(event.getSongInfo());
        playImageView.setImageResource(R.drawable.shape_stop);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPauseMusicEvent(MusicPauseEvent event) {
        STATE = STOPING_STATE;
        setSongInfo(event.getSongInfo());
        playImageView.setImageResource(R.drawable.shape_play);
    }

    public MusicPlayBar(Context context) {
        this(context, null);
    }

    public MusicPlayBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MusicPlayBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        EventBus.getDefault().register(this);
        this.context = context;
        initView();
        initListener();
        initSongInfo();
    }

    private void initView() {
        relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.layout_bottom_songplay_control, this, true);
        circleImageView = findViewById(R.id.circleImageView);
        playImageView = findViewById(R.id.playImageView);
        controllerImageView = findViewById(R.id.controllerImageView);
        songName = findViewById(R.id.songNameTextView);
        singerName = findViewById(R.id.singerTextView);
        seekBar = findViewById(R.id.seekBar);
    }

    private void initListener() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch: " + seekBar.getProgress());
                MediaPlayUtil.getInstance().getMediaPlayer().seekTo(seekBar.getProgress());
            }
        });

        playImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (STATE == PLAYING_STATE) {
                    playImageView.setImageResource(R.drawable.shape_play);
                    MediaPlayUtil.getInstance().pause();
                    STATE = STOPING_STATE;
                } else if (STATE == STOPING_STATE) {
                    playImageView.setImageResource(R.drawable.shape_stop);
                    MediaPlayUtil.getInstance().getMediaPlayer().start();
                    STATE = PLAYING_STATE;
                }
            }
        });

    }

    private void initSongInfo() {
        Log.d(TAG, "initSongInfo: ");
        currentSongInfo = SharePreferenceUtil.getInstance(getContext()).getLatestSong();
        if (currentSongInfo != null) {
            setSongInfo(currentSongInfo);
            // TODO: 2020/2/27 是否处于播放态
        }
    }

    private void setSongInfo(SongInfo songInfo) {
        currentSongInfo = songInfo;
        songName.setText(songInfo.data.songName);
        singerName.setText(songInfo.data.authorName);
        Glide.with(context).load(songInfo.data.img).into(circleImageView);
    }
}
