package com.chen.fragment.model;


import java.util.List;

/**
 * Created by chenzhaohua on 16/11/14.
 */
public class TaskListContent {

    public static final int ACT_NORMAL = 1;
    public static final int ACT_DRILL = 2;

    public String missionId;                   //任务Id
    public String title;                       //任务名称
    public String arrivalTime;                 //任务到期时间
    public String type;                        //任务类型
    public String status;                      //任务状态

    public double arrivalLat;
    public double arrivalLng;
    public String arrivalAddress;

    public String completeTime;
    public String expendHour;
    public String createTime;
    public List<Tag> tagList;
    public boolean isClassify;
    public boolean isNearest;
    public int act;
    public String orderUserType;


    public static class Tag {
        public String tagType;
        public String tagName;
    }

}
