package com.chen.fragment.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chen.fragment.utils.Constant;
import com.chen.fragment.utils.LogUtils;
import com.chen.fragment.utils.SharedPrefsUtil;

/**
 * Created by chenzhaohua on 16/11/9.
 */
public class BaseActivity extends AppCompatActivity {

    protected SharedPrefsUtil mSharedPrefs;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mSharedPrefs = new SharedPrefsUtil(getApplicationContext(), Constant.SharedPrefrence.SHARED_NAME);
        LogUtils.d(this.getClass().getSimpleName() + " onCreate");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.d(this.getClass().getSimpleName() + " onActivityResult");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d(this.getClass().getSimpleName() + " onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d(this.getClass().getSimpleName() + " onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d(this.getClass().getSimpleName() + " onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d(this.getClass().getSimpleName() + " onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d(this.getClass().getSimpleName() + " onDestroy");
    }
}
