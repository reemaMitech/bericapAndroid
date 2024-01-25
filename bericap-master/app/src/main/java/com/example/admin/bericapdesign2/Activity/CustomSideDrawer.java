package com.example.admin.bericapdesign2.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.bericapdesign2.Activity.notification.NotificationActivity;
import com.example.admin.bericapdesign2.DocketModule.Activities.DocketTabActivity;
import com.example.admin.bericapdesign2.R;
import com.example.admin.bericapdesign2.Supply.Activities.SupllyrTabActivity;
import com.example.admin.bericapdesign2.Util.ACU;
import com.example.admin.bericapdesign2.Util.LTU;


public class CustomSideDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Context mContext;
    private static final String SELECTED_ITEM_ID = "selected_item_id";
    AppCompatActivity activity;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    Bundle savedInstanceState;
    private int mSelectedId;
    private boolean mUserSawDrawer = false;
    private static final String FIRST_TIME = "first_time";
    Toolbar toolbar;
    String sFlag = "";
    TextView tvUserName;

    public CustomSideDrawer(Context context, AppCompatActivity activity) {
        mContext = context;
        this.activity = activity;
    }

    public CustomSideDrawer(Context context, AppCompatActivity activity, Bundle bSavedInstanceState, String sFlag) {
        mContext = context;
        this.activity = activity;
        this.sFlag = sFlag;
        //    toolbar = mToolBar;
        savedInstanceState = bSavedInstanceState;
    }

    public void toggleDrawer() {
        setUpToolBar();
        intialization();
    }

    private void setUpToolBar() {
        toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        ImageView imgRefresh = (ImageView) activity.findViewById(R.id.appbar_iv_refresh);

        imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sFlag.equals("main")) {
                    activity.finish();
                    activity.startActivity(
                            new Intent(activity, DocketTabActivity.class));
                } else {
                    activity.finish();
                    activity.startActivity(
                            new Intent(activity, SupllyrTabActivity.class));
                }

            }
        });
        activity.setSupportActionBar(toolbar);
    }

    private void intialization() {
        mDrawer = (NavigationView) activity.findViewById(R.id.nav_view);
        mDrawer.setNavigationItemSelectedListener(this);
        View header = mDrawer.getHeaderView(0);
        tvUserName = (TextView) header.findViewById(R.id.nav_header_tv_username);
        tvUserName.setText(ACU.MySP.getFromSP(mContext, ACU.MySP.USER_NAME, ""));


        mDrawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(activity,
                mDrawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        if (!didUserSeeDrawer()) {
            showDrawer();
            markDrawerSeen();
        } else {
            hideDrawer();
        }
        //  mSelectedId = savedInstanceState == null ? R.id.nav_adl : savedInstanceState.getInt(SELECTED_ITEM_ID);

    }

    private boolean didUserSeeDrawer() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        mUserSawDrawer = sharedPreferences.getBoolean(FIRST_TIME, false);
        return mUserSawDrawer;
    }

    // TODO: 12/2/2015 for Navigation
    private void showDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    // TODO: 12/2/2015 for Navigation
    private void hideDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    public void onBackPressed() {
        // TODO Auto-generated method stub
        //  activity.finish();
        //  Dialog.showDialogOnBackPressed(activity, getResources().getString(R.string.activity_exist));
    }

    private void markDrawerSeen() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        mUserSawDrawer = true;
        sharedPreferences.edit().putBoolean(FIRST_TIME, mUserSawDrawer).apply();
    }

    // TODO: 12/2/2015 for Navigation
    private void navigate(int mSelectedId) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (mSelectedId) {

            case R.id.nav_docketsearch:
                activity.startActivity(
                        new Intent(activity, DocketSearchActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;

            case R.id.nav_changepassword:
                activity.startActivity(
                        new Intent(activity, ChangePasswordActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                activity.finish();
                break;

            case R.id.nav_notification:
                activity.startActivity(
                        new Intent(activity, NotificationActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;

            case R.id.nav_logout:
                ACU.MySP.setSPBoolean(mContext, ACU.MySP.LOGIN_STATUS, false);
                activity.finish();
                activity.startActivity(
                        new Intent(activity, LoginActivity.class));
                break;
            default:
                LTU.TIS(mContext, "", "Coming soon...!");
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mSelectedId = menuItem.getItemId();
        navigate(mSelectedId);
        return true;
    }
}