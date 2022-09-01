package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class signup extends AppCompatActivity {
    Button lgnBtn, rgstrBtn;
    TextInputLayout fullname_var, email_var, phone_var, usrname_var, password_var;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        lgnBtn = findViewById(R.id.login_button);
        rgstrBtn = findViewById(R.id.register_button);
        fullname_var = findViewById(R.id.full_name_text_design);
        email_var = findViewById(R.id.email_text_design);
        phone_var = findViewById(R.id.phone_text_design);
        usrname_var = findViewById(R.id.username_signup_text_design);
        password_var = findViewById(R.id.password1_input_text_field);

    }

    public void RgstrButtonClicked(View view) {
        String fullname_var_ = fullname_var.getEditText().getText().toString();
        String email_var_ = email_var.getEditText().getText().toString();
        String usrname_var_ = usrname_var.getEditText().getText().toString();
        String password_var_ = password_var.getEditText().getText().toString();
        String phone_var_ = phone_var.getEditText().getText().toString();
        if (!fullname_var_.isEmpty()) {
            fullname_var.setError(null);
            fullname_var.setErrorEnabled(false);
            if (!email_var_.isEmpty()) {
                email_var.setError(null);
                email_var.setErrorEnabled(false);
                if (!usrname_var_.isEmpty()) {
                    usrname_var.setError(null);
                    usrname_var.setErrorEnabled(false);
                    if (!phone_var_.isEmpty()) {
                        phone_var.setError(null);
                        phone_var.setErrorEnabled(false);
                        if (!password_var_.isEmpty()) {
                            password_var.setError(null);
                            password_var.setErrorEnabled(false);
                            if (email_var_.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {

                                firebaseDatabase = FirebaseDatabase.getInstance();
                                reference = firebaseDatabase.getReference("datauser");
                                String fullname_var_s = fullname_var.getEditText().getText().toString();
                                String email_var_s = email_var.getEditText().getText().toString();
                                String usrname_var_s = usrname_var.getEditText().getText().toString();
                                String password_var_s = password_var.getEditText().getText().toString();
                                String phone_var_s = phone_var.getEditText().getText().toString();

                                storingData storingDatass = new storingData(fullname_var_s, usrname_var_s, email_var_s, phone_var_s, password_var_s);
                                reference.child(usrname_var_s).setValue(storingDatass);
                                Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Profile.class);
                                startActivity(intent);
                                finish();


                            } else {
                                email_var.setError("Invalid email");
                            }
                        } else {
                            password_var.setError("Please enter the password");
                        }
                    } else {
                        phone_var.setError("Please enter the phone number");
                    }
                } else {
                    usrname_var.setError("Please enter the username");
                }
            } else {
                email_var.setError("Please enter the password");
            }
        } else {
            fullname_var.setError("Please enter your full name");
        }

    }
}

