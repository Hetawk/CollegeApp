package com.ekd.college.ent;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ekd.college.R;

public class Audio extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mediaPlayer;

    private Button btn_play, btn_pause, btn_stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        btn_play = findViewById(R.id.btn_play);
        btn_pause = findViewById(R.id.btn_pause);
        btn_stop = findViewById(R.id.btn_stop);


        btn_play.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
    }//todo: onCreate

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play:
                if (mediaPlayer == null){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.kumama);
                }
                mediaPlayer.start();
                break;
            case R.id.btn_pause:
                if (mediaPlayer != null){
                    mediaPlayer.pause();
                }
                break;
            case R.id.btn_stop:
                if(mediaPlayer != null){
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                break;

        }
    }
}