package com.ecs.offers.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecs.library.slider.SliderLayout;
import com.ecs.library.slider.animations.DescriptionAnimation;
import com.ecs.library.slider.sliderType.BaseSliderView;
import com.ecs.library.slider.sliderType.TextSliderView;
import com.ecs.offers.Activity.SelectPaymentOptionsActivity;
import com.ecs.offers.Adapter.CategoryAdapter;
import com.ecs.offers.Adapter.HLVAdapter;
import com.ecs.offers.Adapter.TrendingOfferAdapter;
import com.ecs.offers.R;
import com.ecs.offers.database.DataBaseConstants;
import com.ecs.offers.database.DataBaseHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;


public class PaymentFragment extends Fragment implements AdapterView.OnItemClickListener,View.OnClickListener {
    SliderLayout mDemoSlider;
    RecyclerView mRecyclerView,categoryRecyclerView,trendingRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.Adapter mCategoryAdapter;
    RecyclerView.Adapter mTrendingAdapter;
    ArrayList<String> alName;
    ArrayList<String> alImage;
    ArrayList<String> catregoryalName;
    ArrayList<String> categoryalImage;
    ArrayList<String> trendingalName;
    ArrayList<String> trendingalImage;
    ImageView ivLeftArrow, ivRighttArrow,ivCategoryLeftArrow,ivCategoryRighttArrow,ivTrendingLeftArrow,ivTrendingRightArrow;
    Context context;
    DataBaseHelper dh;
    Cursor cursor;
    LinearLayoutManager mLayoutManager,categoryLayoutManager,trendingLayoutManager;
    View view;
    TextView txtPaymentOfferKnowMore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_payment, container, false);
        context = getActivity();
        dh = new DataBaseHelper(context);
        setSlider();
        Initialize();
        return view;
    }

    private void Initialize() {

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        txtPaymentOfferKnowMore = (TextView) view.findViewById(R.id.txt_payment_offer_know_more);
        txtPaymentOfferKnowMore.setOnClickListener(this);

        ivLeftArrow = (ImageView) view.findViewById(R.id.iv__slide_leftarrow);
        ivLeftArrow.setOnClickListener(this);
        ivRighttArrow = (ImageView) view.findViewById(R.id.iv__slide_rightarrow);
        ivRighttArrow.setOnClickListener(this);

        cursor = dh.getData(DataBaseConstants.TableNames.TBL_CARD_DETAILS);
        if(cursor != null) {
            alImage = new ArrayList<String>();
            alName = new ArrayList<String>();
            setOffersOnPayemtList(cursor);
        }

        cursor = dh.getData(DataBaseConstants.TableNames.TBL_CATEGORY_DETAILS);
        if(cursor != null) {
            catregoryalName = new ArrayList<String>();
            categoryalImage = new ArrayList<String>();
            setOffersOnCategoryList(cursor);
        }

        cursor = dh.getData(DataBaseConstants.TableNames.TBL_TRENDING_OFFERS);
        if(cursor != null) {
            trendingalName = new ArrayList<String>();
            trendingalImage = new ArrayList<String>();
            setOffersOnTrendingList(cursor);
        }

        ivCategoryLeftArrow = (ImageView) view.findViewById(R.id.catergory_iv__slide_leftarrow);
        ivCategoryLeftArrow.setOnClickListener(this);
        ivCategoryRighttArrow = (ImageView) view.findViewById(R.id.category_iv__slide_rightarrow);
        ivCategoryRighttArrow.setOnClickListener(this);

        ivTrendingLeftArrow = (ImageView) view.findViewById(R.id.trending_iv__slide_leftarrow);
        ivTrendingLeftArrow.setOnClickListener(this);
        ivTrendingRightArrow = (ImageView) view.findViewById(R.id.trending_iv__slide_rightarrow);
        ivTrendingRightArrow.setOnClickListener(this);

        // Calling the RecyclerView for offers on payments in dashboard
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // Calling the RecyclerView for offers on Category in dashboard
        categoryRecyclerView = (RecyclerView) view.findViewById(R.id.category_recycler_view);
        categoryRecyclerView.setHasFixedSize(true);

        // Calling the RecyclerView for trending offers in dashboard
        trendingRecyclerView = (RecyclerView) view.findViewById(R.id.trending_recycler_view);
        trendingRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        categoryLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);


        trendingLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        trendingRecyclerView.setLayoutManager(trendingLayoutManager);
        //  mLayoutManager.scrollToPositionWithOffset(2, 20);


        if(alImage != null) {
            if (alImage.size() > 0) {
                mAdapter = new HLVAdapter(getActivity(), alName, alImage);
                mRecyclerView.setAdapter(mAdapter);
            }
        }

        if(categoryalImage != null) {
            if (categoryalImage.size() > 0) {
                mCategoryAdapter = new CategoryAdapter(getActivity(), catregoryalName, categoryalImage);
                categoryRecyclerView.setAdapter(mCategoryAdapter);
            }
        }

        if(trendingalImage != null) {
            if (trendingalImage.size() > 0) {
                mTrendingAdapter = new TrendingOfferAdapter(getActivity(), trendingalName, trendingalImage);
                trendingRecyclerView.setAdapter(mTrendingAdapter);
            }
        }

  }

    private TreeMap<String, String> setBannerList(Cursor cursor) {
        TreeMap<String, String> file_maps = new TreeMap<String, String>();
        while (cursor.moveToNext()){

            if (!cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblDashboard.SLIDER_IMAGE_URL)).equals("null")) {
                file_maps.put(String.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblDashboard.SLIDER_NAME))),
                        String.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblDashboard.SLIDER_IMAGE_URL))));

            }
        }

        return file_maps;
    }

    private void setOffersOnCategoryList(Cursor cursor) {

        while (cursor.moveToNext()){

            if (!cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblCategoryDetails.CATEGORY_IMAGE)).equals("null")) {
                categoryalImage.add(String.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblCategoryDetails.CATEGORY_IMAGE))));
                catregoryalName.add(String.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblCategoryDetails.CATEGORY_NAME))));
            }
        }
    }

    private void setOffersOnTrendingList(Cursor cursor) {

        while (cursor.moveToNext()){

            if (!cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblTrendingOffers.TRENDING_IMAGE_URL)).equals("null")) {
                trendingalImage.add(String.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblTrendingOffers.TRENDING_IMAGE_URL))));
                trendingalName.add(String.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblTrendingOffers.OFFER_NAME))));
            }
        }
    }

    private void setOffersOnPayemtList(Cursor cursor) {
        while (cursor.moveToNext()){

            if (!cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblCardDetails.CARD_IMAGE_URL)).equals("null")) {
                alImage.add(String.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblCardDetails.CARD_IMAGE_URL))));
                alName.add(String.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblCardDetails.CARD_NAME))));
            }
        }
    }

    private void setSlider() {
        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);
        int i = 0;

        TreeMap<String, String> file_maps = null;

        cursor = dh.getData(DataBaseConstants.TableNames.TBL_DASHBOARD);
        if(cursor != null) {
            file_maps = setBannerList(cursor);
        }

        //TODO Sort it
        if(file_maps != null) {
            for (String name : file_maps.keySet()) {
                Log.e("FOR",""+file_maps.get(name));
                TextSliderView textSliderView = new TextSliderView(getActivity());
                // initialize a SliderLayout
                textSliderView
                        .description("")
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

    @Override
    public void onItemClick(AdapterView<?> view, View v, int position, long arg3) {
        // TODO Auto-generated method stub
        Intent intent = null;
        switch (view.getId()) {

        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.txt_payment_offer_know_more:
                Intent intent = new Intent(context, SelectPaymentOptionsActivity.class);
                context.startActivity(intent);
                break;

            case R.id.iv__slide_rightarrow:
                mRecyclerView.getLayoutManager().scrollToPosition(mLayoutManager.findLastVisibleItemPosition() + 1);
                break;

            case R.id.iv__slide_leftarrow:
                mRecyclerView.getLayoutManager().scrollToPosition(mLayoutManager.findFirstVisibleItemPosition() - 1);
                break;

            case R.id.category_iv__slide_rightarrow:
                categoryRecyclerView.getLayoutManager().scrollToPosition(categoryLayoutManager.findLastVisibleItemPosition() + 1);
                break;

            case R.id.catergory_iv__slide_leftarrow:
                categoryRecyclerView.getLayoutManager().scrollToPosition(categoryLayoutManager.findFirstVisibleItemPosition() - 1);
                break;

            case R.id.trending_iv__slide_rightarrow:
                trendingRecyclerView.getLayoutManager().scrollToPosition(trendingLayoutManager.findLastVisibleItemPosition() + 1);
                break;

            case R.id.trending_iv__slide_leftarrow:
                trendingRecyclerView.getLayoutManager().scrollToPosition(trendingLayoutManager.findFirstVisibleItemPosition() - 1);
                break;
        }
    }
}
