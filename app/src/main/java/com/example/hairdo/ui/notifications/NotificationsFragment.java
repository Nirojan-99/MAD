package com.example.hairdo.ui.notifications;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hairdo.BothSignUp;
import com.example.hairdo.Calendar;
import com.example.hairdo.MainActivity;
import com.example.hairdo.ManageServices;
import com.example.hairdo.R;
import com.example.hairdo.Reviews;
import com.example.hairdo.SalonProfile;

public class NotificationsFragment extends Fragment {

    RelativeLayout btn;
    RelativeLayout rlt1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);


        btn = root.findViewById(R.id.serviceBtn);
        rlt1 = root.findViewById(R.id.customerReview);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ManageServices.class));
            }
        });

        rlt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Reviews.class));
            }
        });

        return root;
    }

}