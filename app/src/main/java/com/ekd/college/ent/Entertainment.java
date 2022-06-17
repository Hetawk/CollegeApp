package com.ekd.college.ent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ekd.college.R;

public class Entertainment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entertainment);



//        audioFunc();
//        pictureFunc();
    }//todo: onCreate


    public void pictureFunc(View view) {
        Intent intent = new Intent(getApplicationContext(), PictureOne.class);
        startActivity(intent);
    }

    public void audioFunc(View view) {
        Intent intent = new Intent(getApplicationContext(), Media.class);
        startActivity(intent);
    }
}