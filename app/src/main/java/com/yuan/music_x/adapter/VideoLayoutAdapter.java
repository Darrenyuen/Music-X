package com.yuan.music_x.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yuan.music_x.R;
import com.yuan.music_x.bean.VideoData;

import java.util.List;

/**
 * yuan
 * 2020/3/28
 **/
public class VideoLayoutAdapter extends RecyclerView.Adapter<VideoLayoutAdapter.ViewHolder> {

    private Context context;

    private List<VideoData.DataBean.ListBean> videoDataList;

    public VideoLayoutAdapter(Context context, List<VideoData.DataBean.ListBean> videoDataList) {
        super();
        this.context = context;
        this.videoDataList = videoDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_video, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoData.DataBean.ListBean listBean = videoDataList.get(position);
        Glide.with(context).load(listBean.getImgUrl()).into(holder.videoImage);
    }

    @Override
    public int getItemCount() {
        return videoDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView videoImage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoImage = itemView.findViewById(R.id.videoImage);
        }

    }
}
