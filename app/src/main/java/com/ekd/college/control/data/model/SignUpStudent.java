package com.ekd.college.control.data.model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ekd.college.R;
import com.ekd.college.control.DBHandlers.StudentModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class SignUpStudent extends AppCompatActivity {
    private Button btn_std_sign_up;
    private EditText mEmail, mPassword, mContact, mName, mPassword_Com, m_studentID, m_dob, m_gName, m_gcNo, mAddress, mSchool;
    private TextView sign_in_redirect;
    private FirebaseAuth mAuth;
    private DatabaseReference dbRef, reference;

    DatePickerDialog picker;


    private String studentName, contactNo, email, password, password_com, studentID, dob, gName, gcNo, address, school;
    //    private static User user;
//    LoggedInUser user;
    StudentModel user;
//    String user;

    private String TAG = "Student Sign Up";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_student);

        mContact = (EditText) findViewById(R.id.st_sign_up_cno);
        mName = (EditText) findViewById(R.id.st_sign_up_name);
        mEmail = (EditText) findViewById(R.id.st_sign_up_email);
        mPassword = (EditText) findViewById(R.id.st_sign_up_pass);
        mPassword_Com = (EditText) findViewById(R.id.st_sign_up_pass_com);
        m_studentID = (EditText) findViewById(R.id.st_sign_up_id);
        m_dob = (EditText) findViewById(R.id.st_sign_up_dob);
        m_gName = (EditText) findViewById(R.id.st_sign_up_gname);
        m_gcNo = (EditText) findViewById(R.id.st_sign_up_gcon);
        mAddress = (EditText) findViewById(R.id.st_sign_up_address);
        mSchool = (EditText) findViewById(R.id.st_sign_up_sch);
        btn_std_sign_up = (Button) findViewById(R.id.btn_st_sign_up);

        sign_in_redirect = (TextView) findViewById(R.id.tv_st_sign_in);




//        database = FirebaseDatabase.getInstance();
//        dbRef = database.getReference(USERS);
        mAuth = FirebaseAuth.getInstance();

        sign_in_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpStudent.this, login.class));
                finish();
            }
        });

        //todo: student
        btn_std_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("StudentModel");
                if (mEmail.getText().toString() != null && mPassword.getText().toString() != null) {
                    studentName = mName.getText().toString();
                    contactNo = mContact.getText().toString();
                    email = mEmail.getText().toString();
                    password = mPassword.getText().toString();
                    password_com = mPassword_Com.getText().toString();
                    studentID = m_studentID.getText().toString();
                    dob = m_dob.getText().toString();
                    gName = m_gName.getText().toString();
                    gcNo = m_gcNo.getText().toString();
                    address = mAddress.getText().toString();
                    school = mSchool.getText().toString();

                    user = new StudentModel(email,studentName, password, studentID,school, dob, gName, gcNo, contactNo, address);

                    registerUserStud();

                }

            }
        });


        m_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(SignUpStudent.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                m_dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }//todo: onCreate



    private void registerUserStud() {
        if (!password.equals(password_com)) {
            Toast.makeText(SignUpStudent.this, "Make sure the two password are the same!", Toast.LENGTH_SHORT).show();
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
                                    dbRef.child(keyid).setValue(user); //adding user info to database
                                    Intent it = new Intent(SignUpStudent.this, login.class);
                                    startActivity(it);
                                    finish();
                                    Toast.makeText(SignUpStudent.this, "Account Created Successful!!", Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignUpStudent.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(SignUpStudent.this, "Please enter Email and password", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }



}