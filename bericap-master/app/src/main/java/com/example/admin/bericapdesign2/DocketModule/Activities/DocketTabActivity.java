package com.example.admin.bericapdesign2.DocketModule.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import com.example.admin.bericapdesign2.Activity.CustomSideDrawer;
import com.example.admin.bericapdesign2.DocketModule.Adapter.DocketTabAdapter;
import com.example.admin.bericapdesign2.R;


public class DocketTabActivity extends AppCompatActivity {
    Context context;
    ViewPager viewPager;
    TabLayout tabLayout;
    DocketTabAdapter adapter;
    String sFlag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplyr_dashboard);
        context = DocketTabActivity.this;

        setUpNavigationView(savedInstanceState);
        getIntentData();
        initilizeUI();
    }

    private void getIntentData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sFlag = extras.getString("flag");
        }
    }

    private void initilizeUI() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupViewPager(viewPager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                final InputMethodManager imm = (InputMethodManager)getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(viewPager.getWindowToken(), 0);
            }

            @Override
            public void onPageScrolled(int position, float offset, int offsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new DocketTabAdapter(getResources(), getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setUpNavigationView(Bundle savedInstanceState) {
        CustomSideDrawer oSlideDrawer = new CustomSideDrawer(context, DocketTabActivity.this,
                savedInstanceState,"main");
        oSlideDrawer.toggleDrawer();
    }
}
