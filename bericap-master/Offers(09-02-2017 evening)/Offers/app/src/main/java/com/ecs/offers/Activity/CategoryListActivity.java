package com.ecs.offers.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.ecs.library.slider.SliderLayout;
import com.ecs.library.slider.animations.DescriptionAnimation;
import com.ecs.library.slider.sliderType.BaseSliderView;
import com.ecs.library.slider.sliderType.TextSliderView;
import com.ecs.offers.Adapter.PagerAdapter;
import com.ecs.offers.CustomClasses.CustomSideDrawer;
import com.ecs.offers.R;

import java.util.TreeMap;

public class CategoryListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Context context;
  //  GridView gvShopingList;
    SliderLayout mDemoSlider;
    Integer[] imgid = {
            R.drawable.ic_slider1,
            R.drawable.ic_slider1,
           // R.drawable.ic_offer3,
            R.drawable.ic_slider1,
            R.drawable.ic_slider1,
           // R.drawable.ic_offer3,

    };
    String[] itemname = {
            "Electronic",
            "Fashion",
            "Footwear",
            "Beauty and Health",
            "Home and Furnishing",
            "Jewellery",
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        context = CategoryListActivity.this;

        //navigation drawer
        setUpNavigationView(savedInstanceState);
        setTab();

        setSlider();
        //intialize ui
        Initialize();

    }

    private void Initialize() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //context = BookingActivity.this;
//        gvShopingList = (GridView) findViewById(R.id.dashboard_gv_list);
//        gvShopingList.setOnItemClickListener(this);
//        CategoriesListAdapter adapter = new CategoriesListAdapter(CategoryListActivity.this, itemname, imgid);
//        gvShopingList.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> view, View v, int position, long arg3) {
        // TODO Auto-generated method stub
        Intent intent = null;
        switch (view.getId()) {
//            case R.id.dashboard_gv_list:
//                intent = new Intent(context, CategoryDetailActivity.class);
//                startActivity(intent);
//                break;
        }
    }
    private void setSlider() {
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        int i = 0;
        TreeMap<String, Integer> file_maps = new TreeMap<String, Integer>();
        // file_maps= R.drawable.ic_shoping,R.drawable.ic_travel;

        file_maps.put("title", R.drawable.ic_slider1);
        file_maps.put("test1", R.drawable.ic_slider1);

        //TODO Sort it
        for (String name : file_maps.keySet()) {

            TextSliderView textSliderView = new TextSliderView(context);
            // initialize a SliderLayout
            textSliderView
                    .description("test")
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", "");

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer("Default");
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(3000);
    }
    // TODO: 12/2/2015 for Navigation
    private void setUpNavigationView(Bundle savedInstanceState) {
        CustomSideDrawer oSlideDrawer = new CustomSideDrawer(context, CategoryListActivity.this,
                savedInstanceState,"Category Detail");
        oSlideDrawer.toggleDrawer();
    }
    private void setTab() {

        try {
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

            tabLayout.addTab(tabLayout.newTab().setText("Home").setIcon(R.drawable.ic_home));
            tabLayout.addTab(tabLayout.newTab().setText("Category").setIcon(R.drawable.ic_category));
            tabLayout.addTab(tabLayout.newTab().setText("Compare").setIcon(R.drawable.ic_compare));
            tabLayout.addTab(tabLayout.newTab().setText("Favorites").setIcon(R.drawable.ic_fevorite));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

            final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), CategoryListActivity.this);
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
