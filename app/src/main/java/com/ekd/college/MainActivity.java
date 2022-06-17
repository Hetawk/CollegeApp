package com.ekd.college;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.ekd.college.control.Papers;
import com.ekd.college.control.data.model.SignIn;
import com.ekd.college.ent.Entertainment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private FirebaseAuth mAuth;
    Papers papers = new Papers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_one);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.frame_layout);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);

        mAuth = FirebaseAuth.getInstance();
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    } //Todo: onCreate Method

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(MainActivity.this, SignIn.class));
            finish();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        final Fragment[] fragment = {null};
        switch (item.getItemId()){
            case R.id.navigation_contact:
                Toast.makeText(this, "Contact Us", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, ContactUs.class));
                break;
            case R.id.navigation_video:
                Toast.makeText(this, "Video Lectures", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_rate:
                Toast.makeText(this, "Rate Us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_ebook:
                NavigationMenuItemView ebook = (NavigationMenuItemView) findViewById(R.id.navigation_ebook);
                Toast.makeText(this, "Ebooks", Toast.LENGTH_SHORT).show();
                ebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        fragment[0] = new Papers();
                        ft.replace(R.id.navigation_view, fragment[0]);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                });

                break;
            case R.id.navigation_ent:
                Toast.makeText(this, "Entertainment..", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Entertainment.class));
                break;
            case R.id.navigation_theme:
                Toast.makeText(this, "Theme", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_logout:
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, SignIn.class));
                finish();
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}