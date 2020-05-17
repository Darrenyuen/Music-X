package com.yuan.music_x.mime;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuan.music_x.R;
import com.yuan.music_x.base.BaseLazyFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseLazyFragment {


    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    protected void fetchData() {

    }

    @Override
    protected void showErrorPage() {

    }
}
