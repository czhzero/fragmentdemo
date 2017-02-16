package com.chen.fragment.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chen.fragment.utils.Constant;
import com.chen.fragment.utils.LogUtils;
import com.chen.fragment.utils.SharedPrefsUtil;

/**
 * Created by chenzhaohua on 16/11/7.
 */
public class BaseFragment extends Fragment {

    protected SharedPrefsUtil mSharedPrefs;
    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mSharedPrefs = new SharedPrefsUtil(getContext().getApplicationContext(), Constant.SharedPrefrence.SHARED_NAME);
        LogUtils.d(this.getClass().getSimpleName() + " onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.d(this.getClass().getSimpleName() + " onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LogUtils.d(this.getClass().getSimpleName() + " onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.d(this.getClass().getSimpleName() + " onActivityCreated");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.d(this.getClass().getSimpleName() + " onActivityResult");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.d(this.getClass().getSimpleName() + " onAttach");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.d(this.getClass().getSimpleName() + " onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(this.getClass().getSimpleName() + " onResume");
    }


    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d(this.getClass().getSimpleName() + " onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.d(this.getClass().getSimpleName() + " onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(this.getClass().getSimpleName() + " onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(this.getClass().getSimpleName() + " onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.d(this.getClass().getSimpleName() + " onDetach");
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtils.d(this.getClass().getSimpleName() + " onConfigurationChanged");
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.d(this.getClass().getSimpleName() + " onSaveInstanceState");
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        LogUtils.d(this.getClass().getSimpleName() + " onViewStateRestored");
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        LogUtils.d(this.getClass().getSimpleName() + " setArguments");
    }


}
