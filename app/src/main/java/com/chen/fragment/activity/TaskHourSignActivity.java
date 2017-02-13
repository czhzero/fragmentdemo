package com.chen.fragment.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.fragment.R;
import com.chen.fragment.api.Server;
import com.chen.fragment.model.HourSignDetailCallback;
import com.chen.fragment.model.HourSignWorkParam;
import com.chen.fragment.model.SignInParam;
import com.chen.fragment.utils.Constant;


import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TaskHourSignActivity extends BaseActivity {


    private TextView tv_commit;
    private Spinner spinner;

    private List<SignInParam> mDefaultSignParamList;
    private List<String> mDefaultAddress;
    private int mSelectedPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_hour_sign);
        initData();
        initView();
    }


    private void initData() {
        initSignParam();
        initSpinnerData();
    }


    private void initSignParam() {

        mDefaultSignParamList = new ArrayList<>();

        SignInParam p1 = new SignInParam();
        p1.locationAddress = "浙江省杭州市滨江区宏飞路靠近艺霖音乐";
        p1.locationLng = 120.214506;
        p1.locationLat = 30.214142;

        SignInParam p2 = new SignInParam();
        p2.locationAddress = "江陵路2013号星耀城1期101号(么么哒美食对面)";
        p2.locationLng = 120.21466;
        p2.locationLat = 30.213917;

        SignInParam p3 = new SignInParam();
        p3.locationAddress = "杭州市滨江区西兴街道江陵路2028号星耀城3幢2层(武警医院)";
        p3.locationLng = 120.215597;
        p3.locationLat = 30.212829;

        SignInParam p4 = new SignInParam();
        p4.locationAddress = "杭州市滨江区江陵路2084号附近";
        p4.locationLng = 120.214922;
        p4.locationLat = 30.21318;

        mDefaultSignParamList.add(p1);
        mDefaultSignParamList.add(p2);
        mDefaultSignParamList.add(p3);
        mDefaultSignParamList.add(p4);

    }

    private void initSpinnerData() {
        mDefaultAddress = new ArrayList<>();
        mDefaultAddress.add("浙江省杭州市滨江区宏飞路靠近艺霖音乐");
        mDefaultAddress.add("江陵路2013号星耀城1期101号(么么哒美食对面)");
        mDefaultAddress.add("杭州市滨江区西兴街道江陵路2028号星耀城3幢2层(武警医院)");
        mDefaultAddress.add("杭州市滨江区江陵路2084号附近");
    }

    private void initView() {

        spinner = (Spinner) findViewById(R.id.spinner);

        tv_commit = (TextView) findViewById(R.id.tv_commit);


        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWork();
            }
        });

        //适配器
        ArrayAdapter arr_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mDefaultAddress);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);


        //注册事件
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                mSelectedPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "什么也没选", Toast.LENGTH_LONG).show();
            }

        });


    }


    private void startWork() {

        final HourSignWorkParam param = new HourSignWorkParam();
        param.attendanceAddress = mDefaultSignParamList.get(mSelectedPosition).locationAddress;
        param.attendanceLat = mDefaultSignParamList.get(mSelectedPosition).locationLat + "";
        param.attendanceLng = mDefaultSignParamList.get(mSelectedPosition).locationLng + "";
        param.executorUserId = mSharedPrefs.getStringSP(Constant.SharedPrefrence.MAN_ID, "");
        param.missionId = "";
        param.remark = "";

        Callback<HourSignDetailCallback> callback = new Callback<HourSignDetailCallback>() {


            @Override
            public void success(HourSignDetailCallback hourSignDetailCallback, Response response) {

                if (hourSignDetailCallback != null && hourSignDetailCallback.code == 1) {
                    Toast.makeText(mContext, "上班成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, hourSignDetailCallback.message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(mContext, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        Server.getServerApi(mContext).startWork(param, callback);
    }


}
