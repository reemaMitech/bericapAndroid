package com.ecs.offers.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.ecs.offers.Fragment.PaymentFragment;
import com.ecs.offers.Fragment.Tab1Fragment;

/**
 * Created by dell on 1/24/2017.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    AppCompatActivity activity;

    public PagerAdapter(FragmentManager fm, int NumOfTabs,AppCompatActivity activity) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.activity=activity;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PaymentFragment tab1 = new PaymentFragment();
                return tab1;
            case 1:
                Tab1Fragment tab2 = new Tab1Fragment();
                return tab2;
//            case 2:
//                CompreActivity tab3 = new CompreActivity();
//                return tab3;
//            case 3:
//                FavouriteListActivity tab4 = new FavouriteListActivity();
//                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
