package com.ecs.offers.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ecs.library.slider.SliderLayout;
import com.ecs.library.slider.animations.DescriptionAnimation;
import com.ecs.library.slider.sliderType.BaseSliderView;
import com.ecs.library.slider.sliderType.TextSliderView;
import com.ecs.offers.R;

import java.util.TreeMap;

public class Tab1Fragment extends Fragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    Context context;
    GridView gvTodayList, gvTrendingList,gvOfferList;
    SliderLayout mDemoSlider;
    Integer[] imgid = {
            R.drawable.ic_shoping,
            R.drawable.ic_travel,
            R.drawable.ic_hospitality,

    };
    String[] itemname = {
            "Shopping",
            "Travel",
            "Hospitality",
    };
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_tab1, container, false);
        context = getActivity();

        Initialize();
        setSlider();
        return view;
    }

    private void Initialize() {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        gvTodayList = (GridView) view.findViewById(R.id.dashboard_gv_list);
//        gvTrendingList = (GridView) view.findViewById(R.id.dashboard_gv_trendinglist);
//        gvOfferList = (GridView) view.findViewById(R.id.dashboard_gv_todayslist);
//        gvTodayList.setOnItemClickListener(this);

//        CustomListAdapter adapter = new CustomListAdapter(getActivity(), itemname, imgid);
//
//        gvTodayList.setAdapter(adapter);
//        gvTrendingList.setAdapter(adapter);
//        gvOfferList.setAdapter(adapter);
    }
    @Override
    public void onItemClick(AdapterView<?> view, View v, int position, long arg3) {
        // TODO Auto-generated method stub
        Intent intent = null;
        switch (view.getId()) {
//            case R.id.dashboard_gv_list:
//                intent = new Intent(getActivity(), CategoryListActivity.class);
//                startActivity(intent);
//                break;
        }
    }

    private void setSlider() {
        mDemoSlider = (SliderLayout)view.findViewById(R.id.slider);
        int i = 0;
        TreeMap<String, Integer> file_maps = new TreeMap<String, Integer>();
        // file_maps= R.drawable.ic_shoping,R.drawable.ic_travel;

        file_maps.put("title", R.drawable.ic_slider1);
        file_maps.put("test1", R.drawable.ic_slider1);

        //TODO Sort it
        for (String name : file_maps.keySet()) {

            TextSliderView textSliderView = new TextSliderView(getActivity());
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
}


