package com.ekd.college.ent;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ekd.college.R;

public class Media extends Activity implements View.OnClickListener {

    private Button audio, video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        audio = findViewById(R.id.audio);
        video = findViewById(R.id.video);


        audio.setOnClickListener(this);
        video.setOnClickListener(this);

    }// todo: onCreate


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.audio:
                intent = new Intent(this, Audio.class);
                startActivity(intent);
                break;
            case R.id.video:
                intent = new Intent(this, Video.class);
                startActivity(intent);
                break;

        }
    }
}