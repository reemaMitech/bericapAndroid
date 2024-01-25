package com.example.admin.bericapdesign2.Util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

//import org.apache.http.conn.util.InetAddressUtils;

public class BU {
    private static final String TAG = "BU";
    public static final String GCM_PROJECT_ID = "653329561144"; //TODO : ECS Dev ID

    public static boolean isConnectingToInternet(Context appContext) {
        // Method to check internet connection
        ConnectivityManager connectivity = (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        LTU.TIS(appContext, TAG, ACU.MSG.NO_INTERNET);
        try {

        } catch (Exception e) {
            LTU.LE(TAG, "Exception " + e.toString());
        }

        return false;
    }

    public static String getIMEI(Context context) {
        String identifier = null;
        TelephonyManager tm;
        try {
            tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null) {
                identifier = tm.getDeviceId();
            }
            if (identifier == null || identifier.length() == 0) {
                identifier = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
            }
        } catch (Exception e) {
            return "Not Available";
        }
        return identifier;
    }

    public static String getLocalIpAddress() {
        // AndroidManifest.xml permissions
        // <uses-permission android:name="android.permission.INTERNET" />
        // <uses-permission
        // android:name="android.permission.ACCESS_NETWORK_STATE" />
        // TODO get IP Address
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ip = Formatter.formatIpAddress(inetAddress
                                .hashCode());
                        // Log.i(TAG, "***** IP="+ ip);
                        LTU.LI("BU getLocalIpAddress", "IP Address :" + ip);
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
            LTU.LE("BU getLocalIpAddress", ex.toString());
        }
        return "NA";
    }

    public static String getIPAddress(boolean useIPv4) {

//        try {
//            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
//            for (NetworkInterface intf : interfaces) {
//                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
//                for (InetAddress addr : addrs) {
//                    if (!addr.isLoopbackAddress()) {
//                        String sAddr = addr.getHostAddress().toUpperCase();
//                        boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
//                        if (useIPv4) {
//                            if (isIPv4)
//                                return sAddr;
//                        } else {
//                            if (!isIPv4) {
//                                int delim = sAddr.indexOf('%'); // drop ip6 port suffix
//                                return delim < 0 ? sAddr : sAddr.substring(0, delim);
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (Exception ex) {
//        } // for now eat exceptions
        return "";
    }

    public static void hideKeyboard(Activity activity) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void SetAdapter(Context context, Spinner spinner, ArrayList<String> arrayList) {
        // TODO Auto-generated method stub
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        //spinner.setOnItemSelectedListener(context);
    }

    public static void SetToAutocompleteTextView(Context context, AutoCompleteTextView auto_text_view, ArrayList<String> venderArray) {
        // TODO Auto-generated method stub
//        final ArrayAdapter<String> vender_adapter = new ArrayAdapter<String>(
//                context, R.layout.autocomplete_text_view,
//                venderArray);
//
//        auto_text_view.setAdapter(vender_adapter);
//        auto_text_view.setTextColor(Color.BLACK);
//        auto_text_view.setThreshold(1);
    }

    public void get(Context context) {
//        TelephonyManager tm = (TelephonyManager)getSystemService(context.TELEPHONY_SERVICE);
//        String countryCode = tm.getNetworkCountryIso();

    }

}
