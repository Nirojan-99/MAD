package com.example.hairdo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hairdo.model.Customer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class UserProfileEdit extends Fragment {

    EditText email, gender, contactNum, address, name, password;
    ImageView profile;
    Button btn;
    TextView titleName;
    FirebaseAuth auth;

    public UserProfileEdit() {

    }

    public static UserProfileEdit newInstance() {
        UserProfileEdit fragment = new UserProfileEdit();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_profile_edit, container, false);
        auth = FirebaseAuth.getInstance();
//        String id = auth.getCurrentUser().getUid();
        String id = "oJO7CwPSZjXFTJIungGeaQZQvo33";

        btn = view.findViewById(R.id.saveChanges);
        titleName = view.findViewById(R.id.username);
        email = view.findViewById(R.id.userEmail);
        gender = view.findViewById(R.id.userGender);
        contactNum = view.findViewById(R.id.userContact);
        address = view.findViewById(R.id.userAddress);
        name = view.findViewById(R.id.userName1);
        password = view.findViewById(R.id.userPassword);
        profile = view.findViewById(R.id.userChangeImage);


        Query query = FirebaseDatabase.getInstance().getReference("Customer").child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        Customer cus = snapshot.getValue(Customer.class);
                        name.setText(cus.name);
                        email.setText(cus.email);
                        titleName.setText(cus.name);
                        gender.setText(cus.gender);
                        contactNum.setText(cus.contact);
                        address.setText(cus.address);
                        password.setText(cus.password);
                        profile.setImageURI(Uri.parse(cus.url));
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredEmail = email.getText().toString().trim();
                String enteredGender = gender.getText().toString().trim();
                String enteredContact = contactNum.getText().toString().trim();
                String enteredAddress = address.getText().toString().trim();
                String enteredName = name.getText().toString().trim();
                String enteredPassword = password.getText().toString().trim();

                if (enteredName.isEmpty() || enteredName.length() < 5) {
                    name.setError("Salon name is required");
                    name.requestFocus();
                    return;
                } else if (enteredEmail.isEmpty() || !enteredEmail.contains("@") || !enteredEmail.contains(".com")) {
                    email.setError("Valid email is required");
                    email.requestFocus();
                    return;
                } else if (enteredAddress.isEmpty() || enteredAddress.length() < 5) {
                    address.setError("Valid address is required");
                    address.requestFocus();
                    return;
                } else if (enteredContact.length() != 10) {
                    contactNum.setError("Valid contact number is required");
                    contactNum.requestFocus();
                    return;
                } else if (enteredPassword.length() < 6) {
                    password.setError("Password should contains uppercase and lowercase characters");
                    password.requestFocus();
                    return;
                } else if (!(enteredGender.contains("male")) || !(enteredGender.contains("female"))) {
                    gender.setError("Gender should be Male or Female");
                    gender.requestFocus();
                    return;
                }

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("name", enteredName.toString());
                hashMap.put("gender", enteredGender.toString());
                hashMap.put("contact", enteredContact.toString());
                hashMap.put("password", enteredPassword.toString());
                hashMap.put("address", enteredAddress.toString());


                FirebaseDatabase.getInstance().getReference("Customer").child(id).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(UserProfileEdit.this, "Record is updated", Toast.LENGTH_SHORT).show();
                        //success code
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //failure code
                    }
                });

            }
        });

        //dpchange
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),DpUpload.class);
                intent.putExtra("id",id);
                intent.putExtra("type","Customer");
                startActivity(intent);
            }
        });

        return view;
    }
}