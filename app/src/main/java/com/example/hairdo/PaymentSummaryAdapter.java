package com.example.hairdo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.model.PaymentModel;
import com.example.hairdo.model.Service;

import java.util.ArrayList;

public class PaymentSummaryAdapter extends RecyclerView.Adapter<PaymentSummaryAdapter.ViewHolder> {

    ArrayList<PaymentModel> data;


    public PaymentSummaryAdapter(ArrayList<PaymentModel> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.payment_summary_recycle_view;
    }

    @NonNull
    @Override
    public PaymentSummaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.payment_summary_recycle_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull PaymentSummaryAdapter.ViewHolder holder, int position) {
        PaymentModel ser = data.get(position);
        holder.paymentDate.setText(ser.date);
        holder.amount.setText(ser.amount+"/-");

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView paymentDate;
        TextView amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            paymentDate = itemView.findViewById(R.id.paymentDate);
            amount = itemView.findViewById(R.id.paymentAmount);

        }
    }
}
