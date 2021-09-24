package com.example.hairdo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.model.Offer;
import com.example.hairdo.model.Service;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder> {

    ArrayList<Offer> data;

    public OffersAdapter(ArrayList<Offer> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(final int position) { return R.layout.offers_recycle_view; }

    @NonNull
    @Override
    public OffersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.offers_recycle_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull OffersAdapter.ViewHolder holder, int position) {
        Offer ser = data.get(position);
        holder.seviceName.setText(ser.name);
        holder.des.setText(ser.description);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = FirebaseDatabase.getInstance().getReference("Offer").orderByChild("name").equalTo(ser.name);
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

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),EditOffer.class);
                intent.putExtra("name",ser.name);
                intent.putExtra("description",ser.description);
                intent.putExtra("id",ser.id);
                intent.putExtra("date",ser.validDate);
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView seviceName ;
        TextView des ;
        ImageView edit;
        ImageView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            seviceName = itemView.findViewById(R.id.name);
            des = itemView.findViewById(R.id.description);
            edit = itemView.findViewById(R.id.offerEdit);
            delete = itemView.findViewById(R.id.delete);

        }
    }
}
