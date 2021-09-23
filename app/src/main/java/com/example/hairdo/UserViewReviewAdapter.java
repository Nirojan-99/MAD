package com.example.hairdo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.model.Review;
import com.example.hairdo.model.Service;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserViewReviewAdapter extends RecyclerView.Adapter<UserViewReviewAdapter.ViewHolder> {

    ArrayList<Review> data;

    public UserViewReviewAdapter(ArrayList<Review> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(final int position) { return R.layout.review_user_view_recycle; }

    @NonNull
    @Override
    public UserViewReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.review_user_view_recycle,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull UserViewReviewAdapter.ViewHolder holder, int position) {
        Review ser = data.get(position);
        holder.userName.setText(ser.userName);
        holder.rtb.setRating(ser.star);
        holder.feedback.setText(ser.feedback);

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView feedback,userName ;
        RatingBar rtb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            feedback = itemView.findViewById(R.id.userReview1);
            userName = itemView.findViewById(R.id.userName);
            rtb = itemView.findViewById(R.id.userRating);

        }
    }
}

