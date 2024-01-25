package com.example.admin.bericapdesign2.Supply.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.admin.bericapdesign2.AsyncTask.AsyncResponse;
import com.example.admin.bericapdesign2.AsyncTask.MyAsyncTask;
import com.example.admin.bericapdesign2.CustomClasses.CustomButton;
import com.example.admin.bericapdesign2.CustomClasses.CustomTextView;
import com.example.admin.bericapdesign2.R;
import com.example.admin.bericapdesign2.Supply.Adapter.SupplyrDocketListAdapter;
import com.example.admin.bericapdesign2.Supply.Model.SupplyrDocketListModel;
import com.example.admin.bericapdesign2.Util.ACU;
import com.example.admin.bericapdesign2.Util.Connectivity;
import com.example.admin.bericapdesign2.Util.Constants;
import com.example.admin.bericapdesign2.Util.LTU;
import com.example.admin.bericapdesign2.Util.MU;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UnLoadingRecentDocketsListFragment extends Fragment implements AsyncResponse, SearchView.OnQueryTextListener {
    String TAG = "UnLoadingRecentDocketsListFragment";
    Context context;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    private ArrayList<SupplyrDocketListModel> mArrayList;
    private SupplyrDocketListAdapter vAdapter;
    CustomTextView tv_vehiclenodata;
    public static boolean isChecked = true;
    SearchView searchView;

    RelativeLayout rl_retry;
    CustomButton btn_retry;

    public static UnLoadingRecentDocketsListFragment newInstance() {
        UnLoadingRecentDocketsListFragment fragment = new UnLoadingRecentDocketsListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();

        View rootView = inflater.inflate(R.layout.activity_unloading_docketlist, container, false);

        initailizeUI(rootView);
        getDocketList();
        //  hideSoftKeyboard(rootView);
        return rootView;
    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0); // hide
        } else {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY); // show
        }
    }

    private void initailizeUI(View rootView) {
        mArrayList = new ArrayList<>();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_userlist);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.VehicleActivitySwipeRefresh);
        tv_vehiclenodata = (CustomTextView) rootView.findViewById(R.id.tv_nodata);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doYourUpdate();
            }
        });
        searchView = (SearchView) rootView.findViewById(R.id.search);

        searchView.setOnQueryTextListener(this);
        searchView.onActionViewExpanded();
        searchView.setIconified(true);
        searchView.clearFocus();
        searchView.setFocusable(false);
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setHint("Search...");
        searchEditText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, getResources().getDimension(R.dimen._4sdp));
        searchEditText.setTextColor(getResources().getColor(R.color.ColorBlack));
        searchEditText.setHintTextColor(getResources().getColor(R.color.ColorBlack));
        search(searchView);

        rl_retry = (RelativeLayout) rootView.findViewById(R.id.rl_retry);
        btn_retry = (CustomButton) rootView.findViewById(R.id.btn_retry);
        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDocketList();
            }
        });
        hideSoftKeyboard(rootView);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchView.clearFocus();
        return true;

    }

    @Override
    public boolean onQueryTextChange(String newText) {
        searchView.clearFocus();
        return true;
    }

    private void getDocketList() {

        final List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("SOURCE", "mobile"));
        params.add(new BasicNameValuePair("APPLICATION_ID", ACU.MySP.getFromSP(context, ACU.MySP.APPLICATION_ID, "")));
        params.add(new BasicNameValuePair("ROLE_ID", ACU.MySP.getFromSP(context, ACU.MySP.ROLE_ID, "")));
        params.add(new BasicNameValuePair("LOCATION_ID", ACU.MySP.getFromSP(context, ACU.MySP.LOCATION_ID, "")));


        String url = Constants.SERVER_URL + Constants.REGISTER_INCOMING_WORKLIST;
        uploadData(url, params, "post", Constants.REGISTER_INCOMING_WORKLIST, "Y");
    }

    public void uploadData(String url, List<NameValuePair> params, String method, String function, String isProgressbar) {
        if (Connectivity.isConnectedFast(context)) {
            new MyAsyncTask(this, context, method, url, null, params, function, isProgressbar).execute();
        } else {
            LTU.TEL(context, MU.NO_INERTNET_CONNECTION, MU.NO_INERTNET_CONNECTION);
        }
    }

    private void doYourUpdate() {
        // TODO implement a refresh
        swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
        getDocketList();
    }

    public void setListAdapter(JSONArray array) {
        mArrayList.clear();
        try {
            JSONObject jsonObject;
            for (int i = 0; i < array.length(); i++) {
                SupplyrDocketListModel userModel = new SupplyrDocketListModel();
                jsonObject = array.getJSONObject(i);

                userModel.set_CUST_NAME(jsonObject.getString("SUP_NAME"));
                userModel.set_CUSTOMER_ADDRESS_1(jsonObject.getString("SUPPLIER_ADDRESS_1"));
                userModel.set_DEL_DOC_NO(jsonObject.getString("DEL_DOC_NO"));
                userModel.set_DOCK_STATUS(jsonObject.getString("DOCK_STATUS"));
                userModel.set_DOCKET_CREATED_TIME(jsonObject.getString("DOCKET_CREATED_TIME"));
                userModel.set_DOCKET_ID(jsonObject.getString("INCOMING_DOCKET_ID"));
                userModel.set_DOCKET_NO(jsonObject.getString("INCOMING_DOCKET_NO"));
                userModel.set_DRIVER_NAME(jsonObject.getString("DRIVER_NAME"));
                userModel.set_LOAD_TYP(jsonObject.getString("LOAD_TYP"));
                userModel.set_LOCATION_NAME(jsonObject.getString("LOCATION_NAME"));
                userModel.set_POST_LOAD_IMG(jsonObject.getString("POST_LOAD_IMG"));
                userModel.set_PRE_LOAD_IMG(jsonObject.getString("PRE_LOAD_IMG"));
                userModel.set_REMARKS_OVERALL(jsonObject.getString("REMARKS_OVERALL"));
                userModel.set_SEAL_1_IMG(jsonObject.getString("SEAL_1_IMG"));
                userModel.set_SEAL_1_NO(jsonObject.getString("SEAL_1_NO"));
                userModel.set_SEAL_2_NO(jsonObject.getString("SEAL_2_NO"));
                userModel.set_TRUCK_NO(jsonObject.getString("TRUCK_NO"));
                userModel.set_GRN_NO(jsonObject.getString("GRN_NO"));
                userModel.set_INVOICE_NO(jsonObject.getString("INVOICE_NO"));
                userModel.set_UNLOADING_STATUS(jsonObject.getString("UNLOADING_STATUS"));
                mArrayList.add(userModel);
            }

            // The number of Columns
            mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(mLayoutManager);

            if (mArrayList.size() > 0) {
                recyclerView.setVisibility(View.VISIBLE);
                tv_vehiclenodata.setVisibility(View.GONE);
                vAdapter = new SupplyrDocketListAdapter(context, mArrayList, Constants.RECENT);
                recyclerView.setAdapter(vAdapter);
            } else {
                recyclerView.setVisibility(View.GONE);
                tv_vehiclenodata.setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (mArrayList.size() > 0)
                    vAdapter.getFilter().filter(newText);

                if (isChecked) {
                    tv_vehiclenodata.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    tv_vehiclenodata.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                return true;
            }
        });
    }

    @Override
    public void processFinish(String result, String functName) {
        Log.e("incoming_recent_data_to_display:->", result.trim());

        try {
            if (functName.equals(Constants.REGISTER_INCOMING_WORKLIST)) {

                try {
                    if (result != null && !result.equals("fail")) {

                        JSONObject object = new JSONObject(result);

                        String sDocketListResult = object.getString("incoming_recent_data_to_display");
                        if (!sDocketListResult.equals("false")) {
                            recyclerView.setVisibility(View.VISIBLE);
                            tv_vehiclenodata.setVisibility(View.GONE);
                            JSONArray array = new JSONArray();
                            array = new JSONArray(sDocketListResult);
                            setListAdapter(array);
                        } else {
                            recyclerView.setVisibility(View.GONE);
                            tv_vehiclenodata.setVisibility(View.VISIBLE);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
