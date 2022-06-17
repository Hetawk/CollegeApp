package com.ekd.college.ent;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.ekd.college.R;

public class Video extends AppCompatActivity implements View.OnClickListener {
    private Button btn_play, btn_pause, btn_stop, btn_restart;
    private VideoView videoView;
    private SeekBar seekBar;
        String videoPath = "android.resource://com.ekd.college/" + R.raw.none_like_you;
//    private String MEDIA_PATH = Environment.getExternalStorageDirectory().getPath()
//            + "Download/trial.webm";
    //                +"Download/none_like_you.mp4";
    Uri u = Uri.parse(videoPath);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        btn_play = findViewById(R.id.btn_play);
        btn_pause = findViewById(R.id.btn_pause);
        btn_stop = findViewById(R.id.btn_stop);
        btn_restart = findViewById(R.id.btn_restart);


        videoView = findViewById(R.id.videoView);
        seekBar = findViewById(R.id.seekBar2);

//        videoView.setVideoPath(MEDIA_PATH);
        videoView.setVideoURI(u);


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(videoView.getDuration());
            }
        });


//        VideoView videoView = findViewById(R.id.video_view);
//        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.trial;
//        Uri uri = Uri.parse(videoPath);
//        videoView.setVideoURI(uri);

//        MediaController mediaController = new MediaController(this);
//        videoView.setMediaController(mediaController);
//        mediaController.setAnchorView(videoView);

        btn_play.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_restart.setOnClickListener(this);

    }//todo: onCreate

    public void PlayButton(View view) {
        if (videoView.isPlaying()) {
            videoView.resume();
        } else {
            videoView.start();
        }
        new CountDownTimer(videoView.getDuration(), 1) {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTick(long millisUntilFinished) {
                seekBar.setProgress(videoView.getCurrentPosition(), true);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }//todo:play

    public void PauseButton(View view) {
        videoView.pause();
    }//todo:pause

    public void RestartButton(View view) {
        videoView.stopPlayback();
//        videoView.setVideoPath(MEDIA_PATH);
        videoView.setVideoURI(u);
        videoView.start();
    }//todo:restart

    public void StopButton(View view) {
        videoView.stopPlayback();
//        videoView.setVideoPath(MEDIA_PATH);
        videoView.setVideoURI(u);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                PlayButton(videoView);
                break;
            case R.id.btn_pause:
                PauseButton(videoView);
                break;
            case R.id.btn_stop:
                StopButton(videoView);
                break;
            case R.id.btn_restart:
                RestartButton(videoView);
                break;
        }
    }
}