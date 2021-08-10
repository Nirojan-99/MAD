package com.example.hairdo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.ui.dashboard.DashboardFragment;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    DashboardFragment ctx;
    String data[];

    public AppointmentAdapter( String data[]) {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public AppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.upcoming_appointments,parent,false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.ViewHolder holder, int position) {
        holder.userName.setText(data[position]);

    }


    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView userName ;
        TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.usernameAppointment);
            date = itemView.findViewById(R.id.dateofAppointment);


        }
    }
}
