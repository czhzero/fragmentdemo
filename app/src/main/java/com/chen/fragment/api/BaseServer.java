package com.chen.fragment.api;


import android.content.Context;
import java.lang.reflect.Field;
import java.util.HashMap;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by chenzhaohua on 16/7/11.
 *
 * 服务器接口基类
 *
 */
public abstract class BaseServer {

    protected static final String HEAD_KEY_TOKEN = "Authorization";
    protected static final String HEAD_KEY_APPVERSION = "app-version";
    protected static final String HEAD_KEY_APPNAME = "app-name";
    protected static final String HEAD_KEY_UG = "User-Agent";
    protected static Context sContext;




    protected <T> T createServerApi(Context context, Class<T> t) {
        sContext = context;
        RestAdapter.Builder builder = initRequestHeader(context, getInternalServer(), getRequestHead());
        RestAdapter restAdapter = builder.build();
        return restAdapter.create(t);
    }

    /**
     * 获取服务器URL
     * @return
     */
    protected abstract String getInternalServer();


    /**
     * 获取Http Head
     *
     * @return
     */
    protected abstract HashMap<String, String> getRequestHead();


    /**
     * 初始化请求头
     *
     * @param context
     * @param server
     * @return
     */
    private RestAdapter.Builder initRequestHeader(final Context context, final String server,
                                                  final HashMap<String, String> heads) {
        RequestInterceptor requestInterceptor = null;

        if (heads == null) {
            return null;
        }

        final String appVersion = heads.get(HEAD_KEY_APPVERSION);
        final String token = heads.get(HEAD_KEY_TOKEN);
        final String userAgent = heads.get(HEAD_KEY_UG);

        try {
            requestInterceptor = new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade requestFacade) {
                    requestFacade.addHeader("Authorization", "token " + token);
                    requestFacade.addHeader("app-version", appVersion);
                    requestFacade.addHeader("User-Agent", userAgent);
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(server)
                .setClient(new OkClient());


        if (getBuildDebug(context)) {
            builder.setLogLevel(RestAdapter.LogLevel.FULL);
        } else {
            builder.setLogLevel(RestAdapter.LogLevel.NONE);
        }


        if (requestInterceptor != null) {
            builder.setRequestInterceptor(requestInterceptor);
        }

        return builder;

    }



    /**
     * 反射方法获取app的BulidConfig的DEBUG值
     *
     * @param context
     * @return
     */
    private boolean getBuildDebug(Context context) {
        try {
            Class<?> clazz = Class.forName(context.getPackageName() + ".BuildConfig");
            Field field = clazz.getField("DEBUG");
            return (boolean) field.get(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }






}
