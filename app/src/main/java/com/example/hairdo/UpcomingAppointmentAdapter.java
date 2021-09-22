package com.example.hairdo;

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

import java.util.ArrayList;

public class UpcomingAppointmentAdapter extends RecyclerView.Adapter<UpcomingAppointmentAdapter.ViewHolder> {
    //    String data[];
    ArrayList<String> data;

    public UpcomingAppointmentAdapter(ArrayList<String> data) {
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
        holder.seviceName.setText(data.get(position));
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(v.getContext(), ManageUpcomingApoointment.class);
                send.putExtra("userName",data.get(position));
//                send.putExtra("date_time",)
                v.getContext().startActivity(send);
//                Toast.makeText(v.getContext(),data.get(position),Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView seviceName ;
        ImageView btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.editAppointments);
            seviceName = itemView.findViewById(R.id.appointmentUsername);

        }
    }
}
