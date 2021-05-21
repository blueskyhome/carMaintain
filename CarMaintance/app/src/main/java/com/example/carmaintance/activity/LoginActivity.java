package com.example.carmaintance.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carmaintance.R;
import com.example.carmaintance.MyApplication;
import com.example.carmaintance.bean.User;
import com.example.carmaintance.util.HttpUtil;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText acountText;
    private EditText passwordText;
    private Button loginButton;
    private TextView registerText;
    private MyApplication application;
    private String url;
    private String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        application = (MyApplication) getApplication();
        url = application.getUrl() + "/user/login";
        acountText = (EditText) findViewById(R.id.account);
        passwordText = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login_button);
        registerText = (TextView) findViewById(R.id.register_text);
        loginButton.setOnClickListener(this);
        registerText.setOnClickListener(this);
    }

    private void initView() {
        SharedPreferences preferences = getSharedPreferences("login_info", MODE_PRIVATE);
        if (preferences.getInt("id", 0) > 0) {
            Intent intent = new Intent(this, UserMainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                if (!TextUtils.isEmpty(acountText.getText()) && !TextUtils.isEmpty(passwordText.getText()))
                    login();
                else
                    Toast.makeText(this, "请输入账号密码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.register_text:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;

        }
    }

    private void login() {
        RequestBody requestBody = new FormBody.Builder()
                .add("username", acountText.getText().toString())
                .add("password", passwordText.getText().toString())
                .build();
        HttpUtil.postOkHttpRequest(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
               Log.e(TAG, Log.getStackTraceString(e));
               Log.e(TAG, "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
//                Log.e(TAG, result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        User user = gson.fromJson(result, User.class);
//                        Log.e(TAG, user.getPassword());
                        if (user.getPassword().equals(passwordText.getText().toString())) {
                            SharedPreferences preferences = getSharedPreferences("login_info", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt("id", user.getId());
                            editor.putString("username", user.getUsername());
                            editor.putString("password", user.getPassword());
                            editor.putString("nickname", user.getNickname());
                            editor.putString("head_url", user.getHead_url());
                            editor.putString("phone_number", user.getPhone_number());
                            editor.putString("car_brand", user.getCar_brand());
                            editor.putString("register_time", user.getRegister_time());
                            boolean commit = editor.commit();
                            Log.i(TAG, "userLogin: commitRet = " + commit);
                            Intent intent = new Intent(LoginActivity.this, UserMainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "账号密码不正确", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}