package com.ekd.college;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactUs extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    private static final int REQUEST_SMS = 2;
    private EditText et_number, et_sms, et_smsNum;
    private ImageView iv_call, iv_sms;
    private TextView tv_viewer;
    private Button btn_send_sms;
    String msg, phoneNo;
    FloatingActionButton call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);
        et_number = findViewById(R.id.et_number);
        call = findViewById(R.id.iv_call);
        iv_sms = findViewById(R.id.iv_sms);
        et_sms = findViewById(R.id.et_sms);
        et_smsNum = findViewById(R.id.et_smsNum);
        btn_send_sms = findViewById(R.id.btn_send_sms);


//        Todo: sms
//        check if the permission is not granted
//        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);


//        Todo: sms onClick
        btn_send_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTextMessage();
            }
        });

//        Todo: call onClick
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

    }//TODO: onCreate

    @SuppressLint("SetTextI18n")
    private void sendTextMessage() {

        msg = et_sms.getText().toString();
        et_smsNum.setText("+8618506832159");
        phoneNo = new String(String.valueOf(et_smsNum));
        if ((msg.trim().length() > 0) && (phoneNo.trim().length() > 0)) {
            if (ContextCompat.checkSelfPermission(ContactUs.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
//            if not granted, check if the user has denied the permission
                ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS);
            } else {
//                a pop up will appear for require permission i.e allow or deny
                //        sending intent
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(phoneNo, null, msg,pi, null);
                Toast.makeText(this, "Message sent successfully", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(ContactUs.this, "Please confirm that phone number and message is enter!", Toast.LENGTH_LONG).show();
        }

    }


    //        Todo: call
    private void makePhoneCall() {
        String number = et_number.getText().toString();
//        Todo: use trim to escape white spaces
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel: " + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(ContactUs.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    //        Todo: call and sms
    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//       Todo CALL
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
//        Todo: SMS
        else if (requestCode == REQUEST_SMS) {
//            check whether the length of grantResults is greater than 0 and is equal to request sms
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendTextMessage();
                Toast.makeText(this, "Permission Granted, thanks", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "ACCESS DENIED, ask for permission first!", Toast.LENGTH_LONG).show();
            }

        }
    }

}
