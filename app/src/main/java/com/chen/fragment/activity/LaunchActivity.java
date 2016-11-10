package com.chen.fragment.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.chen.fragment.utils.Constant;

/**
 * Created by chenzhaohua on 16/11/9.
 */
public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String token = mSharedPrefs.getStringSP(Constant.SharedPrefrence.TOKEN, "");


        Intent intent = new Intent();

        if (TextUtils.isEmpty(token)) {
            intent.setClass(mContext, LoginActivity.class);
        } else {
            intent.setClass(mContext, TaskListActivity.class);
        }

        startActivity(intent);
        finish();
    }
}
