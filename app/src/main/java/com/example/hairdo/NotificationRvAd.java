package com.example.hairdo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.model.NotificationM;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NotificationRvAd extends RecyclerView.Adapter<NotificationRvAd.ViewHolder> {
    public Context context;
    public ArrayList<NotificationM> nfList;

    public NotificationRvAd(Context context, ArrayList<NotificationM> nfList) {
        this.context = context;
        this.nfList = nfList;
    }

    @NonNull
    @NotNull
    @Override
    public NotificationRvAd.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_notification,parent,false);
        return new NotificationRvAd.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NotificationRvAd.ViewHolder holder, int position) {
        NotificationM notificationM=nfList.get(position);
        holder.tvTitle.setText(notificationM.getTitle());
        holder.tvDate.setText(notificationM.getDate());
        holder.tvSub.setText(notificationM.getSub());
    }

    @Override
    public int getItemCount() {
        return nfList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTitle;
        public TextView tvDate;
        public TextView tvSub;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.nfTit);
            tvDate=itemView.findViewById(R.id.nfDate);
            tvSub=itemView.findViewById(R.id.nfSub);
        }
    }

}
