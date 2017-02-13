package com.chen.fragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.fragment.R;
import com.chen.fragment.api.Server;
import com.chen.fragment.model.CodeCallback;
import com.chen.fragment.model.LoginCallback;
import com.chen.fragment.utils.Constant;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by chenzhaohua on 16/11/9.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_tel;
    private EditText et_code;
    private TextView tv_send;
    private TextView tv_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }


    private void initView() {
        et_tel = (EditText) findViewById(R.id.et_tel);
        et_code = (EditText) findViewById(R.id.et_code);
        tv_send = (TextView) findViewById(R.id.tv_send);
        tv_login = (TextView) findViewById(R.id.tv_login);
        tv_send.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_send:
                requestCode();
                break;
            case R.id.tv_login:
                requestLogin();
                break;
        }
    }


    private void requestCode() {

        String tel = et_tel.getText().toString().trim();

        if (TextUtils.isEmpty(tel) || tel.length() != 11) {
            Toast.makeText(mContext, "请输入正确的电话号码", Toast.LENGTH_SHORT).show();
            return;
        }


        Server.getServerApi(mContext).requestCode(tel, new Callback<CodeCallback>() {
            @Override
            public void success(CodeCallback codeCallback, Response response) {
                Toast.makeText(mContext, "验证码已发送", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(mContext, "获取验证码失败", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void requestLogin() {

        String tel = et_tel.getText().toString().trim();

        if (TextUtils.isEmpty(tel) || tel.length() != 11) {
            Toast.makeText(mContext, "请输入正确的电话号码", Toast.LENGTH_SHORT).show();
            return;
        }


        String code = et_code.getText().toString().trim();

        if (TextUtils.isEmpty(code) || code.length() != 4) {
            Toast.makeText(mContext, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
            return;
        }


        Server.getServerApi(mContext).requestLogin(tel, code, new Callback<LoginCallback>() {

            @Override
            public void success(LoginCallback loginCallback, Response response) {
                mSharedPrefs.setStringSP(Constant.SharedPrefrence.TEL, loginCallback.tel);
                mSharedPrefs.setStringSP(Constant.SharedPrefrence.TOKEN, loginCallback.token);
                mSharedPrefs.setStringSP(Constant.SharedPrefrence.MAN_ID, loginCallback.man_id);
                Server.resetServerApi();
                jumpTaskList();
            }

            @Override
            public void failure(RetrofitError error) {

            }

        });


    }


    private void jumpTaskList() {
        Intent intent = new Intent();
        intent.setClass(mContext, TaskListActivity.class);
        startActivity(intent);
        finish();
    }


}
