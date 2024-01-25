package com.ecs.offers.WebServicesUtil;

/*
Class Name      :  AccessWebServices.java 
Developed By    :  Nitin Malwadkar
Modified By     :  
Created Date    :  03-02-2017
Modified Date   :
Purpose         :  General class containing methods to access dotnet webservices for download/upload 
Modified Reason :  Added the service method to download the vehicle regitration service, changed daily time check method as per the modified column fields. Changed the indentation and added the comments wherever required.
 */
//import java.util.StringTokenizer;

import android.content.Context;
import android.database.Cursor;
import android.provider.SyncStateContract;
import android.util.Log;

import com.ecs.offers.database.DataBaseHelper;
import com.ecs.offers.util.Constants;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class AccessWebServices {

    private static int TIME_OUT_15 = 15000;
    private Context context;
    private DataOutputStream dos;
    static boolean isUploaded = false;
    static boolean isAvailable = false;
    static boolean isSuccess = false;
    static String str_success = "";
    static Cursor c_upload;
    static int totalRecord = 0;
    static JSONArray array;
    private static String str_colname;


    public AccessWebServices(Context con) {
        this.context = con;
    }

    public static String postData(String method ,JSONArray array) {
        HttpEntity httpEntity = null;

        ArrayList list = new ArrayList();
        try {
            Log.e("URL :",""+Constants.SERVER_URL+method);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("data", array.toString()));
            HttpParams httpParams = new BasicHttpParams();
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(Constants.SERVER_URL+method);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            String entityResponse = EntityUtils.toString(httpEntity);

            return entityResponse;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String UploadDataAll(String table, DataBaseHelper dh) {

        totalRecord = 0;
        isUploaded = false;
        isAvailable = false;
        isSuccess = false;
        array = new JSONArray();

        c_upload = dh.getDataToUpload(table);
        totalRecord = c_upload.getCount();
        if (totalRecord != 0) {
            if (c_upload.moveToFirst()) {
                for (int i = 0; i < totalRecord; i++) {
                    try {
                        JSONObject obj = new JSONObject();
                        int count = c_upload.getColumnCount();
                        for (int k = 0; k < count - 1; k++) {
                            str_colname = c_upload.getColumnName(k);
                            if (!str_colname.equals("position") && !str_colname.equals("is_uploaded")) {
                                obj.put("" + str_colname, c_upload.getString(c_upload.getColumnIndex("" + c_upload.getColumnName(k))));
                            }
                        }
                        array.put(obj);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    c_upload.moveToNext();
                }
            } else {
                c_upload.close();
            }
            Log.v("SendArray::", "" + array);
            str_success = postData(Constants.UPLOAD_METHOD+table, array);
            Log.e("StrSuccess::", "" + str_success);
            if (str_success.equals("fail")) {
                str_success = "fail";
                return str_success;
            } else {
                JSONArray jArray = null;
                try {
                    jArray = new JSONArray(str_success);

                    int count = 0;
                    JSONObject json_data;
                    String id, phpid;
                    int flag = 0;
                    for (int i = 0; i < jArray.length(); i++) {
                        try {
                            json_data = jArray.getJSONObject(i);
                            id = json_data.getString("id");
                            phpid = json_data.getString("phpid");

                            if (phpid.equals("0") || phpid.equals("") || phpid.equals(null))
                                flag = 1;
                            else
                                count += dh.updateStatusAll(table, id.toString().trim(), phpid.toString().trim());

                        } catch (Exception e) {
                            flag = 1;
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                str_success = "success";
                return str_success;
            }
        }//if not data to upoad
        else {
            str_success = "nodata";
            return str_success;
        }

    }

}
