package com.example.admin.bericapdesign2.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.bericapdesign2.AsyncTask.AsyncResponse;
import com.example.admin.bericapdesign2.AsyncTask.MyAsyncTask;
import com.example.admin.bericapdesign2.CustomClasses.CustomButton;
import com.example.admin.bericapdesign2.R;
import com.example.admin.bericapdesign2.Supply.Activities.SupllyrTabActivity;
import com.example.admin.bericapdesign2.Util.ACU;
import com.example.admin.bericapdesign2.Util.BU;
import com.example.admin.bericapdesign2.Util.Connectivity;
import com.example.admin.bericapdesign2.Util.Constants;
import com.example.admin.bericapdesign2.Util.LTU;
import com.example.admin.bericapdesign2.Util.MU;
import com.example.admin.bericapdesign2.Util.VU;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, AsyncResponse {
    String TAG = "LoginActivity";
    Context context;
    Button btnCancel;
    CustomButton btnLogin;
    EditText edtEmail, edtPassword;
    private String token = ""; //TODO variables of GCM
    private static final String[] TOPICS = {"global"};//TODO variables of GCM

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.context = LoginActivity.this;

        initializeGCM();
        intializeUi();
    }

    //TODO initialize of GCM
    private void initializeGCM() {
        token = ACU.MySP.getFromSP(context, ACU.MySP.TOKEN, "");

        LTU.LE("ClientLogin ", " GCM token " + token);

        if (token.equals("")) {
            if (BU.isConnectingToInternet(context)) {
                new GetGCMTokenTask().execute();
            }
        }
    }

    private void intializeUi() {
        edtEmail = (EditText) findViewById(R.id.login_edt_emailid);
        edtPassword = (EditText) findViewById(R.id.login_edt_password);
        btnLogin = (CustomButton) findViewById(R.id.login_btn_login);
        btnLogin.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.login_btn_cancel);
        btnCancel.setOnClickListener(this);
    }

    private void clearField() {
        edtEmail.getText().clear();
        edtPassword.getText().clear();
        edtEmail.setError(null);
        edtPassword.setError(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn_login:
                if (validateSignInData()) {
                    if (BU.isConnectingToInternet(context)) {
                        signUpLogin();
                    }
                }
                break;
            case R.id.login_btn_cancel:
                break;
        }
    }


    //TODO Method of Custom CustomSignIn
    private boolean validateSignInData() {
        if (VU.isEmpty(edtEmail)) {
            edtEmail.requestFocus();
            edtEmail.setError(getResources().getString(R.string.emailid_empty_error_text));
            return false;
        } else if (VU.isEmpty(edtPassword)) {
            edtPassword.requestFocus();
            edtPassword.setError(getResources().getString(R.string.password_empty_error_text));
            return false;
        }
        return true;
    }

    private void signUpLogin() {

        final List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("SOURCE", "mobile"));
        params.add(new BasicNameValuePair("USERNAME", edtEmail.getText().toString()));
        params.add(new BasicNameValuePair("PASSWORD", edtPassword.getText().toString()));
        params.add(new BasicNameValuePair("GCM_ID", ACU.MySP.getFromSP(context, ACU.MySP.TOKEN, "")));


        String url = Constants.SERVER_URL + Constants.SIGN_UP;
        uploadData(url, params, "post", Constants.SIGN_UP, "Y");
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
            if (result != null && !result.equals("fail")) {
                JSONArray array = new JSONArray(result);
                LTU.LE("Login:->", "response " + result);
                if (array.getJSONObject(0).has("USER_ID")) {
                    ACU.MySP.saveSP(context, ACU.MySP.USER_ID, array.getJSONObject(0).getString("USER_ID"));
                    ACU.MySP.saveSP(context, ACU.MySP.ROLE_ID, array.getJSONObject(0).getString("ROLE_ID"));
                    ACU.MySP.saveSP(context, ACU.MySP.LOCATION_ID, array.getJSONObject(0).getString("LOCATION_ID"));
                    ACU.MySP.saveSP(context, ACU.MySP.USER_NAME, array.getJSONObject(0).getString("USER_NAME"));
                    ACU.MySP.saveSP(context, ACU.MySP.APPLICATION_ID, array.getJSONObject(0).getString("APPLICATION_ID"));

                    LTU.LE("App id", " " + array.getJSONObject(0).getString("APPLICATION_ID"));
                    ACU.MySP.setSPBoolean(context, ACU.MySP.LOGIN_STATUS, true);
                    if (ACU.MySP.getFromSP(context, ACU.MySP.ROLE_ID, "").equals("6")) {
                        Intent intent = new Intent(context, SupllyrTabActivity.class);
                        finish();
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(context, MainActivity.class);
                        finish();
                        startActivity(intent);
                    }

                } else {
                    clearField();
                    LTU.TIS(context, TAG, getResources().getString(R.string.login_not_valid_text));
                }
            } else {
                clearField();
                LTU.TIS(context, TAG, getResources().getString(R.string.login_not_valid_text));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //TODO Async task for custom SignUp
    class GetGCMTokenTask extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            LTU.LI(TAG, " In side onPreExecute :");
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Please Wait");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            LTU.LI(TAG, " In side doInBackground :");
            return GcmRegistration();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            LTU.LI(TAG, " In side onPostExecute :");
            LTU.LI(TAG, " result :" + result);
            pDialog.dismiss();
            if (result == null) {
             //   LTU.TIS(context, TAG, MU.CONFIGURE);
            } else {
               // LTU.TIS(context, TAG, MU.CONFIGURE);
            }
        }

        private String GcmRegistration() {
            try {
                token = InstanceID.getInstance(context).getToken(BU.GCM_PROJECT_ID, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                subscribeTopics(token);
                ACU.MySP.saveSP(context, ACU.MySP.TOKEN, token);

                LTU.LE("GcmRegistration ", " GCM token " + ACU.MySP.getFromSP(context, ACU.MySP.TOKEN, ""));

            } catch (Exception e) {
                LTU.LE(TAG, "Exception " + e);
            }
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("registrationComplete"));
            return token;
        }

        private void subscribeTopics(String token) throws IOException {
            for (String topic : TOPICS) {
                GcmPubSub pubSub = GcmPubSub.getInstance(context);
                pubSub.subscribe(token, "/topics/" + topic, null);
            }
        }
    }
}
