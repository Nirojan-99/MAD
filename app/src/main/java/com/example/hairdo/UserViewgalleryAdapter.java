package com.example.hairdo;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.Helper.CircleTransform;
import com.example.hairdo.model.Gallery;
import com.example.hairdo.model.Service;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserViewgalleryAdapter extends RecyclerView.Adapter<UserViewgalleryAdapter.ViewHolder> {

    ArrayList<Gallery> data;
    Context cont;

    public UserViewgalleryAdapter(ArrayList<Gallery> data , Context con) {
        this.data = data;
        this.cont = con;
    }

    @Override
    public int getItemViewType(final int position) { return R.layout.user_view_gallery_recycler; }

    @NonNull
    @Override
    public UserViewgalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_view_gallery_recycler,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull UserViewgalleryAdapter.ViewHolder holder, int position) {
        Gallery ser = data.get(position);
        if(ser.url != null){
            holder.img.setPadding(0,0,0,0);
            Picasso.with(cont).load(Uri.parse(ser.url)).into(holder.img);
        }


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.galleryImage);

        }
    }
}
