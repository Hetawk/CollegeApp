package com.ekd.college.control;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ekd.college.R;
import com.ekd.college.control.main.SectionsPagerAdapterNotices;
import com.google.android.material.tabs.TabLayout;

public class AdminNotices extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notices);

        TabLayout tabs = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.view_pager);
        SectionsPagerAdapterNotices sectionsPagerAdapterNotices = new SectionsPagerAdapterNotices(this, getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapterNotices);
        tabs.setupWithViewPager(viewPager);


    }





}