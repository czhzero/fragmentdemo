package com.chen.fragment.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.fragment.R;
import com.chen.fragment.adapter.CommonAdapter;
import com.chen.fragment.adapter.ViewHolder;
import com.chen.fragment.api.Server;
import com.chen.fragment.model.AttendanceTaskDetailCallback;
import com.chen.fragment.model.SignInCallback;
import com.chen.fragment.model.SignInParam;
import com.chen.fragment.utils.TimeUtils;


import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TaskLocationSignActivity extends BaseActivity {


    private ListView lv_signin_log;
    private TextView tv_commit;
    private Spinner spinner;
    private CommonAdapter mLogAdapter;

    private List<AttendanceTaskDetailCallback.TaskLog> mLogList;
    private List<SignInParam> mDefaultSignParamList;
    private List<String> mDefaultAddress;
    private String mMissionId;
    private int mSelectedPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_location_sign);
        initData();
        initView();
        requestTaskDetail();
    }


    private void initData() {
        mMissionId = getIntent().getStringExtra("missionId");
        mLogList = new ArrayList<>();
        initSignParam();
        initSpinnerData();
    }


    private void initSignParam() {

        mDefaultSignParamList = new ArrayList<>();

        SignInParam p1 = new SignInParam();
        p1.missionId = mMissionId;
        p1.locationAddress = "江陵路2013号星耀城1期101号(么么哒美食对面)";
        p1.locationLng = 120.21466;
        p1.locationLat = 30.213917;

        SignInParam p2 = new SignInParam();
        p2.missionId = mMissionId;
        p2.locationAddress = "浙江省杭州市滨江区宏飞路靠近艺霖音乐";
        p2.locationLng = 120.214506;
        p2.locationLat = 30.214142;

        SignInParam p3 = new SignInParam();
        p3.missionId = mMissionId;
        p3.locationAddress = "杭州市滨江区西兴街道江陵路2028号星耀城3幢2层(武警医院)";
        p3.locationLng = 120.215597;
        p3.locationLat = 30.212829;

        SignInParam p4 = new SignInParam();
        p4.missionId = mMissionId;
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
        mDefaultAddress.add("江陵路2013号星耀城1期101号(么么哒美食对面)");
        mDefaultAddress.add("浙江省杭州市滨江区宏飞路靠近艺霖音乐");
        mDefaultAddress.add("杭州市滨江区西兴街道江陵路2028号星耀城3幢2层(武警医院)");
        mDefaultAddress.add("杭州市滨江区江陵路2084号附近");
    }

    private void initView() {
        lv_signin_log = (ListView) findViewById(R.id.lv_signin_log);

        spinner = (Spinner) findViewById(R.id.spinner);

        tv_commit = (TextView) findViewById(R.id.tv_commit);

        lv_signin_log.setAdapter(mLogAdapter = new CommonAdapter<AttendanceTaskDetailCallback.TaskLog>(
                mContext, mLogList, R.layout.listitem_task_log) {
            @Override
            public void convert(ViewHolder helper, AttendanceTaskDetailCallback.TaskLog item) {
                TextView tv_name = helper.getView(R.id.tv_name);
                TextView tv_time = helper.getView(R.id.tv_time);
                tv_name.setText(item.name);
                tv_time.setText(TimeUtils.formatTo(item.time, "yyyy-MM-dd HH:mm"));
            }
        });

        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSignIn();
            }
        });

        //适配器
        ArrayAdapter arr_adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mDefaultAddress);
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


    private void requestTaskDetail() {


        Server.getServerApi(mContext).requestAttendanceTaskDetail(mMissionId, new Callback<AttendanceTaskDetailCallback>() {
            @Override
            public void success(AttendanceTaskDetailCallback attendanceTaskDetailCallback, Response response) {

                if (attendanceTaskDetailCallback == null
                        || attendanceTaskDetailCallback.content == null) {
                    return;
                }

                if (attendanceTaskDetailCallback.content.missionLog != null
                        && attendanceTaskDetailCallback.content.missionLog.size() > 0) {
                    mLogList.clear();
                    mLogList.addAll(attendanceTaskDetailCallback.content.missionLog);
                    mLogAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }


    private void requestSignIn() {


        Server.getServerApi(mContext).signIn(mDefaultSignParamList.get(mSelectedPosition), new Callback<SignInCallback>() {
            @Override
            public void success(SignInCallback signInCallback, Response response) {
                if (signInCallback != null) {
                    Toast.makeText(mContext, signInCallback.message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(mContext, "签到失败", Toast.LENGTH_SHORT).show();
            }
        });

    }







}
