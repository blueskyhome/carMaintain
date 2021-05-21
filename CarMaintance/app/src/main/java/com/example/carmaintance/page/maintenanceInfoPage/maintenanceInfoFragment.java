package com.example.carmaintance.page.maintenanceInfoPage;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carmaintance.R;
import com.example.carmaintance.adapter.MaintainInfoAdapter;
import com.example.carmaintance.page.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class maintenanceInfoFragment extends BaseFragment {

    private View view;
    private List<String> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private MaintainInfoAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_maintenanceinfo, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.maintain_info_recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MaintainInfoAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);
        mAdapter.addDates(initData());
        serHeader(recyclerView);
        return view;
    }

    private void serHeader(RecyclerView view) {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.maintain_program_header, view, false);
        mAdapter.setHeaderView(header);
    }

    private List<String> initData() {
        list.add("洗车");
        list.add("贴膜");
        list.add("镀晶");
        list.add("内饰清洗");
        list.add("全车打蜡");
        list.add("杀菌消毒");
        list.add("机油");
        list.add("机油滤清器");
        list.add("空气滤清器");
        list.add("燃油滤清器");
        list.add("燃油系统");
        list.add("发动机");
        list.add("蓄电池");
        list.add("汽车玻璃");
        list.add("雨刷");
        list.add("雾灯");
        list.add("空调滤清器");
        list.add("空调制冷剂");
        list.add("空调管路");
        list.add("刹车保养套");
        list.add("刹车油");
        list.add("刹车片");
        list.add("刹车盘");
        list.add("防冻液");
        list.add("水箱清洗");
        list.add("火花塞");
        list.add("节气门清洗");
        list.add("喷油嘴清洗");
        list.add("进气系统清洗");
        list.add("变速油箱");
        list.add("正时皮带");
        list.add("助力转向油");
        list.add("减振器");
        list.add("脚垫");
        list.add("坐垫");
        list.add("后备箱垫");

        return list;
    }
}