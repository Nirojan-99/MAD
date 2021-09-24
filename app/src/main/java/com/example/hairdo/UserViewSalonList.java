package com.example.hairdo;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.Helper.CircleTransform;
import com.example.hairdo.Helper.GetRating;
import com.example.hairdo.model.Salon;
import com.example.hairdo.model.Service;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserViewSalonList extends RecyclerView.Adapter<UserViewSalonList.ViewHolder> {

    ArrayList<Salon> data;
    Context cont;

    public UserViewSalonList(ArrayList<Salon> data,Context con) {
        this.data = data;
        this.cont = con;
    }

    @Override
    public int getItemViewType(final int position) { return R.layout.salons_list_user_home; }

    @NonNull
    @Override
    public UserViewSalonList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.salons_list_user_home,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull UserViewSalonList.ViewHolder holder, int position) {
        Salon ser = data.get(position);

        GetRating getRating = new GetRating();
        getRating.getRating(ser._id,holder.rtb,holder.txt);
        holder.name.setText(ser.name);
        holder.location.setText(ser.address);
        if(ser.url != null){
            holder.img.setPadding(0,0,0,0);
            Picasso.with(cont).load(Uri.parse(ser.url)).into(holder.img);
        }

        holder.clicable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cont,SalonViewInUser.class);
                intent.putExtra("id",ser._id);
                v.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name ;
        ImageView img;
        RatingBar rtb;
        TextView txt;
        TextView location;
        LinearLayout clicable;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.imageView2);
            rtb = itemView.findViewById(R.id.salonRating);
            txt = itemView.findViewById(R.id.ratingcount);
            location = itemView.findViewById(R.id.location);
            clicable = itemView.findViewById(R.id.salonViewAsUser);

        }
    }
}
