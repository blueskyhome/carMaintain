package com.example.carmaintance.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.carmaintance.R;
import com.example.carmaintance.bean.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private List<Appointment> mDates = new ArrayList<>();
    private View view;
    private Activity mActivity;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView program_img;
        private TextView program_name;
        private TextView store_name;
        private TextView program_price;
        private TextView appointment_time;

        public ViewHolder(View view) {
            super(view);
            program_img = (ImageView) view.findViewById(R.id.recycler_program_img);
            program_name = (TextView) view.findViewById(R.id.recycler_program_name);
            store_name = (TextView) view.findViewById(R.id.recycler_store_name);
            program_price = (TextView) view.findViewById(R.id.recycler_appointment_price);
            appointment_time = (TextView) view.findViewById(R.id.recycler_appointment_time);
        }
    }

    public AppointmentAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void addDate(List<Appointment> dates) {
        mDates.addAll(dates);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Appointment appointment = mDates.get(position);
        Glide.with(view).load(appointment.getProgram_url()).into(holder.program_img);
        holder.program_name.setText(appointment.getProgram_name());
        holder.store_name.setText(appointment.getStore_name());
        holder.program_price.setText("¥ " + String.valueOf(appointment.getProgram_price()));
        holder.appointment_time.setText(appointment.getTime().substring(0, 10));

    }

    @Override
    public int getItemCount() {
        return mDates.size();
    }
}
