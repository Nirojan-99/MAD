package com.example.hairdo.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.BothSignUp;
import com.example.hairdo.DpUpload;
import com.example.hairdo.Helper.CircleTransform;
import com.example.hairdo.ManageServices;
import com.example.hairdo.R;
import com.example.hairdo.SalonViewInUser;
import com.example.hairdo.ServicesAdapter;
import com.example.hairdo.ServicesSalonAdapter;
import com.example.hairdo.model.Salon;
import com.example.hairdo.model.Service;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {

    //        String id = auth.getCurrentUser().getUid();
    String id = "ejHLtEYSByaRAt0p7zp5yMaD9Na2";
    ArrayList<Service> myListData = new ArrayList<Service>();
    HashMap<String, Object> hashMap = new HashMap<>();
    ServicesSalonAdapter adapter;
    ImageView dp,dpchange;
    TextView address, mail, contact, likes, name;
    RatingBar rtb;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

//        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recylersalon);
//        adapter = new ServicesSalonAdapter(myListData);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
//        recyclerView.setAdapter(adapter);

        //another
        RecyclerView recyclerView = root.findViewById(R.id.recylersalon);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new ServicesSalonAdapter(myListData);
        recyclerView.setAdapter(adapter);

        //initialize
        dp = root.findViewById(R.id.dp);
        address = root.findViewById(R.id.address);
        mail = root.findViewById(R.id.mail);
        contact = root.findViewById(R.id.contact);
        likes = root.findViewById(R.id.likes);
        name = root.findViewById(R.id.name);
        rtb = root.findViewById(R.id.salonRating);
        dpchange = root.findViewById(R.id.touch);

        //dp change
        dpchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), DpUpload.class);
                intent.putExtra("id", id);
                intent.putExtra("type", "Salon");
                startActivity(intent);

            }
        });

        //fetch data
        Query query = FirebaseDatabase.getInstance().getReference("Salon").child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        Salon cus = snapshot.getValue(Salon.class);
                        name.setText(cus.name);
                        mail.setText(cus.email);
                        contact.setText(cus.contact);
                        address.setText(cus.address);
//                        likes.setText(cus.likes);
                        likes.setText("102");
                        rtb.setRating(3.0f);
                        if(cus.url != null){
                            dp.setPadding(0,0,0,0);
                            Picasso.with(root.getContext()).load(Uri.parse(cus.url)).transform(new CircleTransform()).into(dp);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //fetch data
        Query query1 = FirebaseDatabase.getInstance().getReference("Services").orderByChild("id").equalTo(id);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Service ser = dataSnapshot.getValue(Service.class);
                        myListData.add(ser);
                    }
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(root.getContext(), "no servies available", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    public void onClickServices(View view) {
        Intent intent = new Intent(view.getContext(), BothSignUp.class);
        startActivity(intent);
    }

    public void onClickSalons(View v) {
        Intent intent = new Intent(v.getContext(), SalonViewInUser.class);
        startActivity(intent);
    }


}