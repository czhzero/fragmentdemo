package com.chen.fragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chen.fragment.R;
import com.chen.fragment.activity.LoginActivity;
import com.chen.fragment.utils.Constant;


/**
 * Created by chenzhaohua on 16/11/7.
 */
public class StaticFragmentA extends BaseFragment {


    private TextView tv_tel;
    private TextView tv_logout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_static_a, null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }


    private void initView(View view) {
        tv_tel = (TextView) view.findViewById(R.id.tv_tel);
        tv_logout = (TextView) view.findViewById(R.id.tv_logout);
        tv_tel.setText(mSharedPrefs.getStringSP(Constant.SharedPrefrence.TEL, "没有号码"));
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSharedPrefs.setStringSP(Constant.SharedPrefrence.TOKEN, "");
                mSharedPrefs.setStringSP(Constant.SharedPrefrence.TEL, "");
                Intent intent = new Intent();
                intent.setClass(mContext, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }


}
