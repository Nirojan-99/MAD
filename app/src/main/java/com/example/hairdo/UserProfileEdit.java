package com.example.hairdo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class UserProfileEdit extends Fragment {

    EditText titleName, email, gender, contactNum, address, name, password;
    ImageView profile;
    Button btn;

    public UserProfileEdit() {

    }

    public static UserProfileEdit newInstance() {
        UserProfileEdit fragment = new UserProfileEdit();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        btn = getView().findViewById(R.id.saveChanges);
//        titleName = getView().findViewById(R.id.username);
//        email = getView().findViewById(R.id.userEmail);
//        gender = getView().findViewById(R.id.userGender);
//        contactNum = getView().findViewById(R.id.userContact);
//        address = getView().findViewById(R.id.userAddress);
//        name = getView().findViewById(R.id.userName1);
//        password = getView().findViewById(R.id.userPassword);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



//        String id = getActivity().getIntent().getStringExtra("userMail").toString();

//        Toast.makeText(getView().getContext(), "frfgsdfd", Toast.LENGTH_SHORT).show();


        return inflater.inflate(R.layout.fragment_user_profile_edit, container, false);
    }
}