package com.yuan.music_x.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.yuan.music_x.R;
import com.yuan.music_x.bean.NewSong;

import java.util.List;

/**
 * yuan
 * 2020/3/4
 **/
public class SingleSongAdapter extends ArrayAdapter {

    private int resourceId;
    private List<NewSong> newSongList;

    public SingleSongAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        resourceId = resource;
        newSongList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NewSong newSong = newSongList.get(position);
        View view;
        ViewHolder viewHolder;
        if (convertView != null) {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        String singerName = newSong.Filename.split("-")[0].trim();
        String songName = newSong.Filename.split("-")[1].trim();
        viewHolder.numberTextView.setText(position + 1 + "");
        viewHolder.songNameTextView.setText(songName);
        viewHolder.singerNameTextView.setText(singerName);

        return view;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView numberTextView;
        TextView songNameTextView;
        TextView singerNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            numberTextView = itemView.findViewById(R.id.number);
            songNameTextView = itemView.findViewById(R.id.songNameTextView);
            singerNameTextView = itemView.findViewById(R.id.singerTextView);
        }

    }
}
