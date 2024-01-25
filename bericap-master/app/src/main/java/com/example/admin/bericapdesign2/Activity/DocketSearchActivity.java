package com.example.admin.bericapdesign2.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.admin.bericapdesign2.Adapter.DocketSearchListAdapter;
import com.example.admin.bericapdesign2.AsyncTask.AsyncResponse;
import com.example.admin.bericapdesign2.AsyncTask.MyAsyncTask;
import com.example.admin.bericapdesign2.R;
import com.example.admin.bericapdesign2.Util.ACU;
import com.example.admin.bericapdesign2.Util.Connectivity;
import com.example.admin.bericapdesign2.Util.Constants;
import com.example.admin.bericapdesign2.Util.LTU;
import com.example.admin.bericapdesign2.Util.MU;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DocketSearchActivity extends AppCompatActivity implements AsyncResponse {
    String TAG = "DocketSearchActivity";
    Toolbar toolbar;
    Context context;
    Button btn_search;
    EditText searchText;
    ListView lvSearchDocketList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docket_search);
        context = DocketSearchActivity.this;
        setToolBar();

        searchText = (EditText) findViewById(R.id.activity_docket_search_edt_sname);
        btn_search = (Button) findViewById(R.id.activity_docket_search_btn_search);
        lvSearchDocketList = (ListView) findViewById(R.id.activity_docket_search_ls_item);
        btn_search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                searchDocket(searchText.getText().toString().trim());
            }
        });
    }


    public void setToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        android.support.v7.app.ActionBar bar = getSupportActionBar();          //For setting tool bar
        bar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {                              //For setting back button on tool bar
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void searchDocket(String sSearch) {

        final List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("SOURCE", "mobile"));
        params.add(new BasicNameValuePair("APPLICATION_ID", ACU.MySP.getFromSP(context, ACU.MySP.APPLICATION_ID, "")));
        params.add(new BasicNameValuePair("ROLE_ID", ACU.MySP.getFromSP(context, ACU.MySP.ROLE_ID, "")));
        params.add(new BasicNameValuePair("LOCATION_ID", ACU.MySP.getFromSP(context, ACU.MySP.LOCATION_ID, "")));

        params.add(new BasicNameValuePair("SEARCH", sSearch));


        String url = Constants.SERVER_URL + Constants.SEARCH;
        uploadData(url, params, "post", Constants.SEARCH, "Y");
    }

    public void uploadData(String url, List<NameValuePair> params, String method, String function, String isProgressbar) {
        if (Connectivity.isConnectedFast(context)) {
            new MyAsyncTask(this, context, method, url, null, params, function, isProgressbar).execute();
        } else {
            LTU.TEL(context, MU.NO_INERTNET_CONNECTION, MU.NO_INERTNET_CONNECTION);
        }
    }

    @Override
    public void processFinish(String result, String functName) {
        LTU.LE("Result", " Search result :->  : " + result);

        try {
            if (result != null && !result.equals("fail")) {

                JSONObject object = new JSONObject(result);
                String sDocketListResult = object.getString("worklist_data_to_display");

                JSONArray array = new JSONArray();
                array = new JSONArray(sDocketListResult);

                JSONArray activeList = new JSONArray();
                for (int i = 0; i < array.length(); i++) {
                    try {
                        if (array.getJSONObject(i).getString("DOCK_STATUS").equals("INSPECTED")) {
                            activeList.put(array.get(i));
                        } else {
                            activeList.put(array.get(i));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setListAdapter(activeList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setListAdapter(JSONArray result) throws JSONException {
        if (result != null && result.length() > 0) {
            DocketSearchListAdapter adapter = new DocketSearchListAdapter(this, result);
            lvSearchDocketList.setAdapter(adapter);
        } else {
            lvSearchDocketList.setVisibility(View.GONE);
        }
    }
}
