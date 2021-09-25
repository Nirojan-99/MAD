package com.example.hairdo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.model.Holiday;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.List;

public class HolidaysRvAd extends  RecyclerView.Adapter<HolidaysRvAd.ViewHolder>{
    public Context context;
    public ArrayList<Holiday> holidayList;

    public HolidaysRvAd(Context context, ArrayList<Holiday> holidayList) {
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
        holder.deleteBtn.setOnClickListener(v -> {
            FirebaseDatabase.getInstance().getReference(Holiday.class.getSimpleName()).child(holiday.getFbKey()).removeValue().addOnSuccessListener(suc->{
                Toast.makeText(context, "Holiday is removed", Toast.LENGTH_SHORT).show();
                notifyItemRemoved(position);
                Intent intent1=new Intent(context,Holidays.class);
                context.startActivity(intent1);
            }).addOnFailureListener(er->{
                Toast.makeText(context, "Not Removed: "+er.getMessage(), Toast.LENGTH_SHORT).show();
            });

        });

        holder.editBtn.setOnClickListener(v->{
            Intent intent=new Intent(context,HolidaysEdit.class);
            intent.putExtra("editKey",holiday.getFbKey());
            intent.putExtra("OldDate",holiday.getSelected_Date());
            intent.putExtra("OldRemark",holiday.getRemark());
            intent.putExtra("OldFormatDate",holiday.getDate());
            context.startActivity(intent);

        });


    }

    @Override
    public int getItemCount() {
        return holidayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_Date;
        public TextView tv_Remark;
        public ImageView editBtn;
        public ImageView deleteBtn;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_Date=itemView.findViewById(R.id.showDate);
            tv_Remark=itemView.findViewById(R.id.showRemark);
            editBtn=itemView.findViewById(R.id.hdEditBtn);
            deleteBtn=itemView.findViewById(R.id.hdDeletBtn);

        }
    }
}
