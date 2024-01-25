package com.example.admin.bericapdesign2.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Class Name       :  <b>ACU.java<b/>
 * Purpose          :  ACU is class App constant Utility.
 * Developed By     :  <b>@Raghu_android</b>
 * Created Date     :  <b>24.09.2015</b>
 * <p/>
 * Modified Reason  :  <b></b>
 * Modified By      :  <b>@Raghu_android</b>
 * Modified Date    :  <b></b>
 * <p/>
 **/
public class ACU {
    public static class MSG {
        public static final String NO_INTERNET = "No Internet access.";
    }

    public static class MySP {
        public static String TOKEN = "token";// TODO: 12/4/2015 0- not login, 1- login, 2-guest user
        public static String DOWNLOAD_DATE = "download_date";// TODO: 12/4/2015 true-     false- public static String getSPString(Context context, String tag, String defltValue) {
        public static String LOGIN_STATUS = "login_status";// TODO: 12/4/2015 0- not login, 1- login, 2-guest user
        public static String USER_ID = "user_id";// TODO: 12/4/2015 0- not login, 1- login, 2-guest user
        public static String USER_NAME = "use_name";// TODO: 12/4/2015 0- not login, 1- login, 2-guest user
        public static String ROLE_ID = "roll_id";// TODO: 12/4/2015 0- not login, 1- login, 2-guest user
        public static String LOCATION_ID = "location_id";// TODO: 12/4/2015 0- not login, 1- login, 2-guest user
        public static String APPLICATION_ID = "application_id";// TODO: 12/4/2015 0- not login, 1- login, 2-guest user
        public static String CREATED_TIME = "created_time";// TODO: 12/4/2015 0- not login, 1- login, 2-guest user
        public static String UPDATED_TIME = "updated_time";// TODO: 12/4/2015 0- not login, 1- login, 2-guest user


        public static void saveSP(Context mContext, String key, String data) {
            final String PREFS_NAME = "SettingDetails";
            final SharedPreferences SpyAppData = mContext.getSharedPreferences(
                    PREFS_NAME, 0);
            SharedPreferences.Editor editor = SpyAppData.edit();
            editor.putString(key, data);
            editor.commit();
        }

        public static void setPreImage(Context mContext, String key, String data) {
            final String PREFS_NAME = "preuploadimage";
            final SharedPreferences preuploadimage = mContext.getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = preuploadimage.edit();
            editor.putString(key, data);
            editor.commit();
        }

        public static String getPreImage(Context mContext, String key,String data) {
            final String PREFS_NAME = "preuploadimage";
            final SharedPreferences preuploadimage = mContext.getSharedPreferences(
                    PREFS_NAME, 0);
            final String preData = preuploadimage.getString(key,data).trim();
            return preData;
        }

        /*For setting postload image*/
        public static void setPostImage(Context mContext, String key, String data) {
            final String PREFS_NAME = "postuploadimage";
            final SharedPreferences postuploadimage = mContext.getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = postuploadimage.edit();
            editor.putString(key, data);
            editor.commit();
        }

        /*For setting docket no*/
        public static void setDeliveryDocketNumber(Context mContext, String key, String data) {
            final String PREFS_NAME = "delivery_docket_no";
            final SharedPreferences delivery_docket_no = mContext.getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = delivery_docket_no.edit();
            editor.putString(key, data);
            editor.commit();
        }

        public static String getDeliveryDocketNumber(Context mContext, String key,String data) {
            final String PREFS_NAME = "delivery_docket_no";
            final SharedPreferences delivery_docket_no = mContext.getSharedPreferences(
                    PREFS_NAME, 0);
            final String postData = delivery_docket_no.getString(key,data).trim();
            return postData;
        }

        public static String getPostImage(Context mContext, String key,String data) {
            final String PREFS_NAME = "postuploadimage";
            final SharedPreferences postuploadimage = mContext.getSharedPreferences(
                    PREFS_NAME, 0);
            final String postData = postuploadimage.getString(key,data).trim();
            return postData;
        }

         /*For setting Seal1 image*/
         public static void setSeal1Image(Context mContext, String key, String data) {
             final String PREFS_NAME = "seal1uploadimage";
             final SharedPreferences seal1uploadimage = mContext.getSharedPreferences(PREFS_NAME, 0);
             SharedPreferences.Editor editor = seal1uploadimage.edit();
             editor.putString(key, data);
             editor.commit();
         }

        public static String getSeal1Image(Context mContext, String key,String data) {
            final String PREFS_NAME = "seal1uploadimage";
            final SharedPreferences seal1uploadimage = mContext.getSharedPreferences(
                    PREFS_NAME, 0);
            final String postData = seal1uploadimage.getString(key,data).trim();
            return postData;
        }

        /*For setting Seal2 image*/
        public static void setSeal2Image(Context mContext, String key, String data) {
            final String PREFS_NAME = "seal2uploadimage";
            final SharedPreferences seal2uploadimage = mContext.getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = seal2uploadimage.edit();
            editor.putString(key, data);
            editor.commit();
        }

        public static String getSeal2Image(Context mContext, String key,String data) {
            final String PREFS_NAME = "seal2uploadimage";
            final SharedPreferences seal2uploadimage = mContext.getSharedPreferences(
                    PREFS_NAME, 0);
            final String postData = seal2uploadimage.getString(key,data).trim();
            return postData;
        }

        /**
         * This method gives the saved data from local SharedPreferences File by
         * just Send context and the key name of the data
         */
        public static String getFromSP(Context mContext, String key, String dvalu) {
            final String PREFS_NAME = "SettingDetails";
            final SharedPreferences ToolsAppData = mContext.getSharedPreferences(
                    PREFS_NAME, 0);
            final String preData = ToolsAppData.getString(key, dvalu).trim();
            return preData;

        }

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
