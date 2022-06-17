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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignIn extends AppCompatActivity {
    private Button btn_std_signIn, btn_sign_in_admin;
    private EditText signInEmail, signInPassword;
    private TextView st_redirect, ad_redirect;
    private FirebaseAuth mAuth;
    DatabaseReference dbRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btn_std_signIn = (Button) findViewById(R.id.btn_sign_in);
        btn_sign_in_admin = (Button) findViewById(R.id.btn_sign_in_admin);
        signInEmail = (EditText) findViewById(R.id.sign_in_email);
        signInPassword = (EditText) findViewById(R.id.sign_in_pass);
        st_redirect = (TextView) findViewById(R.id.register_stud);
        ad_redirect = (TextView)  findViewById(R.id.register_admin);

        mAuth = FirebaseAuth.getInstance();


        //checking if user is logged in
//        if (mAuth.getCurrentUser() != null) {
//            updateUI(mAuth.getCurrentUser());
//        }


        //todo Admin Sign Up
        ad_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, SignUpAdmin.class));
                finish();
            }
        });
        //todo Student Sign Up
        st_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, SignUpStudent.class));
                finish();
            }
        });


        //todo: student Sign In
        btn_std_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        });

        //todo:admin Sign In
        btn_sign_in_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = signInEmail.getText().toString();
                String pass = signInPassword.getText().toString();
                if(!email.isEmpty() && !pass.isEmpty()){
                    dbRef = FirebaseDatabase.getInstance().getReference().child("AdminModel");
                    mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);

                                Toast.makeText(SignIn.this, "Login Successful!!", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(SignIn.this, AdminMenu.class));
                                finish();
                            }else{
                                Toast.makeText(SignIn.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(SignIn.this, "Please Enter your email and password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        
    }//todo: onCreate


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
            Intent intent = new Intent(getApplicationContext(), AdminMenu.class);
            intent.putExtra("username", currentUser.getEmail());
            Log.v("DATA", currentUser.getUid());
            startActivity(intent);
    }


}