package com.example.hairdo;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.model.Gallery;
import com.example.hairdo.model.Service;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    ArrayList<Gallery> data;
    Context cont;

    public GalleryAdapter(ArrayList<Gallery> data,Context con) {
        this.data = data;
        this.cont = con;
    }

    @Override
    public int getItemViewType(final int position) { return R.layout.gallery_recycler; }

    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.gallery_recycler,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.ViewHolder holder, int position) {
        Gallery ser = data.get(position);
        if(ser.url != null){
            holder.img.setPadding(0,0,0,0);
            Picasso.with(cont).load(Uri.parse(ser.url)).into(holder.img);
        }


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = FirebaseDatabase.getInstance().getReference("Gallery").orderByChild("url").equalTo(ser.url);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                            dataSnapshot.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(v.getContext(), "deleted!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(v.getContext(), "Unable to delete", Toast.LENGTH_SHORT).show();
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
        TextView delete ;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            delete = itemView.findViewById(R.id.deletegallery);
            img = itemView.findViewById(R.id.galleryImage);

        }
    }
}
