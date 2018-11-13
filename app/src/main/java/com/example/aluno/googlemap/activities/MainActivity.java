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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.AppTheme_NoActionBar);
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
    }

    private void setupViewPager(ViewPager viewPager) {
        mMapFragmentAdapter.addFragment(new MapFragmentB1(), "Bus1");
        mMapFragmentAdapter.addFragment(new MapFragmentB2(), "Bus2");
        mMapFragmentAdapter.addFragment(new MapFragmentB3(), "Bus3");
        viewPager.setAdapter(mMapFragmentAdapter);
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

            case R.id.nav_manage:
                break;

            case R.id.nav_share:
                break;

            case R.id.nav_send:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_intinerario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;

            case R.id.B1:
                Toast.makeText(this, "B1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.B2:
                Toast.makeText(this, "B2", Toast.LENGTH_SHORT).show();
                break;

            case R.id.B3:
                Toast.makeText(this, "B3", Toast.LENGTH_SHORT).show();
                break;

            case R.id.teste_group:
                Toast.makeText(this, "teste_group", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
