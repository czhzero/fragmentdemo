package com.chen.fragment.model;

import java.io.Serializable;



public class AttendanceHourSign implements Serializable {

    public static final String CURRENT_STATUS_UNWORK_UNSTART = "UNWORK_UNSTART";//未上班-没有点击开始上班状态

    public static final String CURRENT_STATUS_UNWORK_REST = "UNWORK_REST";//未上班-休息状态

    public static final String CURRENT_STATUS_WORKING = "WORKING";//正在上班

    public static final String CURRENT_STATUS_WAIT_UNWORK = "WAIT_UNWORK";//待下班

    public static final String WORKING_WAIT_WAIT_UNWORK = "WORKING_WAIT_WAIT_UNWORK";//正在上班等待休息

    /**
     * "currentStatus": "UNSTART",
     * "currentStatusName": "未上班",
     * "leftBtnDisabled": false,
     * "leftBtnName": "继续上班",
     * "missionId": "wdnOC98RaRRA6JX-m6FUubow",
     * "rightBtnDisabled": true,
     * "rightBtnName": "我要休息",
     * "timePeriod": "15:55-16:55",
     * "workHour": 0,
     * "workText": "请确认%s是否上班"
     */

    public String currentStatus;//当前状态

    public String currentStatusName;//当前状态名字

    public double workHour;//今日累计上班小时数

    public String leftBtnName;//左边按钮名字

    public String rightBtnName;//右边按钮名字

    public boolean leftBtnDisabled;//左边按钮是否不可点击

    public boolean rightBtnDisabled;//右边按钮是否不可点击

    public String workText;//确认工作文案

    public Double attendanceLng;

    public Double attendanceLat;

    public String attendanceAddress;

    public String missionId;

    public String timePeriod;

    public String title;

}
