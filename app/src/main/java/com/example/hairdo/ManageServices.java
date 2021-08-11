package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ManageServices extends AppCompatActivity {

    Button btn;
    EditText etx;

    ArrayList<String>  myListData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_services);

        myListData.add("Facial");
        myListData.add("wix");
        myListData.add("massage");
        myListData.add("any");
        myListData.add("any");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.serviceRecycleView);
        ServicesAdapter adapter = new ServicesAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btn = findViewById(R.id.addServices);
        etx = findViewById(R.id.newService);

    }

    public void OnAddService(View view){
        Toast.makeText(getApplicationContext(),etx.getText().toString(),Toast.LENGTH_SHORT).show();
        myListData.add(etx.getText().toString());
    }



}