package com.yuan.music_x.base;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuan.music_x.util.NetWorkUtil;

/**
 * yuan
 * 2020/2/27
 **/
public abstract class BaseLazyFragment extends Fragment {
    
    protected String TAG = this.getClass().getSimpleName();

    protected Activity activity;

    private boolean isViewInitiated;

    private boolean isVisibleToUser;

    private boolean isDataInitiated;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint: ");
        this.isVisibleToUser = isVisibleToUser;
        tryToFetchData();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: try to request data");
        isViewInitiated = true;
        tryToFetchData();
    }

    protected void tryToFetchData() {
        tryToFetchData(false);
    }

    protected void tryToFetchData(boolean isForceUpdate) {
        Log.d(TAG, "tryToFetchData: " + isVisibleToUser + " " + isViewInitiated + " " + isDataInitiated + " " + isForceUpdate);
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || isForceUpdate)) {
            if (NetWorkUtil.isNetWorkConnected(getContext())) {
                fetchData();
                isDataInitiated = true;
            } else {
                showErrorPage();
            }
        }
    }

    protected abstract void fetchData();

    protected abstract void showErrorPage();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        Log.d(TAG, "onAttachFragment: ");
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
