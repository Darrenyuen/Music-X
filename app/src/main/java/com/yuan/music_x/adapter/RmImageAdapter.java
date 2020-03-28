package com.yuan.music_x.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yuan.music_x.R;
import com.yuan.music_x.bean.RMSong;
import com.yuan.music_x.util.ConvertUtil;
import com.yuan.music_x.widget.RecommendImageView;

import java.util.List;

/**
 * yuan
 * 2020/2/28
 **/
public class RmImageAdapter extends RecyclerView.Adapter<RmImageAdapter.ViewHolder> {

    private Context context;

    private List<RMSong> rmSongList;

    private OnItemClickListener onItemClickListener;

    public RmImageAdapter(List<RMSong> rmSongList) {
        super();
        this.rmSongList = rmSongList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(new LinearLayout(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        RMSong rmSong = rmSongList.get(position);
        holder.imageView = new RecommendImageView(context, rmSongList.get(position).getNum());
        Glide.with(context).load(rmSong.getImageURL()).into(holder.imageView);
        holder.imageView.setClipBounds(new Rect(0, 0, ConvertUtil.dpToPx(context, 150), ConvertUtil.dpToPx(context, 150)));
        holder.songInfo.setText(rmSong.getSongListName() + " | " + rmSong.getSongListSinger());
        holder.addView();
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return rmSongList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        RecommendImageView imageView;
        TextView songInfo;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            itemView.setLayoutParams(new ViewGroup.LayoutParams(ConvertUtil.dpToPx(context, 155), ViewGroup.LayoutParams.WRAP_CONTENT));
            itemView.setPadding(ConvertUtil.dpToPx(context, 5), ConvertUtil.dpToPx(context, 5), ConvertUtil.dpToPx(context, 5), ConvertUtil.dpToPx(context, 5));
            ((LinearLayout) itemView).setOrientation(LinearLayout.VERTICAL);
            songInfo = new TextView(itemView.getContext());
            songInfo.setMaxLines(2);
            songInfo.setTextColor(context.getResources().getColor(R.color.text_dark));
        }

        private void addView() {
            if (imageView.getParent() != null || songInfo.getParent() != null) return;
            ((LinearLayout) itemView).addView(imageView);
            ((LinearLayout) itemView).addView(songInfo);
        }

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}
