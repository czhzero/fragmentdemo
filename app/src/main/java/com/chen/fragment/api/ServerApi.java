package com.chen.fragment.api;


import com.chen.fragment.model.AttendanceTaskDetailCallback;
import com.chen.fragment.model.CodeCallback;
import com.chen.fragment.model.LoginCallback;
import com.chen.fragment.model.SignInCallback;
import com.chen.fragment.model.SignInParam;
import com.chen.fragment.model.TaskListCallback;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by chenzhaohua 2016/1/4
 */
public interface ServerApi {


    /**
     * 获取验证码
     *
     * @param phone
     * @param callback
     */
    @GET("/WindCloud/account/register/code")
    void requestCode(@Query("tel") String phone, Callback<CodeCallback> callback);


    /**
     * 登录账户
     *
     * @param phone
     * @param code
     * @param callback
     */
    @GET("/WindCloud/account/register/login")
    void requestLogin(@Query("tel") String phone, @Query("code") String code,
                      Callback<LoginCallback> callback);


    /**
     * 获取任务列表
     *
     * @param page
     * @param callback
     */
    @GET("/WindMission/mission/management/list/page")
    void requestTaskList(@Query("pageno") int page, Callback<TaskListCallback> callback);


    /**
     * 获取出勤任务详情
     *
     * @param missionId
     * @param callback
     */
    @GET("/WindMission/mission/attendance/getSignDetail")
    void requestAttendanceTaskDetail(@Query("missionId") String missionId,
                                     Callback<AttendanceTaskDetailCallback> callback);


    /**
     * 坐标/扫码 签到
     *
     * @param param
     * @param callback
     */
    @POST("/WindMission/mission/attendance/operateSign")
    void signIn(@Body SignInParam param, Callback<SignInCallback> callback);


}
