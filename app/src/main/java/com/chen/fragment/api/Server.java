package com.chen.fragment.api;

import android.content.Context;

import com.chen.fragment.utils.Constant;
import com.chen.fragment.utils.SharedPrefsUtil;

import java.util.HashMap;


public class Server extends BaseServer {

    private static final String SERVER_URL = "http://api.gomrwind.com:5000";


    private static ServerApi sServerApi;

    /**
     * 服务器接口对象
     *
     * @param context
     * @return
     */
    public static ServerApi getServerApi(final Context context) {
        if (sServerApi == null) {
            sServerApi = new Server().createServerApi(context.getApplicationContext(), ServerApi.class);
        }
        return sServerApi;
    }


    /**
     * 重置Api,当连接Header内容需要改变时候，必须调用reset方法
     */
    public static void resetServerApi() {
        sServerApi = null;
    }


    @Override
    public String getInternalServer() {
        return Server.getServer();
    }


    /**
     * 设置Http Head
     *
     * @return
     */
    @Override
    public HashMap<String, String> getRequestHead() {
        HashMap<String, String> headMap = new HashMap<>();
        if (sContext != null) {
            SharedPrefsUtil sharedPrefsUtil = new SharedPrefsUtil(sContext.getApplicationContext(),
                    Constant.SharedPrefrence.SHARED_NAME);
            headMap.put(HEAD_KEY_TOKEN, sharedPrefsUtil.getStringSP(Constant.SharedPrefrence.TOKEN, ""));
        }
        return headMap;
    }


    /**
     * 获取服务器地址
     *
     * @return
     */
    public static String getServer() {
        return SERVER_URL;
    }


}
