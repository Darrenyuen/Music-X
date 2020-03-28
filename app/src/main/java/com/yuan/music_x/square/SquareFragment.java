package com.yuan.music_x.square;


import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.yuan.music_x.R;
import com.yuan.music_x.adapter.BannerAdapter;
import com.yuan.music_x.adapter.RmImageAdapter;
import com.yuan.music_x.adapter.SingleSongAdapter;
import com.yuan.music_x.base.BaseLazyFragment;
import com.yuan.music_x.bean.NewSong;
import com.yuan.music_x.bean.RMSong;
import com.yuan.music_x.bean.SongInfo;
import com.yuan.music_x.event.MusicStartEvent;
import com.yuan.music_x.util.MediaPlayUtil;
import com.yuan.music_x.util.NetWorkUtil;
import com.yuan.music_x.util.ToastUtil;
import com.yuan.music_x.widget.MusicPlayBar;
import com.yuan.music_x.widget.bannerview.BannerView;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedList;
import java.util.List;

import okhttp3.internal.cache.DiskLruCache;

/**
 * A simple {@link Fragment} subclass.
 */
public class SquareFragment extends BaseLazyFragment {

    private SquareViewModel squareViewModel;

    private RecyclerView recommendSongsLayout;
    private ListView newSongsLayout;
    private BannerView bannerView;
    private View viewStub;
    private LinearLayout rootView;
    private RelativeLayout successNetWork;
    private MusicPlayBar musicPlayBar;
    private BannerAdapter bannerAdapter;
    private RmImageAdapter rmImageAdapter;
    SkeletonScreen skeletonScreen;
    List<NewSong> newSongs;

    public SquareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        squareViewModel = ViewModelProviders.of(this).get(SquareViewModel.class);

        View view = inflater.inflate(R.layout.fragment_square, container, false);
        rootView = view.findViewById(R.id.rootView);
        viewStub = (ViewStub) view.findViewById(R.id.errorNetWork);
        successNetWork = view.findViewById(R.id.successNetWork);
        bannerView = view.findViewById(R.id.bannerView);
        recommendSongsLayout = view.findViewById(R.id.rmSongsLayout);
        newSongsLayout = view.findViewById(R.id.newSongsLayout);
        musicPlayBar = view.findViewById(R.id.musicPlayBar);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recommendSongsLayout.setLayoutManager(horizontalLayoutManager);
        skeletonScreen = Skeleton.bind(recommendSongsLayout)
                .adapter(rmImageAdapter)
                .shimmer(false)
                .frozen(false)
                .duration(1200)
                .count(5)
                .load(R.layout.skeleton_item_rmsong)
                .show();

        newSongsLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!NetWorkUtil.isNetWorkConnected(getContext())) {
                    ToastUtil.showShort(getContext(), "当前网络不可用");
                    return;
                }

                squareViewModel.getSongInfo(newSongs.get(i).getHash());
            }
        });

        return view;
    }

    @Override
    protected void fetchData() {
        if (viewStub.getVisibility() == View.VISIBLE) {
            viewStub.setVisibility(View.GONE);
        }
        if (successNetWork.getVisibility() == View.GONE) {
            successNetWork.setVisibility(View.VISIBLE);
            rootView.setOnClickListener(null);
        }
        squareViewModel.getWebContent();

        List<String> imageList = new LinkedList<>();
        imageList.add("http://img4.imgtn.bdimg.com/it/u=3470378741,1129719615&fm=26&gp=0.jpg");
        imageList.add("http://img5.imgtn.bdimg.com/it/u=2409659293,2053768022&fm=26&gp=0.jpg");
        imageList.add("http://img0.imgtn.bdimg.com/it/u=723043357,2631433199&fm=26&gp=0.jpg");
        imageList.add("http://img3.imgtn.bdimg.com/it/u=2625248896,4104164811&fm=26&gp=0.jpg");
        imageList.add("http://img3.imgtn.bdimg.com/it/u=2275476808,3744090945&fm=26&gp=0.jpg");

        List<ImageView> imageViewList = new LinkedList<>();
        for (int i = 0; i < imageList.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageViewList.add(imageView);
        }

        bannerAdapter = new BannerAdapter(imageList, imageViewList);
        bannerView.setAdapter(bannerAdapter);
        squareViewModel.getRMSongs().observe(this, new Observer<List<RMSong>>() {
            @Override
            public void onChanged(final List<RMSong> rmSongs) {
                rmImageAdapter = new RmImageAdapter(rmSongs);
                rmImageAdapter.setOnItemClickListener(new RmImageAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO: 2020/3/7 推荐歌曲详细页
                    }
                });
                skeletonScreen.hide();
                recommendSongsLayout.setAdapter(rmImageAdapter);
            }
        });

        squareViewModel.getLiveNewSongs().observe(this, new Observer<List<NewSong>>() {
            @Override
            public void onChanged(List<NewSong> newSongList) {
                newSongs = newSongList;
                newSongsLayout.setAdapter(new SingleSongAdapter(getContext(), R.layout.item_song, newSongList));
            }
        });

        squareViewModel.getSongInfo().observe(this, new Observer<SongInfo>() {
            @Override
            public void onChanged(SongInfo songInfo) {
                MediaPlayUtil.getInstance().start(songInfo.data.playUrl);
                EventBus.getDefault().post(new MusicStartEvent(songInfo));
            }
        });
    }

    @Override
    protected void showErrorPage() {
        successNetWork.setVisibility(View.GONE);
        viewStub.setVisibility(View.VISIBLE);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryToFetchData();
            }
        });
    }
}
