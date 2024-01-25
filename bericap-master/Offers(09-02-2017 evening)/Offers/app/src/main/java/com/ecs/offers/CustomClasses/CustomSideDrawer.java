package com.ecs.offers.CustomClasses;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ecs.offers.Activity.DashBoardActivity;
import com.ecs.offers.R;

public class CustomSideDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Context mContext;
    AppCompatActivity activity;
    Bundle savedInstanceState;
    Toolbar toolbar;
    DrawerLayout drawer;
    String sTitle="";
    CustomTextView tvSignIn;
   // @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_custom_side_drawer);



        public CustomSideDrawer(Context context, AppCompatActivity activity, Bundle bSavedInstanceState, String sTitle) {
            mContext = context;
            this.activity = activity;
            //    toolbar = mToolBar;
            savedInstanceState = bSavedInstanceState;
            this.sTitle=sTitle;
        }

        public void toggleDrawer() {

            setToolBar();
            intialization();
        }


    private void setToolBar() {
        toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        tvSignIn = (CustomTextView)activity.findViewById(R.id.toolbar_tv_signin);
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent mainIntent = new Intent(activity, LoginActivity.class);
                // activity.startActivity(mainIntent);
            }
        });
        activity.setSupportActionBar(toolbar);
    }

    private void intialization() {
        mContext = CustomSideDrawer.this;
        drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.custom_side_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent mainIntent = new Intent(activity, DashBoardActivity.class);
            activity.startActivity(mainIntent);
            drawer.closeDrawers();
        } else if (id == R.id.nav_notification) {
            Toast.makeText(activity, "Comming soon.....", Toast.LENGTH_SHORT).show();
            drawer.closeDrawers();
        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
