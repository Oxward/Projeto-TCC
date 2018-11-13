package com.example.aluno.googlemap.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.aluno.googlemap.R;
import com.example.aluno.googlemap.fragments.MapFragmentAdapter;
import com.example.aluno.googlemap.fragments.MapFragmentB1;
import com.example.aluno.googlemap.fragments.MapFragmentB2;
import com.example.aluno.googlemap.fragments.MapFragmentB3;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private MapFragmentAdapter mMapFragmentAdapter;

    private byte currentTab = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ViewPager viewPager = findViewById(R.id.vp_pages);
        mMapFragmentAdapter = new MapFragmentAdapter(getSupportFragmentManager());
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tbl_pages);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTab = (byte) tab.getPosition();
                Toast.makeText(MainActivity.this, "Pág " + currentTab, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {

            case R.id.nav_schedule:
                try {
                    Intent intent = new Intent(MainActivity.this, IntinerarioActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d(TAG, "" + e.getMessage());
                }
                break;

            case R.id.nav_share:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onButtonPress(View view) {

        switch (currentTab) {
            case 0:
                switch (view.getId()) {
                    case R.id.FABmorning:
                        Toast.makeText(this, "FABMorning", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.FABafternoon:
                        Toast.makeText(this, "FABafternoon", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.FABevening:
                        Toast.makeText(this, "FABevening", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;

            case 1:
                switch (view.getId()) {
                    case R.id.FABmorning:
                        Toast.makeText(this, "FABMorning", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.FABafternoon:
                        Toast.makeText(this, "FABafternoon", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.FABevening:
                        Toast.makeText(this, "FABevening", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;

            case 2:
                switch (view.getId()) {
                    case R.id.FABmorning:
                        Toast.makeText(this, "FABMorning", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.FABafternoon:
                        Toast.makeText(this, "FABafternoon", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.FABevening:
                        Toast.makeText(this, "FABevening", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        mMapFragmentAdapter.addFragment(new MapFragmentB1(), "OEE-7906");
        mMapFragmentAdapter.addFragment(new MapFragmentB2(), "OEE-2466");
        mMapFragmentAdapter.addFragment(new MapFragmentB3(), "NHU-1403");
        viewPager.setAdapter(mMapFragmentAdapter);
    }
}