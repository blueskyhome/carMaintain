package com.example.carmaintance.page.minePage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.carmaintance.R;
import com.example.carmaintance.activity.LoginActivity;
import com.example.carmaintance.page.BaseFragment;

import static android.content.Context.MODE_PRIVATE;


public class MineFragment extends BaseFragment implements View.OnClickListener {

    private View mineView;
    private Activity bindActivity;
    private ImageView headImg;
    private TextView nickname;
    private TextView phone_number;
    private View appointingView;
    private View appointedView;
    private View evaluateView;
    private View saleView;
    private View followView;
    private View helpView;
    private View opinionView;
    private View aboutView;
    private Button account_button;
    private String TAG = "MineFragment";
    private boolean loginState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         mineView = inflater.inflate(R.layout.fragment_mine, container, false);
        initView(mineView);
        return mineView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        appointingView.setOnClickListener(this);
        appointedView.setOnClickListener(this);
        evaluateView.setOnClickListener(this);
        saleView.setOnClickListener(this);
        followView.setOnClickListener(this);
        helpView.setOnClickListener(this);
        opinionView.setOnClickListener(this);
        aboutView.setOnClickListener(this);
        account_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_button:
                SharedPreferences preferences = bindActivity.getSharedPreferences("login_info", MODE_PRIVATE);
                preferences.edit().clear().commit();
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                getActivity().finish();
                startActivity(loginIntent);
                break;
            case R.id.appointing_tab:
                Intent appointingIntent = new Intent(bindActivity, AppointmentActivity.class);
                appointingIntent.putExtra("complete", "0");
                startActivity(appointingIntent);
                break;
            case R.id.appointed_tab:
                Intent appointedIntent = new Intent(bindActivity, AppointmentActivity.class);
                appointedIntent.putExtra("complete", "1");
                startActivity(appointedIntent);
        }
    }

    private void initView(View view){
        bindActivity = getActivity();
        headImg = (ImageView)view.findViewById(R.id.head_img);
        nickname = (TextView)view.findViewById(R.id.nickname);
        phone_number = (TextView) view.findViewById(R.id.phone_number);
        appointingView = (View) view.findViewById(R.id.appointing_tab);
        appointedView = (View) view.findViewById(R.id.appointed_tab);
        evaluateView = (View) view.findViewById(R.id.evaluate_tab);
        saleView = (View) view.findViewById(R.id.sale_tab);
        followView = (View) view.findViewById(R.id.follow);
        helpView = (View) view.findViewById(R.id.help);
        opinionView = (View) view.findViewById(R.id.opinion);
        aboutView = (View) view.findViewById(R.id.about_mine);
        account_button = (Button) view.findViewById(R.id.account_button);

        SharedPreferences login_info = bindActivity.getSharedPreferences("login_info", MODE_PRIVATE);
        nickname.setText(login_info.getString("nickname", ""));
        phone_number.setText(login_info.getString("phone_number", ""));
        Glide.with(mineView).load(login_info.getString("head_url", "")).into(headImg);
    }
}