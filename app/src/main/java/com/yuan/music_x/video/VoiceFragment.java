package com.yuan.music_x.voice;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuan.music_x.R;
import com.yuan.music_x.base.BaseLazyFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class VoiceFragment extends BaseLazyFragment {


    public VoiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_voice, container, false);
    }

    @Override
    protected void fetchData() {

    }

    @Override
    protected void showErrorPage() {

    }
}
