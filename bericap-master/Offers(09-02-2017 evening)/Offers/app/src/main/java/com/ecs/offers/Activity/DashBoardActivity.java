package com.ecs.offers.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ecs.offers.Adapter.PagerAdapter;
import com.ecs.offers.CustomClasses.CustomSideDrawer;
import com.ecs.offers.R;

public class DashBoardActivity extends AppCompatActivity {
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        context = DashBoardActivity.this;
        setUpNavigationView(savedInstanceState);
        setTab();
    }
    // TODO: 12/2/2015 for Navigation
    private void setUpNavigationView(Bundle savedInstanceState) {
        CustomSideDrawer oSlideDrawer = new CustomSideDrawer(context, DashBoardActivity.this,
                savedInstanceState, "Deal & Offers Guide");
        oSlideDrawer.toggleDrawer();
    }

    private void setTab() {

        try {
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

            tabLayout.addTab(tabLayout.newTab().setText("Home").setIcon(R.drawable.home_selectd));
            tabLayout.addTab(tabLayout.newTab().setText("Category").setIcon(R.drawable.ic_category));
            tabLayout.addTab(tabLayout.newTab().setText("Compare").setIcon(R.drawable.ic_compare));
            tabLayout.addTab(tabLayout.newTab().setText("Favorites").setIcon(R.drawable.ic_fevorite));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

            final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), DashBoardActivity.this);
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    try {
                        viewPager.setCurrentItem(tab.getPosition());
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        } catch (Exception e) {

        }
    }
    }

