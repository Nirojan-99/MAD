package com.example.hairdo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hairdo.BothSignUp;
import com.example.hairdo.R;
import com.example.hairdo.SalonViewInUser;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    public void onClickServices(View view){
        Intent intent = new Intent(view.getContext(), BothSignUp.class);
        startActivity(intent);
    }
    public void onClickSalons(View v){
        Intent intent = new Intent(v.getContext(), SalonViewInUser.class);
        startActivity(intent);
    }


}