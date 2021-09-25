package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hairdo.model.Salon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchSalon extends AppCompatActivity {

    EditText search;
    ImageButton imbt;
    ProgressBar pgs;
    TextView nothing;
    ArrayList<Salon> myListData = new ArrayList<Salon>();
    UserViewSalonList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_salon);

        //initialize
        search = findViewById(R.id.searchValue);
        imbt = findViewById(R.id.button);
        pgs = findViewById(R.id.searchProgress);
        nothing = findViewById(R.id.nothing);

        //set adapter
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.searchedRecycler);
        adapter = new UserViewSalonList(myListData, SearchSalon.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchSalon.this));
        recyclerView.setAdapter(adapter);

        imbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pgs.setVisibility(View.VISIBLE);
                nothing.setVisibility(View.GONE);
                String enteredValue = search.getText().toString().trim();
                myListData.clear();
                adapter.notifyDataSetChanged();

                if (enteredValue.isEmpty()) {
                    Toast.makeText(SearchSalon.this, "Search key is empty", Toast.LENGTH_LONG).show();
                    return;
                }

                //fetch salons
                Query query = FirebaseDatabase.getInstance().getReference("Salon").orderByChild("name").equalTo(enteredValue);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Salon ser = dataSnapshot.getValue(Salon.class);
                                ser.set_id(dataSnapshot.getKey());
                                myListData.add(ser);
                            }
                            pgs.setVisibility(View.GONE);
//                            pgs.setVisibility(View.GONE);
                            adapter.notifyDataSetChanged();

                        } else {
                            nothing.setVisibility(View.VISIBLE);
                            pgs.setVisibility(View.GONE);
                            Toast.makeText(SearchSalon.this, "no salons available", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        nothing.setVisibility(View.VISIBLE);
                        pgs.setVisibility(View.GONE);
                        Toast.makeText(SearchSalon.this, "no salons available", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}