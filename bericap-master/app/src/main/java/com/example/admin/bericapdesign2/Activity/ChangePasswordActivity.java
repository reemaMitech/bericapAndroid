package com.example.admin.bericapdesign2.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.bericapdesign2.AsyncTask.AsyncResponse;
import com.example.admin.bericapdesign2.AsyncTask.MyAsyncTask;
import com.example.admin.bericapdesign2.R;
import com.example.admin.bericapdesign2.Services.AccessWebServices;
import com.example.admin.bericapdesign2.Util.ACU;
import com.example.admin.bericapdesign2.Util.BU;
import com.example.admin.bericapdesign2.Util.Connectivity;
import com.example.admin.bericapdesign2.Util.Constants;
import com.example.admin.bericapdesign2.Util.LTU;
import com.example.admin.bericapdesign2.Util.MU;
import com.example.admin.bericapdesign2.Util.VU;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener, AsyncResponse {
    private static int SPLASH_TIME_OUT = 3000;
    String TAG = "ChangePasswordActivity";
    Toolbar toolbar;
    Context context;
    Button btn_submit;
    EditText old_password, new_password, confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        context = ChangePasswordActivity.this;
        intializeUi();
        setToolBar();
    }

    @SuppressLint("WrongViewCast")
    private void intializeUi() {
        //for login
        old_password = (EditText) findViewById(R.id.changepassword_edt_oldpassword);
        new_password = (EditText) findViewById(R.id.changepassword_edt_newpassword);
        confirm_password = (EditText) findViewById(R.id.changepassword_edt_confirmpassword);

        btn_submit = (Button) findViewById(R.id.login_btn_submit);
        btn_submit.setOnClickListener(this);

    }

    public void setToolBar() {
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn_submit:

                if (validateSignInData()) {
                    if (BU.isConnectingToInternet(context)) {
                        postPasswordInfo();
                    }
                }
                break;
            case R.id.login_btn_cancel:
                //  clearField();
                break;
        }

    }


    private boolean validateSignInData() {
        if (VU.isEmpty(old_password)) {
            old_password.requestFocus();
            old_password.setError(getResources().getString(R.string.oldpassword_empty_error_text));
            return false;
        } else if (VU.isEmpty(new_password)) {
            new_password.requestFocus();
            new_password.setError(getResources().getString(R.string.Newpassword_empty_error_text));
            return false;
        } else if (VU.isEmpty(confirm_password)) {
            confirm_password.requestFocus();
            confirm_password.setError(getResources().getString(R.string.confirmpassword_empty_error_text));
            return false;
        } else if (VU.isConfirPassWord(new_password, confirm_password)) {
            confirm_password.requestFocus();
            confirm_password.setError(getResources().getString(R.string.confirm_change_password_compare_error_text));
            return false;
        }
        return true;
    }


    private void postPasswordInfo() {

        final List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("SOURCE", "mobile"));
        params.add(new BasicNameValuePair("USER_NAME", ACU.MySP.getFromSP(context, ACU.MySP.USER_NAME, "")));
        params.add(new BasicNameValuePair("OLD_PASSWORD", old_password.getText().toString()));
        params.add(new BasicNameValuePair("CHANGE_PASSWORD", new_password.getText().toString()));
        params.add(new BasicNameValuePair("CONFIRM_PASSWORD", confirm_password.getText().toString()));

        String url = Constants.SERVER_URL + Constants.CHANGE_PASSWORD;
        uploadData(url, params, "post", Constants.CHANGE_PASSWORD, "Y");
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
        try {
            if (result != null) {
                JSONArray array = null;

                array = new JSONArray(result);


                if (array.getJSONObject(0).has("message")) {
                    if (array.getJSONObject(0).getString("message").equals("Your Password changed successfully !!")) {
                        Toast.makeText(ChangePasswordActivity.this, "Your Password changed successfully ", Toast.LENGTH_SHORT).show();
                        old_password.setText("");
                        new_password.setText("");
                        confirm_password.setText("");
                        Intent pass = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                        finish();
                        startActivity(pass);
                    } else {
                        Toast.makeText(ChangePasswordActivity.this, "Error in changing password", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

