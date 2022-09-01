package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;



import java.lang.ref.WeakReference;

public class login extends AppCompatActivity {
    Button signUpBtn, loginbtn;
    TextInputLayout usrname_var, password_var;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
       // Smartech.getInstance(new WeakReference<>(context)).initializeSdk(this);


        signUpBtn = findViewById(R.id.signnup);
        loginbtn = findViewById(R.id.loginn);
        usrname_var = findViewById(R.id.username_signup_text_design);
        password_var = findViewById(R.id.password_input_text_field);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_ = usrname_var.getEditText().getText().toString();
                String password_ = password_var.getEditText().getText().toString();
                if(!username_.isEmpty()){
                    usrname_var.setError(null);
                    usrname_var.setErrorEnabled(false);
                    if(!password_.isEmpty()){
                    password_var.setError(null);
                    password_var.setErrorEnabled(false);

                    final String username_data = usrname_var.getEditText().getText().toString();
                    final String password_data = password_var.getEditText().getText().toString();

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("datauser");
                        Query check_username = databaseReference.orderByChild("username").equalTo(username_data);

                        check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    usrname_var.setError(null);
                                    usrname_var.setErrorEnabled(false);
                                    String password_check = dataSnapshot.child(username_data).child("password").getValue(String.class);
                                    if(password_check.equals(password_data)){
                                        password_var.setError(null);
                                        password_var.setErrorEnabled(false);
                                        Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),Profile.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        password_var.setError("Please enter the correct password");
                                    }
                                }
                                else {
                                    usrname_var.setError("username doesnot exists");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }else{
                        password_var.setError("Please Enter the password");
                    }

                }else {
                    usrname_var.setError("Please enter the username");
                }
            }
        });



        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), signup.class);
                startActivity(intent);
            }
        });

    }

    public void loginBtnClicked(View view) {

    }
}