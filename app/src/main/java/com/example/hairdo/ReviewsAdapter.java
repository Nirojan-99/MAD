package com.example.hairdo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.util.HashMap;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    ArrayList<Review> data;

    public ReviewsAdapter(ArrayList<Review> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(final int position) { return R.layout.reviews_recycle_view; }

    @NonNull
    @Override
    public ReviewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.reviews_recycle_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ReviewsAdapter.ViewHolder holder, int position) {
        Review ser = data.get(position);
        holder.name.setText(ser.userName);
        holder.feedback.setText(ser.feedback);
        holder.rtb.setRating(ser.star);
        holder.date.setText(ser.date);

        if(ser.reply != null){
            holder.reply.setVisibility(View.GONE);
            holder.replymsg.setVisibility(View.GONE);
            holder.owner.setVisibility(View.VISIBLE);
            holder.ownerReply.setText(ser.reply);
            holder.ownerReply.setVisibility(View.VISIBLE);
        }

        holder.reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reply = holder.replymsg.getText().toString().trim();

                if(reply.isEmpty()){
                    holder.reply.setError("Require valid reply");
                    holder.reply.requestFocus();
                    return;
                }

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("reply", reply.toString());

                //update reply
                FirebaseDatabase.getInstance().getReference("Review").child(ser._id).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });

            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name ,date ,owner,ownerReply;
        TextView feedback ;
        RatingBar rtb;
        Button reply;
        EditText replymsg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.reviewUsername);
            date = itemView.findViewById(R.id.date);
            feedback = itemView.findViewById(R.id.userReview);
            rtb = itemView.findViewById(R.id.userRating);
            reply = itemView.findViewById(R.id.saolReplySubmit);
            replymsg = itemView.findViewById(R.id.salonreply);
            owner = itemView.findViewById(R.id.owner);
            ownerReply = itemView.findViewById(R.id.ownerreply);


        }
    }
}
