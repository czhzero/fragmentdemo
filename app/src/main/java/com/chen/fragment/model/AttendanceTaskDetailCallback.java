package com.chen.fragment.model;

import java.util.List;

/**
 * Created by chenzhaohua on 16/11/15.
 */
public class AttendanceTaskDetailCallback {

    public Content content;

    public String message;
    public String code;

    public static class Content {

        public MissionInfo missionInfo;

        public List<TaskLog> missionLog;
    }

    public static class MissionInfo {
        public double rangeRadius;
        public String missionId;
        public double attendanceLng;
        public String arrivalEndTime;                   // 最迟签到时间 超过该时间扣钱
        public String attendanceAddress;
        public String arrivalTime;                      // 任务签到时间
        public String statusName;
        public double attendanceLat;
        public String signType;
        public String leaveTime;
        public String currentStatus;
        public String messageDetail;
        public List<String> targets;
    }

    public static class TaskLog {
        public String id;
        public String time;
        public String name;
        public String status;
    }

    public static class AttendanceStatus {
        public static final String UNSTART = "UNSTART";        // 未开始
        public static final String ARRIVAL = "ARRIVAL";        // 已到达
        public static final String LEAVE = "LEAVE";            // 已离开
        public static final String OUTING = "OUTING";          // 外勤
        public static final String ONAUDIT = "ONAUDIT";        // 待审批
    }

    public static class SignInType {
        public static final String LOCATION = "1";      // 位置签到
        public static final String SCAN = "2";          // 关系签到
    }

}
