package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hairdo.model.Customer;
import com.example.hairdo.model.Service;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ManageServices extends AppCompatActivity {

    Button btn;
    EditText etx;
    //        String id = auth.getCurrentUser().getUid();
    String id = "oJO7CwPSZjXFTJIungGeaQZQvo33";
    ArrayList<Service>  myListData = new ArrayList<Service>();
    HashMap<String, Object> hashMap = new HashMap<>();
    ServicesAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_services);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.serviceRecycleView);
        adapter = new ServicesAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Query query = FirebaseDatabase.getInstance().getReference("Services").orderByChild("id").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
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
                Toast.makeText(ManageServices.this, "no servies available", Toast.LENGTH_SHORT).show();
            }
        });

//        myListData.add("Facial");
//        myListData.add("wix");
//        myListData.add("massage");
//        myListData.add("any");
//        myListData.add("any");



        btn = findViewById(R.id.addServices);
        etx = findViewById(R.id.newService);


    }

    public void OnAddService(View view){
        String newService = etx.getText().toString().trim();

        if(newService.isEmpty()){
            etx.setError("Require valid service name");
            etx.requestFocus();
            return;
        }
        Service service = new Service(id,newService);
        FirebaseDatabase.getInstance().getReference("Services").push().setValue(service).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ManageServices.this, "Service is updated", Toast.LENGTH_SHORT).show();
//                finish();
                startActivity(getIntent());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ManageServices.this, "Service is not updated", Toast.LENGTH_SHORT).show();
            }
        });
    }



}