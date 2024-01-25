package com.example.admin.bericapdesign2.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.admin.bericapdesign2.Adapter.DocketListAdapter;
import com.example.admin.bericapdesign2.AsyncTask.AsyncResponse;
import com.example.admin.bericapdesign2.AsyncTask.MyAsyncTask;
import com.example.admin.bericapdesign2.R;
import com.example.admin.bericapdesign2.Services.AccessWebServices;
import com.example.admin.bericapdesign2.Util.ACU;
import com.example.admin.bericapdesign2.Util.Connectivity;
import com.example.admin.bericapdesign2.Util.Constants;
import com.example.admin.bericapdesign2.Util.LTU;
import com.example.admin.bericapdesign2.Util.MU;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncResponse{

    private ListView lvDocketList, lvRcentDocketList;
    String TAG = "ActiveDocketDetails";
    Context context;
    Button btn_search;
    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        setUpNavigationView(savedInstanceState);

        initializeUi();
        getDocketList();

    }

    private void setUpNavigationView(Bundle savedInstanceState) {
        CustomSideDrawer oSlideDrawer = new CustomSideDrawer(getApplicationContext(), MainActivity.this,
                savedInstanceState,"main");
        oSlideDrawer.toggleDrawer();
    }

    private void initializeUi() {
        context = MainActivity.this;
        searchText = (EditText) findViewById(R.id.activity_docket_search_edt_sname);
        btn_search = (Button) findViewById(R.id.activity_docket_search_btn_search);
        lvDocketList = (ListView) findViewById(R.id.content_doket_lv_activedocketlist);
        lvRcentDocketList = (ListView) findViewById(R.id.content_doket_lv_recentdocketlist);
    }

    private void getDocketList() {

        final List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("SOURCE", "mobile"));
        params.add(new BasicNameValuePair("APPLICATION_ID", ACU.MySP.getFromSP(context, ACU.MySP.APPLICATION_ID, "")));
        params.add(new BasicNameValuePair("ROLE_ID", ACU.MySP.getFromSP(context, ACU.MySP.ROLE_ID, "")));
        params.add(new BasicNameValuePair("LOCATION_ID", ACU.MySP.getFromSP(context, ACU.MySP.LOCATION_ID, "")));

        String url = Constants.SERVER_URL + Constants.REGISTER;
        uploadData(url, params, "post", Constants.REGISTER, "Y");
    }

    public void uploadData(String url, List<NameValuePair> params, String method, String function, String isProgressbar) {
        if (Connectivity.isConnectedFast(context)) {
            new MyAsyncTask(this, context, method, url, null, params, function, isProgressbar).execute();
        } else {
            LTU.TEL(context, MU.NO_INERTNET_CONNECTION, MU.NO_INERTNET_CONNECTION);
        }
    }

    String flag = "";

    private void setListAdapter(JSONArray result) throws JSONException {
        flag = "active";
        if (result != null && result.length() > 0) {
            DocketListAdapter adapter = new DocketListAdapter(this, result, flag);
            lvDocketList.setAdapter(adapter);
        } else {
            lvDocketList.setVisibility(View.GONE);
        }
    }

    private void setRecentDocketListAdapter(JSONArray result) {
        flag = "recent";
        if (result != null && result.length() > 0) {
            DocketListAdapter adapter = new DocketListAdapter(this, result, flag);
            lvRcentDocketList.setAdapter(adapter);
        } else {
            lvRcentDocketList.setVisibility(View.GONE);
        }
    }

    @Override
    public void processFinish(String result, String functName) {
        try {
            if (result != null && !result.equals("fail")) {

                JSONObject object = new JSONObject(result);

                String sDocketListResult = object.getString("worklist_data_to_display");
                if (!sDocketListResult.equals("false"))
                {
                    JSONArray array = new JSONArray();
                    array = new JSONArray(sDocketListResult);
                    setListAdapter(array);
                }
                String sDocketRecentResult = object.getString("recent_data_to_display");
                if (!sDocketRecentResult.equals("false"))
                {
                    JSONArray array = new JSONArray();
                    array = new JSONArray(sDocketRecentResult);
                    setRecentDocketListAdapter(array);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
