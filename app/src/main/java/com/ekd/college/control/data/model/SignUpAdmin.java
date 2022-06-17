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

import com.ekd.college.R;
import com.ekd.college.control.DBHandlers.AdminModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpAdmin extends AppCompatActivity {
    private Button btn_admin_sign_up;
    private EditText mEmail, mID, mName, mPassword, mPassword_Com;
    private TextView sig_redirect;
    private FirebaseAuth mAuth;
    private DatabaseReference dbRef, reference;
//    private static final String USERS = "users";
//    FirebaseDatabase database;


    private String email, adminID,name, password, password_com;
//    private static User user;
    AdminModel user;
//    String user;

    private String TAG = "Admin Sign Up";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_admin);

        mName = (EditText) findViewById(R.id.ad_sign_up_name);
        mEmail = (EditText) findViewById(R.id.ad_sign_up_email);
        mPassword = (EditText) findViewById(R.id.ad_sign_up_pass);
        mPassword_Com = (EditText) findViewById(R.id.ad_sign_up_pass_com);
        mID = (EditText) findViewById(R.id.ad_sign_up_id);


        sig_redirect = (TextView) findViewById(R.id.tv_ad_sign_in);
        btn_admin_sign_up = (Button) findViewById(R.id.btn_sign_up_admin);

//        database = FirebaseDatabase.getInstance();
//        dbRef = database.getReference(USERS);
        mAuth = FirebaseAuth.getInstance();
        sig_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpAdmin.this, SignIn.class));
                finish();
            }
        });



        //todo:Admin
        btn_admin_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("AdminModel");
                if (mEmail.getText().toString() != null && mPassword.getText().toString() != null) {
                    email = mEmail.getText().toString();
                    adminID = mID.getText().toString();
                    name = mName.getText().toString();
                    password = mPassword.getText().toString();
                    password_com = mPassword_Com.getText().toString();
                    user = new AdminModel(email, adminID,name, password);

                    registerUserAdmin();

                }

            }
        });
    }//todo: onCreate


    //todo: admin
    private void registerUserAdmin() {
        if (!password.equals(password_com)) {
            Toast.makeText(SignUpAdmin.this, "Make sure the two password are the same!", Toast.LENGTH_SHORT).show();
        } else {
            if (!email.isEmpty() || !password.isEmpty()) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "createUserWithEmail:success");
                                    String keyid = dbRef.push().getKey();
                                    assert keyid != null;
                                    //dbRef.child(keyid).child("password").setValue(user); //adding user info to database
                                    dbRef.child(keyid).setValue(user);
                                    Intent it = new Intent(SignUpAdmin.this, SignIn.class);
                                    //Intent it = new Intent(SignUpAdmin.this, SignIn.class);
                                    //it.putExtra("username", String.valueOf(user));
                                    startActivity(it);
                                    finish();
                                    Toast.makeText(SignUpAdmin.this, "Account Created Successful!!", Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignUpAdmin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(SignUpAdmin.this, "Please enter Email and password", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }


}