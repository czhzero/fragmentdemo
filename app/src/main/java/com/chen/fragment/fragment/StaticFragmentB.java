package com.chen.fragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.chen.fragment.R;
import com.chen.fragment.activity.LoginActivity;
import com.chen.fragment.adapter.CommonAdapter;
import com.chen.fragment.adapter.ViewHolder;
import com.chen.fragment.utils.Constant;

import java.util.ArrayList;


/**
 * Created by chenzhaohua on 16/11/7.
 */
public class StaticFragmentB extends BaseFragment {


    private ListView lv_task_list;
    private CommonAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_static_b, null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }


    private void initView(View view) {
        lv_task_list = (ListView) view.findViewById(R.id.lv_task_list);

        final ArrayList<String> testList = new ArrayList<>();
        testList.add("任务  A");
        testList.add("任务  A");
        testList.add("任务  B");

        mAdapter = new CommonAdapter<String>(getActivity(), testList, R.layout.listitem_task) {
            @Override
            public void convert(ViewHolder helper, String item) {
                TextView tv_name = helper.getView(R.id.tv_name);
                tv_name.setText(item);
            }
        };

        lv_task_list.setAdapter(mAdapter);

        lv_task_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });

    }

}
