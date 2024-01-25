package com.example.admin.bericapdesign2.Services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.admin.bericapdesign2.Util.LTU;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

public class AccessWebServices {
    // TODO: 6/3/2016 method name
    static int ConnectionTimeout = 60000;
    static int SoTimeout = 60000;

    private static String TAG = "";
    private final SharedPreferences sharedPreferences;
    private Context context;
    private static int TIME_OUT_10 = 10000;
    private static int TIME_OUT_15 = 15000;
    private static String result;
    private static InputStream is;
    private static StringBuilder sb;


    public AccessWebServices(Context con) {
        this.context = con;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    //TODO Upload Download methods using JSON
    public static String uploadJsonData(JSONArray array, String url) {
        // TODO Auto-generated method stub
        //satish send
        try {
            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, TIME_OUT_15);
            HttpConnectionParams.setSoTimeout(httpParams, TIME_OUT_15);

            HttpClient client = new DefaultHttpClient(httpParams);
            HttpPost request = new HttpPost(url+ "?");
            request.setEntity(new ByteArrayEntity(array.toString().getBytes("UTF8")));
            request.setHeader("json", array.toString());


            HttpResponse response = client.execute(request);

            //responce from server
            HttpEntity entity = response.getEntity();
            // If the response does not enclose an entity, there is no need

            if (entity != null) {
                is = entity.getContent();
                //Log.i("Read from server", ""+result);
            }
        } catch (ConnectTimeoutException ex) {
            //downtime_flag=1;
        } catch (UnsupportedEncodingException uee) {
            Log.d("Exceptions", "UnsupportedEncodingException");
            uee.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            sb = new StringBuilder();
            sb.append(reader.readLine());
            String line = "0";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            result = sb.toString();
            Log.v("i am here::", "" + result);
        } catch (Exception e) {
            LTU.LE(TAG, "Error converting result " + e.toString());
            return "fail";
        }
        return result;
    }
    public static String getData(String url) {

        String result = "";
        try {

            HostnameVerifier hostnameVerifier = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

            DefaultHttpClient client = new DefaultHttpClient();

            SchemeRegistry registry = new SchemeRegistry();
            SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
            socketFactory.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
            registry.register(new Scheme("https", socketFactory, 443));
            SingleClientConnManager mgr = new SingleClientConnManager(client.getParams(), registry);
            DefaultHttpClient httpClient = new DefaultHttpClient(mgr, client.getParams());

            // Set verifier
            HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);

            HttpPost httpPost = new HttpPost(url);
            //httpPost.setEntity(new ByteArrayEntity(array.toString().getBytes("UTF8")));
            // httpPost.setHeader("json", array.toString());
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            // If the response does not enclose an entity, there is no need
            if (entity != null) {
                is = entity.getContent();
            }

        } catch (ConnectTimeoutException ex) {

        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            sb = new StringBuilder();
            sb.append(reader.readLine() + "\n");
            String line = "0";
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();

        } catch (Exception e) {
            return "fail";
        }
        return result;
    }
    public static String UploadJsonData1(String url, JSONArray array) {
        HttpEntity httpEntity = null;

        ArrayList list = new ArrayList();
        try {
            Log.e("URL :", "" + url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("data", array.toString()));
            HttpParams httpParams = new BasicHttpParams();
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            String entityResponse = EntityUtils.toString(httpEntity);
            return entityResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
    public static String postData(String sUrl,List<NameValuePair> params) {
        HttpEntity httpEntity = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(sUrl);
            Log.e("Url:->", sUrl);
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            String entityResponse = EntityUtils.toString(httpEntity);

            //TODO MARATHI TEXT UPlOAD

            LTU.LE(TAG, "Entity Response  : " + entityResponse);
            return entityResponse;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            Log.e("error", "" + e);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("error1", "" + e);
        }
        return "";
    }
}