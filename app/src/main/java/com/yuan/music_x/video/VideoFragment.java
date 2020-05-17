package com.yuan.music_x.video;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.yuan.music_x.R;
import com.yuan.music_x.adapter.VideoLayoutAdapter;
import com.yuan.music_x.base.BaseLazyFragment;
import com.yuan.music_x.bean.VideoData;
import com.yuan.music_x.bean.VideoInfo;
import com.yuan.music_x.util.ToastUtil;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseLazyFragment {

    private ViewStub errorNetwork;
    private RecyclerView videoLayout;

    private VideoViewModel videoViewModel;
    private GridLayoutManager layoutManager;
    private VideoLayoutAdapter videoLayoutAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        errorNetwork = view.findViewById(R.id.errorNetWork);
        videoLayout = view.findViewById(R.id.videoLayout);
        layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        videoLayout.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void fetchData() {
        videoViewModel.getVideoData("1-12/");
        videoViewModel.getVideoData().observe(this, new Observer<VideoData>() {
            @Override
            public void onChanged(final VideoData videoData) {
                videoLayoutAdapter = new VideoLayoutAdapter(getContext(), videoData.data.list);
                videoLayoutAdapter.setOnItemClickListener(new VideoLayoutAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        videoViewModel.getVideoInfo(videoData.data.list.get(position).getId());
                        // TODO: 2020/3/29 需不需要重开一个activity播放视频
                        Log.d(TAG, "onItemClick: " + videoData.data.list.get(position).getId());
                    }
                });
                videoLayout.setAdapter(videoLayoutAdapter);
            }
        });

        videoViewModel.getVideoInfo().observe(this, new Observer<VideoInfo>() {
            @Override
            public void onChanged(VideoInfo videoInfo) {
                Log.d(TAG, "onChanged: " + videoInfo.data.videoUrl);
                Intent intent = new Intent(getContext(), VideoPlayActivity.class);
                intent.putExtra("videoUrl", videoInfo.data.videoUrl);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void showErrorPage() {

    }
}
