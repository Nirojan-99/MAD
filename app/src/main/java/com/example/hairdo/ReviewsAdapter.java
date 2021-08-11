package com.example.hairdo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    String userName[];

    public ReviewsAdapter(String[] userName) {
        this.userName = userName;
    }

    @Override
    public int getItemViewType(final int position) { return R.layout.reviews_recycle_view; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.reviews_recycle_view,parent,false);
        ReviewsAdapter.ViewHolder viewHolder = new ReviewsAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.username.setText(userName[position]);
    }

    @Override
    public int getItemCount() {
        return userName.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView username ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.reviewUsername);

        }
    }
}
