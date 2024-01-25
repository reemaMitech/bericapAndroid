package com.ecs.offers.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.ecs.offers.CustomClasses.CustomSideDrawer;
import com.ecs.offers.R;

public class CategoryDetailActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    Context context;
   // GridView gvShopingList;
    Integer[] imgid = {
            R.drawable.ic_slider1,
            R.drawable.ic_slider1,
          //  R.drawable.ic_offer3,
            R.drawable.ic_slider1,
            R.drawable.ic_slider1,
          //  R.drawable.ic_offer3,
            R.drawable.ic_slider1,
           // R.drawable.ic_offer3,

    };
    String[] itemname = {
            "Mobiles",
            "Computers",
            "Accessories",
            "Camera",
            "Car Accessories",
            "Home Appliance",
            "Toys",
            "Others",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        context = CategoryDetailActivity.this;

        //navigation drawer
        setUpNavigationView(savedInstanceState);

        //intialize ui
       // Initialize();


    }

    // TODO: 12/2/2015 for Navigation
    private void setUpNavigationView(Bundle savedInstanceState) {
        CustomSideDrawer oSlideDrawer = new CustomSideDrawer(context, CategoryDetailActivity.this,
                savedInstanceState,"Category Detail");
        oSlideDrawer.toggleDrawer();
    }
    private void Initialize() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //context = BookingActivity.this;
      //  gvShopingList = (GridView) findViewById(R.id.dashboard_gv_list);
      //  gvShopingList.setOnItemClickListener(this);
//        CategoriesDetailListAdapter adapter = new CategoriesDetailListAdapter(CategoryDetailActivity.this, itemname, imgid);
//        gvShopingList.setAdapter(adapter);

    }
    @Override
    public void onItemClick(AdapterView<?> view, View v, int position, long arg3) {
        // TODO Auto-generated method stub
        Intent intent = null;
        switch (view.getId()) {
         //   case R.id.dashboard_gv_list:
               // intent = new Intent(context, PaymentOptionsActivity.class);
              //  startActivity(intent);
            //    break;
        }
    }

}
