package com.ecs.offers.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.ecs.offers.Adapter.OfferListAdapter;
import com.ecs.offers.CustomClasses.CustomButton;
import com.ecs.offers.R;
import com.ecs.offers.database.DataBaseHelper;
import com.ecs.offers.util.BU;
import com.ecs.offers.util.LTU;
import com.ecs.offers.util.MU;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ECS on 08/02/2017.
 */
public class OffersListActivity extends AppCompatActivity {

    Context context;
    View view;
    ListView lvGetDealList;
    DataBaseHelper dh;
    Spinner spSelectBank, spCardType;
    String selectedCategory = "",selectedSubCategory = "";
    CustomButton btnFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_list);
        context = OffersListActivity.this;
        dh = new DataBaseHelper(context);
        try {
            Initialize();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void Initialize() throws JSONException {

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        lvGetDealList = (ListView) findViewById(R.id.getdeal_lv_getdeal);
        spSelectBank = (Spinner) findViewById(R.id.paymentoption_spinner_selectbank);
        spCardType = (Spinner) findViewById(R.id.paymentoption_spinner_cardtype);
        spCardType = (Spinner) findViewById(R.id.paymentoption_spinner_cardtype);
        btnFilter = (CustomButton) findViewById(R.id.btn_filter);

        setFilterButton();
        setCategoryListSpinner();
        setSubCategorySpinner();
        setOfferListAdapter();

    }

    private void setFilterButton() {

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                setOfferListOnFilterButton();
            }
        });
    }

    private boolean validate() {

        if(selectedCategory.equals("")){
            LTU.TOAST_L(context, MU.SELECT_CATEGORY_MSG);
            return false;
        }else if(selectedSubCategory.equals("")){
            LTU.TOAST_L(context, MU.SELECT_SUB_CATEGORY_MSG);
            return false;
        }else{
            return true;
        }

    }

    private void setOfferListOnFilterButton() {

        JSONArray array = null;
        try {
            array = dh.getOfferListOnFilter(selectedCategory, selectedSubCategory);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("array",""+array.length());
        if(array!=null) {
            if (array.length() == 0)
                LTU.TOAST_L(context, MU.EMPTY_OFFER_MSG);

                OfferListAdapter adapter = new OfferListAdapter(OffersListActivity.this, array);
                lvGetDealList.setAdapter(adapter);

        }
    }

    private void setCategoryListSpinner() {

        ArrayList list = dh.getCategorySubCategoryList("category");
        if(list != null) {

            BU.SetAdapter(context, spSelectBank, list);
            spSelectBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if (position != 0) {
                        selectedCategory = spSelectBank.getSelectedItem().toString();
                    }else{
                        selectedCategory = "";
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    private void setSubCategorySpinner() {

        ArrayList list = dh.getCategorySubCategoryList("sub_category");
        if(list != null) {
            BU.SetAdapter(context, spCardType, list);

            spCardType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if (position != 0) {
                        selectedSubCategory = spCardType.getSelectedItem().toString();
                    }else{
                        selectedSubCategory = "";
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }


    private void setOfferListAdapter() throws JSONException {

        JSONArray array = null;
        array = dh.getOfferList();
        Log.e("array",""+array.length());
        if(array!=null) {
            if (array.length() > 0) {
                OfferListAdapter adapter = new OfferListAdapter(OffersListActivity.this, array);
                lvGetDealList.setAdapter(adapter);
            }
        }

    }

}
