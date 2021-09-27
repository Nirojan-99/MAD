package com.example.hairdo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.model.Service;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {

    ArrayList<Service> data;
    Context cont;

    public ServicesAdapter(ArrayList<Service> data) {
        this.data = data;
    }

    public ServicesAdapter(ArrayList<Service> data, Context cont) {
        this.data = data;
        this.cont = cont;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.service_recycle_view;
    }

    @NonNull
    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.service_recycle_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ServicesAdapter.ViewHolder holder, int position) {
        Service ser = data.get(position);
        holder.seviceName.setText(ser.name);

        //delete service
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(ser._id,position);

            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView seviceName;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            seviceName = itemView.findViewById(R.id.services);
            img = itemView.findViewById(R.id.delete);

        }
    }


    private void showDialog(String id,int position) {

        final Dialog dialog = new Dialog(cont);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.delete_confirm_popup);

        Button btn = dialog.findViewById(R.id.delete12);
        TextView txt = dialog.findViewById(R.id.cancel12);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                FirebaseDatabase.getInstance().getReference("Services").child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(v.getContext(), "deleted!", Toast.LENGTH_SHORT).show();
                        data.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(v.getContext(), "Unable to delete", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);

    }


}
