package com.ekd.college.control.data.model;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ekd.college.AdminMenu;
import com.ekd.college.MainActivity;
import com.ekd.college.R;
import com.ekd.college.control.DBHandlers.StudentModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class login extends AppCompatActivity {

    Button std_login, admin_login;
    StudentModel studentModel;
    EditText username;
    EditText password;
    DatabaseReference dbRef;
    private TextView st_redirect, ad_redirect;

    String keyid;
    String pass;
//    User userName = new User();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_student);


        std_login = findViewById(R.id.btn_sign_in);
        admin_login = findViewById(R.id.btn_sign_in_admin);
        username = findViewById(R.id.sign_in_email);
        password = findViewById(R.id.sign_in_pass);
        st_redirect = (TextView) findViewById(R.id.register_stud);
        ad_redirect = (TextView)  findViewById(R.id.register_admin);

        mAuth = FirebaseAuth.getInstance();


//        checking if user is logged in
        if (mAuth.getCurrentUser() != null) {
            updateUI(mAuth.getCurrentUser());
        }


        //todo Admin Sign Up
        ad_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpAdmin.class));
                finish();
            }
        });
        //todo Student Sign Up
        st_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpStudent.class));
                finish();
            }
        });


        std_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyid = username.getText().toString();
                pass = password.getText().toString();
                dbRef = FirebaseDatabase.getInstance().getReference().child("StudentModel");
                if(!keyid.isEmpty() && !pass.isEmpty()) {
                    mAuth.signInWithEmailAndPassword(keyid, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);

                                Toast.makeText(login.this, "Login Successful!!", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(SignIn.this, AdminMenu.class));
                                finish();
                            } else {
                                Toast.makeText(login.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(login.this, "Please Enter your email and password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignIn.class));
            }
        });

    }//todo onCreate


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }
    }

    public void updateUI(FirebaseUser currentUser) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("username", currentUser.getEmail());
        Log.v("DATA", currentUser.getUid());
        startActivity(intent);
    }



}
