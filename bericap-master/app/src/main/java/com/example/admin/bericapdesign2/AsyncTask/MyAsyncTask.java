package com.example.admin.bericapdesign2.AsyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.admin.bericapdesign2.Services.AccessWebServices;
import com.example.admin.bericapdesign2.Util.MU;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class MyAsyncTask extends AsyncTask<String, Void, String> {
    ProgressDialog progressDialog;
    String TAG = "MyAsyncTask";
    Context context;
    String sMethod = "", sUrl = "", funct = "", sFlag = "";
    JSONArray array;
    JSONObject object;
    List<NameValuePair> sParams;
    public AsyncResponse delegate = null;

    public MyAsyncTask(AsyncResponse delegate, Context context, String method, String sUrl, JSONArray array, List<NameValuePair> params, String funct, String y) {
        this.delegate = delegate;
        this.context = context;
        this.sMethod = method;
        this.sUrl = sUrl;
        this.array = array;
        this.object = object;
        this.sParams = params;
        this.funct = funct;
        this.sFlag = y;
    }

    //Am using it in an AsyncTask. So in  my onPreExecute, I do this:

    @Override
    protected void onPostExecute(String result) {
        if (sFlag.equals("Y")) {
            progressDialog.dismiss();
        }

        delegate.processFinish(result, funct);
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub

        super.onPreExecute();
        if (sFlag.equals("Y")) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(MU.WAIT_DOWNLOAD);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }


    }

    @Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub

        if (sMethod.equals("get")) {
            return AccessWebServices.getData(sUrl);
        } else if (sMethod.equals("post")) {
            return AccessWebServices.postData(sUrl,sParams);
        } else if (sMethod.equals("array")) {
            return AccessWebServices.uploadJsonData(array, sUrl);
        } else if (sMethod.equals("upload")) {
            return AccessWebServices.UploadJsonData1(sUrl, array);
        }
        return "fail";
    }
}