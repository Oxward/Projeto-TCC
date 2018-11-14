package com.example.aluno.googlemap.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.aluno.googlemap.R;
import com.example.aluno.googlemap.fragments.Bus1;
import com.example.aluno.googlemap.fragments.Bus2;
import com.example.aluno.googlemap.fragments.Bus3;
import com.example.aluno.googlemap.fragments.SectionPageAdapter;

public class IntinerarioActivity extends AppCompatActivity {

    private static final String TAG = "IntinerarioActivity";

    private SectionPageAdapter mSectionPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intinerario);
        Log.d(TAG, "onCreate: Iniciou");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        ViewPager mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

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

    private void setupViewPager(ViewPager viewPager) {
        mSectionPageAdapter.addFragment(new Bus1(), "OEE-7903");
        mSectionPageAdapter.addFragment(new Bus2(), "OEE-2466");
        mSectionPageAdapter.addFragment(new Bus3(), "NHU-1403");
        viewPager.setAdapter(mSectionPageAdapter);
    }
}