package com.example.aluno.googlemap.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.aluno.googlemap.R;
import com.example.aluno.googlemap.fragments.Bus1;
import com.example.aluno.googlemap.fragments.Bus2;
import com.example.aluno.googlemap.fragments.Bus3;
import com.example.aluno.googlemap.fragments.SectionPageAdapter;

public class IntinerarioActivity extends AppCompatActivity {

    private static final String TAG = "IntinerarioActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intinerario);
        Log.d(TAG, "onCreate: Iniciou");

        SectionPageAdapter mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        ViewPager mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Bus1(), "Bus1");
        adapter.addFragment(new Bus2(), "Bus2");
        adapter.addFragment(new Bus3(), "Bus3");
        viewPager.setAdapter(adapter);
    }
}
