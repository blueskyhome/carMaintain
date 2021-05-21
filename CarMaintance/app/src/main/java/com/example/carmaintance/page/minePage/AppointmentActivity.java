package com.example.carmaintance.page.minePage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carmaintance.MyApplication;
import com.example.carmaintance.R;
import com.example.carmaintance.activity.BaseActivity;
import com.example.carmaintance.adapter.AppointmentAdapter;
import com.example.carmaintance.bean.Appointment;
import com.example.carmaintance.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AppointmentActivity extends BaseActivity {

    private List<Appointment> appointmentList = new ArrayList<>();
    private MyApplication application;
    private String url;
    private String TAG = "AppointmentActivity";
    private RecyclerView recyclerView;
    private AppointmentAdapter adapter;
    private TextView appointmentTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        Bundle bundle = this.getIntent().getExtras();
        application = (MyApplication) getApplication();
        url = application.getUrl() + "/appointment/userCompleteAppointment";
        String complete = bundle.getString("complete");
        appointmentTitle = (TextView) findViewById(R.id.appointment_title);
        if (complete.equals("0")) {
            appointmentTitle.setText("预约中");
        } else {
            appointmentTitle.setText("已完成");
        }
        recyclerView  = (RecyclerView) findViewById(R.id.appointment_recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AppointmentAdapter(this);
        recyclerView.setAdapter(adapter);
        initDate(complete);
    }

    private void initDate (String complete) {
        SharedPreferences preferences = getSharedPreferences("login_info", MODE_PRIVATE);
        String username = preferences.getString("username", "用户名");
        Log.e(TAG, username);
        Log.e(TAG, complete);
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("complete", complete)
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
                Log.e(TAG, result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        appointmentList = gson.fromJson(result, new TypeToken<List<Appointment>>(){}.getType());
                        adapter.addDate(appointmentList);
                    }
                });
            }
        });
    }
}