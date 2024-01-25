package com.ecs.offers.util;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by DELL on 09/30/2015.
 */
public class LTU {
    public final static String TAG = "ECS";

    public static void LI(String msg1, String msg2) {
        // TODO to display messages in project
        // msg1 is caller(class or function ) and msg2 is message
        Log.i(TAG, msg1 + " " + msg2);
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
//        Toast.makeText(context, msg2, Toast.LENGTH_SHORT).show();
        Toast t = Toast.makeText(context, msg2, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.TOP, 10, 100);
        t.show();
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
//        Toast.makeText(context, msg2, Toast.LENGTH_SHORT).show();
        Toast t = Toast.makeText(context, msg2, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.TOP, 10, 100);
        t.show();
    }

    public static void TEL(Context context, String msg1, String msg2) {
        // TODO to display error during exception in project
        // msg1 is caller(class or function ) and msg2 is message
        Log.e(TAG, "Toast : " + msg1 + " " + msg2);
        Toast.makeText(context, msg2, Toast.LENGTH_LONG).show();
    }

    public static void TOAST_L(Context context,String msg2) {
        // TODO to display error during exception in project
        Toast t = Toast.makeText(context, msg2, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER, 10, 100);
        t.show();
    }
}
