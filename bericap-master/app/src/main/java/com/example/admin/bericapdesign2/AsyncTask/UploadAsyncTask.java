package com.example.admin.bericapdesign2.AsyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.admin.bericapdesign2.Services.MyHttpEntity;
import com.example.admin.bericapdesign2.Supply.Activities.SupllyrTabActivity;
import com.example.admin.bericapdesign2.Util.LTU;
import com.example.admin.bericapdesign2.Util.MU;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class UploadAsyncTask extends AsyncTask<Void, Integer, String> {
    //  public class UploadAsyncTask extends AsyncTask<Void, Integer, String> {

    HttpClient httpClient = new DefaultHttpClient();
    private Context context;
    private Exception exception;
    private ProgressDialog progressDialog;
    String flag = "", sURL = "";
    MultipartEntityBuilder multipartEntityBuilder;

    public UploadAsyncTask(Context context, String flag, String sURL, MultipartEntityBuilder multipartEntityBuilder) {
        this.context = context;
        this.flag = flag;
        this.sURL = sURL;
        this.multipartEntityBuilder = multipartEntityBuilder;
    }

    @Override
    protected String doInBackground(Void... params) {

        HttpResponse httpResponse = null;
        HttpEntity httpEntity = null;
        String responseString = null;

        try {
            String SERVER_PATH = sURL;
            Log.e("sURL:->", SERVER_PATH);

            HttpPost httpPost = new HttpPost(SERVER_PATH);

            // Add the file to be uploaded

            // Progress listener - updates task's progress
            MyHttpEntity.ProgressListener progressListener =
                    new MyHttpEntity.ProgressListener() {
                        @Override
                        public void transferred(float progress) {
                            publishProgress((int) progress);
                        }
                    };

            // POST
            httpPost.setEntity(new MyHttpEntity(multipartEntityBuilder.build(),
                    progressListener));


            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // Server response
                responseString = EntityUtils.toString(httpEntity);
                Log.e("responseString", responseString);
            } else {
                responseString = EntityUtils.toString(httpEntity);
                Log.e("responseStringElseee", responseString.trim());
                responseString = "Error occurred! Http Status Code: "
                        + statusCode;
            }
        } catch (UnsupportedEncodingException | ClientProtocolException e) {
            e.printStackTrace();
            Log.e("UPLOAD", e.getMessage());
            this.exception = e;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseString;
    }

    @Override
    protected void onPreExecute() {

        // Init and show dialog
        this.progressDialog = new ProgressDialog(this.context);
        this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
    }

    @Override
    protected void onPostExecute(String result) {

        // Close dialog
        this.progressDialog.dismiss();
        Log.e("Image_upload_result", ":->" + result.trim());
           /* Toast.makeText(context,
                    result, Toast.LENGTH_LONG).show();*/
        if (flag.trim().equals("Y")) {
            refreshFragment();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        // Update process
        this.progressDialog.setProgress((int) progress[0]);
    }

    public void refreshFragment() {
        LTU.TOAST_MSG(context, MU.DATA_ADD_SUCCESS);
        Intent intent = new Intent(context, SupllyrTabActivity.class);
        context.startActivity(intent);
    }
}

