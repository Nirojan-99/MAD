package com.example.hairdo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.model.Appointment;

import java.util.ArrayList;

public class UpcomingAppointmentAdapter extends RecyclerView.Adapter<UpcomingAppointmentAdapter.ViewHolder> {
    //    String data[];
    ArrayList<Appointment> data;

    public UpcomingAppointmentAdapter(ArrayList<Appointment> data ) {
        this.data = data;
    }

    @Override
    public int getItemViewType(final int position) { return R.layout.upcoming_appointment_recycle_view; }

    @NonNull
    @Override
    public UpcomingAppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.upcoming_appointment_recycle_view,parent,false);
        UpcomingAppointmentAdapter.ViewHolder viewHolder = new UpcomingAppointmentAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull UpcomingAppointmentAdapter.ViewHolder holder, int position) {
         Appointment ser = data.get(position);
         holder.userName.setText(ser.cname);
         holder.date.setText(ser.date+" | "+ser.time);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ManageUpcomingApoointment.class);
                intent.putExtra("id",ser._id);
                v.getContext().startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView userName,date ;
        ImageView btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.editAppointments);
            userName = itemView.findViewById(R.id.appointmentUsername);
            date = itemView.findViewById(R.id.datetime);

        }
    }
}
