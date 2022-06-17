package com.ekd.college.control;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ekd.college.R;
import com.ekd.college.control.main.SectionsPagerAdapterReferenceMaterials;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class AdminReferenceMaterials extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reference_materials);
        SectionsPagerAdapterReferenceMaterials sectionsPagerAdapterReferenceMaterials = new SectionsPagerAdapterReferenceMaterials(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapterReferenceMaterials);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);


    }

}