package com.example.carmaintance;

import android.app.Application;

public class MyApplication extends Application {

    private static final String baseUrl = "http://100.64.3.16:8080";
    private String url;

    @Override
    public void onCreate()
    {
        super.onCreate();
        setUrl(baseUrl); // 初始化全局变量
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }

}
