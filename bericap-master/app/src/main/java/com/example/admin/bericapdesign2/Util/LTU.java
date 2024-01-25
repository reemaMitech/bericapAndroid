package com.example.admin.bericapdesign2.Util;
/**
 * Class Name       :  <b>LTU.java<b/>
 * Purpose          :  LTU is java class contain logs and toast which are used in though out the project.
 * Developed By     :  <b>@Raghu_android</b>
 * Created Date     :  <b>24.09.2015</b>
 * <p/>
 * Modified Reason  :  <b></b>
 * Modified By      :  <b></b>
 * Modified Date    :  <b></b>
 * <p/>
 */

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class LTU {
    public final static String TAG = "ECS";

    public static void LI(String msg1, String msg2) {
        // TODO to display messages in project
        // msg1 is caller(class or function ) and msg2 is message
//        Log.i(TAG, msg1 + " " + msg2);
    }

    public static void LE(String msg1, String msg2) {
        // TODO to display error during exception in project
        // msg1 is caller(class or function ) and msg2 is message
        Log.e(TAG, msg1 + " " + msg2);
    }

    public static void TIS(Context context, String msg1, String msg2) {
        // TODO to display Toast messages and log
        // msg1 is caller(class or function ) and msg2 is message
        Log.i(TAG, "Toast : " + msg1 + " " + msg2);
        Toast.makeText(context, msg2, Toast.LENGTH_SHORT).show();
    }

    public static void TIL(Context context, String msg1, String msg2) {
        // TODO to display error during exception in project
        // msg1 is caller(class or function ) and msg2 is message
        Log.i(TAG, "Toast : " + msg1 + " " + msg2);
        Toast.makeText(context, msg2, Toast.LENGTH_LONG).show();
    }

    public static void TES(Context context, String msg1, String msg2) {
        // TODO to display error during exception in project
        // msg1 is caller(class or function ) and msg2 is message
        Log.e(TAG, "Toast : " + msg1 + " " + msg2);
        Toast.makeText(context, msg2, Toast.LENGTH_SHORT).show();
    }

    public static void TEL(Context context, String msg1, String msg2) {
        // TODO to display error during exception in project
        // msg1 is caller(class or function ) and msg2 is message
        Log.e(TAG, "Toast : " + msg1 + " " + msg2);
        Toast.makeText(context, msg2, Toast.LENGTH_LONG).show();
    }

    //TODO Unused
    public static void SBS(View view, String msg1, String msg2) {
        // TODO to display error during exception in project
        // msg1 is caller(class or function ) and msg2 is message
        Log.e(TAG, "Toast : " + msg1 + " " + msg2);
        Snackbar.make(view, msg2, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
    }

    public static void SBL(View view) {
        // TODO to display error during exception in project
        // msg1 is caller(class or function ) and msg2 is message

        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public static void TOAST_MSG(Context context, String msg2) {
        // TODO to display error during exception in project
        final Toast t = Toast.makeText(context, msg2, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER, 10, 100);
        t.show();

        // final Toast toast = Toast.makeText(getApplicationContext(), "This message will disappear     in half second", Toast.LENGTH_SHORT);
        // toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                t.cancel();
            }
        }, 500);
    }

}
