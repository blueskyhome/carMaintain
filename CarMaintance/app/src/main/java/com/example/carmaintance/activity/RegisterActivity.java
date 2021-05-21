package com.example.carmaintance.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.carmaintance.MyApplication;
import com.example.carmaintance.R;
import com.example.carmaintance.util.HttpUtil;
import com.example.carmaintance.util.TimeUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText acount;
    private EditText password;
    private EditText phone_number;
    private EditText nickname;
    private EditText brand;
    private Button register;
    private ImageView backImage;
    private MyApplication application;
    private String url;
    private String TAG = "RegisterActivity";
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigister);
        application = (MyApplication) getApplication();
        url = application.getUrl() + "/user/register";
        handler = new Handler();

        acount = (EditText) findViewById(R.id.register_account);
        password = (EditText) findViewById(R.id.register_password);
        phone_number = (EditText) findViewById(R.id.register_phone_number);
        nickname = (EditText) findViewById(R.id.register_nickname);
        brand = (EditText) findViewById(R.id.register_brand);
        register = (Button) findViewById(R.id.register_button);
        backImage = (ImageView) findViewById(R.id.back_button);
        register.setOnClickListener(this);
        backImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_button:
                if (TextUtils.isEmpty(acount.getText()) && TextUtils.isEmpty(password.getText())
                        && TextUtils.isEmpty(phone_number.getText()) && TextUtils.isEmpty(nickname.getText())
                        && TextUtils.isEmpty(brand.getText())) {
                    Toast.makeText(RegisterActivity.this, "请填写完整信息", Toast.LENGTH_SHORT).show();
                } else {
                    register();
                }
                break;
            case R.id.back_button:
                finish();
                break;
        }
    }

    private void register() {
        RequestBody requestBody = new FormBody.Builder()
                .add("id", "0")
                .add("username", acount.getText().toString())
                .add("password", password.getText().toString())
                .add("nickname", nickname.getText().toString())
                .add("head_url", "https://ww1.sinaimg.cn/large/0077HGE3ly1gqlm268iv7j30mj0kuta6.jpg")
                .add("phone_number", phone_number.getText().toString())
                .add("car_brand", brand.getText().toString())
                .add("register_time", TimeUtil.getCurrentTime())
                .build();
        HttpUtil.postOkHttpRequest(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "请求失败");
                Log.e(TAG, Log.getStackTraceString(e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (result.equals("0")){
                            Toast.makeText(application, "账号已注册", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "请求成功");
                            finish();
                        }
                    }
                });
            }
        });
    }
}