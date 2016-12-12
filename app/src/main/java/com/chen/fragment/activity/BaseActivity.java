package com.chen.fragment.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chen.fragment.utils.Constant;
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
    }
}
