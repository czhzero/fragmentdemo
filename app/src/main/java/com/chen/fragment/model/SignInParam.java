package com.chen.fragment.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chenzhaohua on 16/11/15.
 */
public class SignInParam {

    public String missionId;
    public double locationLat;
    public double locationLng;
    public String locationAddress;
    @SerializedName("effectorId")
    public String leaderId;
}
