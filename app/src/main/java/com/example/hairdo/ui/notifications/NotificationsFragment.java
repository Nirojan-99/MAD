package com.example.hairdo.ui.notifications;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hairdo.BothSignUp;
import com.example.hairdo.ContactUs;
import com.example.hairdo.EditDetails;
import com.example.hairdo.Calendar;
import com.example.hairdo.LogIn;
import com.example.hairdo.MainActivity;
import com.example.hairdo.ManageServices;
import com.example.hairdo.PaymentSummary;
import com.example.hairdo.R;
import com.example.hairdo.Reviews;
import com.example.hairdo.SalonGallery;
import com.example.hairdo.SalonProfile;
import com.example.hairdo.Summary;
import com.example.hairdo.UpcomingAppointments;
import com.example.hairdo.offer;

public class NotificationsFragment extends Fragment {

    RelativeLayout btn;
    RelativeLayout rlt1;
    RelativeLayout rlt2;
    RelativeLayout rlt3;
    RelativeLayout rlt4;
    RelativeLayout rlt5;
    RelativeLayout rlt6;
    LinearLayout rlt7;
    RelativeLayout rlt8;
    RelativeLayout rlt9;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);


        btn = root.findViewById(R.id.serviceBtn);
        rlt1 = root.findViewById(R.id.customerReview);
        rlt2 = root.findViewById(R.id.manageOffers);
        rlt3 = root.findViewById(R.id.manageAppointments);
        rlt4 = root.findViewById(R.id.viewCalendar);
        rlt5 = root.findViewById(R.id.summary);
        rlt6 = root.findViewById(R.id.editDetails);
        rlt7 = root.findViewById(R.id.logout);
        rlt8 = root.findViewById(R.id.contactUs);
        rlt9 = root.findViewById(R.id.gallery);

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

        rlt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), offer.class));
            }
        });

        rlt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UpcomingAppointments.class));
            }
        });

        rlt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Calendar.class));
            }
        });

        rlt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Summary.class));
            }
        });

        rlt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditDetails.class));
            }
        });

        rlt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PaymentSummary.class));
            }
        });

        rlt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ContactUs.class));
            }
        });

        rlt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SalonGallery.class));
            }
        });

        return root;
    }

}