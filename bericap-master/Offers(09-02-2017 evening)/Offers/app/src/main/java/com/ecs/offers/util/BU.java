package com.ecs.offers.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Base64;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

public class BU {

    public static boolean isConnectingToInternet(Context appContext) {
        // Method to check internet connection
        ConnectivityManager connectivity = (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    public static String getIMEI(Context con) {
        String identifier = null;
        TelephonyManager tm;
        try {
            tm = (TelephonyManager) con
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null)
                identifier = tm.getDeviceId();
            if (identifier == null || identifier.length() == 0)
                identifier = Secure.getString(con.getContentResolver(),
                        Secure.ANDROID_ID);
        } catch (Exception e) {
            return "Not Available";
        }
        return identifier;
    }


    public static void SetAdapter(Context context, Spinner spinner, ArrayList<String> arrayList) {
        // TODO Auto-generated method stub
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        //spinner.setOnItemSelectedListener(context);
    }

    //TODO method to en-code data to base 64
    public static String encodeToBase64(Bitmap image) {
        System.gc();
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        return imageEncoded;
    }



    public static class MySP {


        public static String getSPString(Context context, String tag, String defltValue) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            return sp.getString(tag, defltValue);
       }

        public static Boolean setSPString(Context context, String tag, String value) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            try {
                sp.edit().putString(tag, value).apply();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        public static Boolean getSPBoolean(Context context, String tag, boolean defltValue) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            return sp.getBoolean(tag, defltValue);
        }

        public static Boolean setSPBoolean(Context context, String tag, boolean value) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            try {
                sp.edit().putBoolean(tag, value).apply();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

    }

}