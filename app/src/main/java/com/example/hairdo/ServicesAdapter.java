package com.example.hairdo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {
//    String data[];
    ArrayList<String> data;

    public ServicesAdapter(ArrayList<String> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(final int position) { return R.layout.service_recycle_view; }

    @NonNull
    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.service_recycle_view,parent,false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ServicesAdapter.ViewHolder holder, int position) {
        holder.seviceName.setText(data.get(position));

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
