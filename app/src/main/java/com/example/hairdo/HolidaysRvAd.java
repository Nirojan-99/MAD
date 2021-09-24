package com.example.hairdo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.model.Holiday;

import org.jetbrains.annotations.NotNull;


import java.util.List;

public class HolidaysRvAd extends  RecyclerView.Adapter<HolidaysRvAd.ViewHolder>{
    public Context context;
    public List<Holiday> holidayList;

    public HolidaysRvAd(Context context, List<Holiday> holidayList) {
        this.context = context;
        this.holidayList = holidayList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_holidays,parent,false);

        return new HolidaysRvAd.ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Holiday holiday=   holidayList.get(position);
        holder.tv_Date.setText(holiday.getSelected_Date());
        holder.tv_Remark.setText(holiday.getRemark());


    }

    @Override
    public int getItemCount() {
        return holidayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_Date;
        public TextView tv_Remark;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_Date=itemView.findViewById(R.id.showDate);
            tv_Remark=itemView.findViewById(R.id.showRemark);

        }
    }
}
