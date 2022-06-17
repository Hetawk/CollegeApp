package com.ekd.college.ent;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ekd.college.R;

public class PictureOne extends Activity implements View.OnClickListener {
    private ImageView iv;
    private Button capture, gallery;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_one);
        iv = findViewById(R.id.iv);
        capture = findViewById(R.id.capture);
        gallery = findViewById(R.id.gallery);


        capture.setOnClickListener(this);
        gallery.setOnClickListener(this);


    }// todo: onCreate

    //todo: capture
    private void captureFunc() {
        //todo: request for camera runtime permission
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);

//            capture.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(intent, 100);
//                }
//            });
        }

    }

    //todo: view Images
    private void viewGallery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                //permission ont granted, request for it
                String [] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                //show popup for runtime permission
                requestPermissions(permissions, PERMISSION_CODE);
            }else {
                pickImageFromGallery();
            }
        }
    }

    private void pickImageFromGallery() {
        //todo:
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }
    //vg
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //todo: permission was granted
                    pickImageFromGallery();
                }
                else{
                    Toast.makeText(this, "Permission denied..!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            iv.setImageBitmap(bitmap);
        }
        else if (requestCode == RESULT_OK || requestCode == IMAGE_PICK_CODE){
            //set image to
            iv.setImageURI(data.getData());
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.capture:
                captureFunc();
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
                break;
            case R.id.gallery:
                viewGallery();
                break;

        }
    }


}