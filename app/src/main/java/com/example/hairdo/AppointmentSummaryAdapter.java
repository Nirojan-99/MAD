package com.example.hairdo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.model.Appointment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AppointmentSummaryAdapter extends RecyclerView.Adapter<AppointmentSummaryAdapter.ViewHolder> {


    List<Appointment> appointmentList;


    public AppointmentSummaryAdapter(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }


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

        ViewHolder viewHolder = (ViewHolder)holder;
        Appointment appointment = appointmentList.get(position);
        viewHolder.salonName.setText(appointment.getSname());
        viewHolder.Date.setText(appointment.getDate());
        viewHolder.time.setText(appointment.getTime());

        viewHolder.apCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),QR_Generator.class);
                intent.putExtra("apID", appointment.get_id());
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {

        return appointmentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView salonName;
        TextView Date;
        TextView time;
        ConstraintLayout apCL;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            salonName = itemView.findViewById(R.id.salon_name);
            Date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            apCL=itemView.findViewById(R.id.apCL);
        }
    }
}