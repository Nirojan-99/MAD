package com.example.hairdo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.model.Service;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServicesSalonAdapter extends RecyclerView.Adapter<ServicesSalonAdapter.ViewHolder> {

    ArrayList<Service> data;

    public ServicesSalonAdapter(ArrayList<Service> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(final int position) { return R.layout.services_recycler_view; }

    @NonNull
    @Override
    public ServicesSalonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.services_recycler_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ServicesSalonAdapter.ViewHolder holder, int position) {
        Service ser = data.get(position);
        holder.seviceName.setText(ser.name);


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView seviceName ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            seviceName = itemView.findViewById(R.id.services);

        }
    }
}
