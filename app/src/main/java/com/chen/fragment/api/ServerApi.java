package com.chen.fragment.api;


import com.chen.fragment.model.CodeCallback;
import com.chen.fragment.model.LoginCallback;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by chenzhaohua 2016/1/4
 */
public interface ServerApi {


    @GET("/WindCloud/account/register/code")
    void requestCode(@Query("tel") String phone, Callback<CodeCallback> callback);


    @GET("/WindCloud/account/register/login")
    void requestLogin(@Query("tel") String phone, @Query("code") String code,
                      Callback<LoginCallback> callback);

}
