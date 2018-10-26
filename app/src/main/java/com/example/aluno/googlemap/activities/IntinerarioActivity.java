package com.example.aluno.googlemap.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

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


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:
                        Log.d(TAG, "onTabSelected: Tab 1");
                        break;

                    case 1:
                        Log.d(TAG, "onTabSelected: Tab 2");
                        break;

                    case 2:
                        Log.d(TAG, "onTabSelected: Tab 3");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        mSectionPageAdapter.addFragment(new Bus1(), "Bus1");
        mSectionPageAdapter.addFragment(new Bus2(), "Bus2");
        mSectionPageAdapter.addFragment(new Bus3(), "Bus3");
        viewPager.setAdapter(mSectionPageAdapter);
    }
}
