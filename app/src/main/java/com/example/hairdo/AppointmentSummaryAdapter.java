package com.example.hairdo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.model.Appointment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AppointmentSummaryAdapter extends RecyclerView.Adapter<AppointmentSummaryAdapter.ViewHolder> {
//    ArrayList<String> data;
    List<Appointment> appointmentList;
//    ArrayList<String> salonName;
//    ArrayList<String> Date;

    public AppointmentSummaryAdapter(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

//    ArrayList<String> time;

//    public AppointmentSummaryAdapter(ArrayList<String> salonName, ArrayList<String> date, ArrayList<String> time) {
//        this.salonName = salonName;
//        Date = date;
//        this.time = time;
//    }

    @Override
    public int getItemViewType(final int position) { return R.layout.appointment_summary_recycle_view; }

    @NonNull
    @NotNull
    @Override
    public AppointmentSummaryAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.appointment_summary_recycle_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AppointmentSummaryAdapter.ViewHolder holder, int position) {
//        Appointment ap = data.get(position);
//        holder.Date.setText(Date.get(position));
//        holder.time.setText(time.get(position));
//        holder.salonName.setText(salonName.get(position));
        ViewHolder viewHolder = (ViewHolder)holder;
        Appointment appointment = appointmentList.get(position);
        viewHolder.salonName.setText(appointment.getSname());
        viewHolder.Date.setText(appointment.getDate());
        viewHolder.time.setText(appointment.getTime());
    }

    @Override
    public int getItemCount() {

        return appointmentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView salonName;
        TextView Date;
        TextView time;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            salonName = itemView.findViewById(R.id.salon_name);
            Date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }
    }
}