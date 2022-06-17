package com.ekd.college.control;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

import com.ekd.college.R;

public class AdminExam extends TabActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_exam);

        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost); // initiate TabHost
        TabHost.TabSpec spec; // Reusable TabSpec for each tab
        Intent intent; // Reusable Intent for each tab

        spec = tabHost.newTabSpec("VIEW EXAMS"); // Create a new TabSpec using tab host
        spec.setIndicator("VIEW EXAMS"); // set the “HOME” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, AdminExamView.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs

        spec = tabHost.newTabSpec("INSERT/UPDATE"); // Create a new TabSpec using tab host
        spec.setIndicator("INSERT/UPDATE"); // set the “CONTACT” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, AdminExamInsertUpdate.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("DELETE"); // Create a new TabSpec using tab host
        spec.setIndicator("DELETE"); // set the “ABOUT” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, AdminExamDelete.class);
        spec.setContent(intent);
        tabHost.addTab(spec);
        //set tab which one you want to open first time 0 or 1 or 2
        tabHost.setCurrentTab(1);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // display the name of the tab whenever a tab is changed
                Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT).show();
            }
        });


    }


}