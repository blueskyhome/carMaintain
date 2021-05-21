package com.example.carmaintance.page.typePage;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carmaintance.R;
import com.example.carmaintance.page.BaseFragment;

public class TypeFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private TextView programSearch;
    private TextView beautyCleaning;
    private TextView routineMaintain;
    private TextView commonAccessory;
    private TextView airMaintain;
    private TextView brakeMaintain;
    private TextView waterTank;
    private TextView highMileage;
    private TextView interior;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_type, container, false);
        programSearch = (TextView) view.findViewById(R.id.program_search);
        beautyCleaning = (TextView) view.findViewById(R.id.beauty_cleaning);
        routineMaintain = (TextView) view.findViewById(R.id.routine_maintain);
        commonAccessory = (TextView) view.findViewById(R.id.common_accessory);
        airMaintain = (TextView) view.findViewById(R.id.air_maintenance);
        brakeMaintain = (TextView) view.findViewById(R.id.brake_maintain);
        waterTank = (TextView) view.findViewById(R.id.water_tank_maintenance);
        highMileage = (TextView) view.findViewById(R.id.high_mileage);
        interior = (TextView) view.findViewById(R.id.interior);

        programSearch.setOnClickListener(this);
        brakeMaintain.setOnClickListener(this);
        routineMaintain.setOnClickListener(this);
        commonAccessory.setOnClickListener(this);
        airMaintain.setOnClickListener(this);
        brakeMaintain.setOnClickListener(this);
        waterTank.setOnClickListener(this);
        highMileage.setOnClickListener(this);
        interior.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.program_search:
                break;
            case R.id.beauty_cleaning:
                Drawable btnDrawable = getResources().getDrawable(R.drawable.bg_top_border);

                break;
        }
    }
}