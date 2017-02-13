package com.chen.fragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.chen.fragment.R;
import com.chen.fragment.activity.TaskHourSignActivity;
import com.chen.fragment.activity.TaskLocationSignActivity;
import com.chen.fragment.activity.TaskScanSignActivity;
import com.chen.fragment.adapter.CommonAdapter;
import com.chen.fragment.adapter.ViewHolder;
import com.chen.fragment.api.Server;
import com.chen.fragment.model.TaskListCallback;
import com.chen.fragment.model.TaskListContent;
import com.chen.fragment.utils.Constant;
import com.chen.fragment.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by chenzhaohua on 16/11/7.
 */
public class StaticFragmentB extends BaseFragment {


    private ListView lv_task_list;
    private CommonAdapter mAdapter;
    private List<TaskListContent> mList;
    private TextView tv_empty;
    private TextView tv_start_work;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_static_b, null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView(view);
        requestTaskList();
    }

    private void initData() {
        mList = new ArrayList<>();
    }


    private void initView(View view) {

        tv_start_work = (TextView) view.findViewById(R.id.tv_start_work);
        tv_empty = (TextView) view.findViewById(R.id.tv_empty);

        lv_task_list = (ListView) view.findViewById(R.id.lv_task_list);

        lv_task_list.setAdapter(mAdapter = new CommonAdapter<TaskListContent>(getActivity(),
                mList, R.layout.listitem_task) {
            @Override
            public void convert(ViewHolder helper, TaskListContent item) {
                TextView tv_name = helper.getView(R.id.tv_name);
                TextView tv_time = helper.getView(R.id.tv_time);
                TextView tv_status = helper.getView(R.id.tv_status);


                tv_name.setText(item.title);
                tv_time.setText(TimeUtils.formatTo(item.arrivalTime, "yyyy-MM-dd"));


                switch (item.status) {
                    case Constant.TaskStatus.CREATE:
                        tv_status.setText("正在进行中");
                        tv_status.setTextColor(mContext.getResources().getColor(R.color.green_66bb6a));
                        tv_name.setTextColor(mContext.getResources().getColor(R.color.grey_201f1f));
                        tv_time.setTextColor(mContext.getResources().getColor(R.color.grey_201f1f));
                        break;
                    case Constant.TaskStatus.CLOSE:
                        tv_status.setText("任务已关闭");
                        tv_status.setTextColor(mContext.getResources().getColor(R.color.grey_84898f));
                        tv_name.setTextColor(mContext.getResources().getColor(R.color.grey_84898f));
                        tv_time.setTextColor(mContext.getResources().getColor(R.color.grey_84898f));
                        break;
                    case Constant.TaskStatus.COMPLETE:
                        tv_status.setText("任务已完成");
                        tv_status.setTextColor(mContext.getResources().getColor(R.color.grey_84898f));
                        tv_name.setTextColor(mContext.getResources().getColor(R.color.grey_84898f));
                        tv_time.setTextColor(mContext.getResources().getColor(R.color.grey_84898f));
                        break;
                }
            }
        });

        lv_task_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(TextUtils.isEmpty(mList.get(position).arrivalAddress)) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, TaskScanSignActivity.class);
                    intent.putExtra("missionId", mList.get(position).missionId);
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent();
                    intent.setClass(mContext, TaskLocationSignActivity.class);
                    intent.putExtra("missionId", mList.get(position).missionId);
                    mContext.startActivity(intent);
                }
            }
        });

        tv_start_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, TaskHourSignActivity.class);
                mContext.startActivity(intent);
            }
        });

    }


    private void requestTaskList() {
        Server.getServerApi(mContext).requestTaskList(1, new Callback<TaskListCallback>() {
            @Override
            public void success(TaskListCallback taskListCallback, Response response) {
                if (taskListCallback != null) {
                    updateView(taskListCallback.content);
                } else {
                    tv_empty.setVisibility(View.VISIBLE);
                    lv_task_list.setVisibility(View.GONE);
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });

    }


    private void updateView(List<TaskListContent> list) {

        if(list == null || list.size()<=0) {
            return;
        }

        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }




}
