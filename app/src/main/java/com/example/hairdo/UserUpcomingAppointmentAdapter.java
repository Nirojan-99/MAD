package com.example.hairdo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class UserUpcomingAppointmentAdapter extends RecyclerView.Adapter<UserUpcomingAppointmentAdapter.ViewHolder>{
    ArrayList<String> salonName;
    ArrayList<String> Date;
    ArrayList<String> time;

    public UserUpcomingAppointmentAdapter(ArrayList<String> salonName, ArrayList<String> date, ArrayList<String> time) {
        this.salonName= salonName;
        Date= date;
        this.time = time;
    }
    @Override
    public int getItemViewType(final int position) { return R.layout.user_upcoming_appointment_recycle_view; }


    @NotNull
    @Override
    public UserUpcomingAppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_upcoming_appointment_recycle_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( @NotNull UserUpcomingAppointmentAdapter.ViewHolder holder, int position) {
        holder.Date.setText(Date.get(position));
        holder.time.setText(time.get(position));
        holder.salonName.setText(salonName.get(position));
    }

    @Override
    public int getItemCount() {
        return salonName.size();
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