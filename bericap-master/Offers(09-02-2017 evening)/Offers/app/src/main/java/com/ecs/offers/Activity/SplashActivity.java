package com.ecs.offers.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ecs.offers.R;
import com.ecs.offers.WebServicesUtil.AccessWebServices;
import com.ecs.offers.database.DataBaseConstants;
import com.ecs.offers.database.DataBaseHelper;
import com.ecs.offers.util.BU;
import com.ecs.offers.util.Constants;
import com.ecs.offers.util.LTU;
import com.ecs.offers.util.MU;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {
    Context context;
    DataBaseHelper dh;
    String like_fav_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = SplashActivity.this;
        dh = new DataBaseHelper(context);
        init();
        /*Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                    Intent loginIntent = new Intent(context, DashBoardActivity.class);
                    startActivity(loginIntent);
                }
            }
        };
        timerThread.start();*/
    }

    private void init() {
        if (BU.isConnectingToInternet(context)) {
            new DownloadData().execute();
        } else {
            LTU.TOAST_L(context, MU.NO_INTERNET_CONNECTION_TRY_AGAIN);
        }
    }

    class DownloadData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            JSONArray array = new JSONArray();
            JSONObject json = new JSONObject();
            try {
                json.put("","");
                array.put(json);

                like_fav_result = AccessWebServices.postData(Constants.METHOD_LIKE_FAV, array);
                return AccessWebServices.postData(Constants.METHOD_DASHBOARD, array);

            } catch (JSONException e) {

                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.e("Result",""+result);
            if(result != null){

                try {

                    JSONObject jsonObject = new JSONObject(result.trim());
                    JSONArray jsonArray = new JSONArray();

                    jsonArray = jsonObject.getJSONArray("Dashboard Details");
                    if(jsonArray.length()>0)
                    dh.insertDownloadedData(DataBaseConstants.TableNames.TBL_DASHBOARD, jsonArray.toString());

                    jsonArray = jsonObject.getJSONArray("Category Details");
                    if(jsonArray.length()>0)
                    dh.insertDownloadedData(DataBaseConstants.TableNames.TBL_CATEGORY_DETAILS, jsonArray.toString());

                    jsonArray = jsonObject.getJSONArray("Subcategory Details");
                    if(jsonArray.length()>0)
                        dh.insertDownloadedData(DataBaseConstants.TableNames.TBL_SUB_CATEGORY_DETAILS, jsonArray.toString());

                    jsonArray = jsonObject.getJSONArray("Card Details");
                    if(jsonArray.length()>0)
                    dh.insertDownloadedData(DataBaseConstants.TableNames.TBL_CARD_DETAILS, jsonArray.toString());

                    jsonArray = jsonObject.getJSONArray("Trending Details");
                    if(jsonArray.length()>0)
                    dh.insertDownloadedData(DataBaseConstants.TableNames.TBL_TRENDING_OFFERS, jsonArray.toString());

                    jsonArray = jsonObject.getJSONArray("Cardtype Details");
                    if(jsonArray.length()>0)
                        dh.insertDownloadedData(DataBaseConstants.TableNames.TBL_CARD_TYPE, jsonArray.toString());

                    jsonArray = jsonObject.getJSONArray("Provider Details");
                    if(jsonArray.length()>0)
                        dh.insertDownloadedData(DataBaseConstants.TableNames.TBL_PAYMENT_OPTION_PROVIDER, jsonArray.toString());

                    jsonArray = jsonObject.getJSONArray("Offer Details");
                    if(jsonArray.length()>0)
                        dh.insertDownloadedData(DataBaseConstants.TableNames.TBL_OFFER_DETAILS, jsonArray.toString());

                    Intent loginIntent = new Intent(context, DashBoardActivity.class);
                    startActivity(loginIntent);

                    LTU.TOAST_L(context, MU.SUCCESS_DOWNLOAD);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            Log.e("like_fav_result",""+like_fav_result);
            if(like_fav_result != null){

                try {

                    JSONObject jsonObject = null;
                    jsonObject = new JSONObject(like_fav_result);

                    JSONArray jsonArray = new JSONArray();
                    jsonArray = jsonObject.getJSONArray("Icon Details");

                    if(jsonArray.length()>0)
                        dh.insertDownloadedData(DataBaseConstants.TableNames.TBL_LIKE_FAVORITE_SHARE, jsonArray.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }

        }
    }


}
