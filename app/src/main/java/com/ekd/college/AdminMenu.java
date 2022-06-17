package com.ekd.college;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ekd.college.control.AdminExam;
import com.ekd.college.control.AdminNotices;
import com.ekd.college.control.AdminReferenceMaterials;
import com.ekd.college.control.AdminTimeTableList;
import com.ekd.college.control.Admin_Profile;
import com.ekd.college.faculty.UpdateFaculty;
import com.ekd.college.notice.DeleteNoticeActivity;
import com.ekd.college.notice.UploadNotice;

public class AdminMenu extends AppCompatActivity implements View.OnClickListener {

    CardView uploadNotice, addGalleryImage, addEbook, faculty, deleteNotice, pages,
    profiles, timetables, exam, papers, notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uploadNotice = findViewById(R.id.addNotice);
        addGalleryImage = findViewById(R.id.addGalleryImage);
        addEbook = findViewById(R.id.addEbook);
        faculty = findViewById(R.id.faculty);
        deleteNotice = findViewById(R.id.deleteNotice);
        pages = findViewById(R.id.pages);
        profiles = findViewById(R.id.profiles);
        timetables = findViewById(R.id.timetables);
        exam = findViewById(R.id.exam);
        papers = findViewById(R.id.papers);
        notifications = findViewById(R.id.notifications);




        uploadNotice.setOnClickListener(this);
        addGalleryImage.setOnClickListener(this);
        addEbook.setOnClickListener(this);
        faculty.setOnClickListener(this);
        deleteNotice.setOnClickListener(this);
        pages.setOnClickListener(this);
        profiles.setOnClickListener(this);
        timetables.setOnClickListener(this);
        exam.setOnClickListener(this);
        papers.setOnClickListener(this);
        notifications.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.addNotice:
                intent = new Intent(this, UploadNotice.class);
                startActivity(intent);
                break;
            case R.id.addGalleryImage:
                intent = new Intent(this, UploadImage.class);
                startActivity(intent);
                break;
            case R.id.addEbook:
                intent = new Intent(this, UploadPdfActivity.class);
                startActivity(intent);
                break;
            case R.id.faculty:
                intent = new Intent(this, UpdateFaculty.class);
                startActivity(intent);
                break;
            case R.id.deleteNotice:
                intent = new Intent(this, DeleteNoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.pages:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.papers:
                intent = new Intent(this, AdminReferenceMaterials.class);
                startActivity(intent);
                break;
            case R.id.notifications:
                intent = new Intent(this, AdminNotices.class);
                startActivity(intent);
                break;
            case R.id.profiles:
                intent = new Intent(this, Admin_Profile.class);
                startActivity(intent);
                break;
            case R.id.exam:
                intent = new Intent(this, AdminExam.class);
                startActivity(intent);
                break;
            case R.id.timetables:
                intent = new Intent(this, AdminTimeTableList.class);
                startActivity(intent);
                break;

        }
    }
}