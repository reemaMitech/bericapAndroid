package com.example.admin.bericapdesign2.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.admin.bericapdesign2.R;

public class Dialog {
    public static void showDialogOnBackPressed(final Context appContext, String msg) {
        // TODO show Dialog...........
        AlertDialog.Builder builder = new AlertDialog.Builder(appContext);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(appContext.getResources().getString(R.string.yes_text), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((Activity) appContext).finish();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        appContext.startActivity(intent);
                    }
                })
                .setNegativeButton(appContext.getResources().getString(R.string.no_text), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void showDialog1(Context appContext, String msg) {
        // TODO show Dialog...........
        AlertDialog.Builder alert = new AlertDialog.Builder(appContext);
        alert.setTitle(appContext.getResources().getString(R.string.alert_text));
        alert.setMessage(msg);
        alert.setPositiveButton(appContext.getResources().getString(R.string.ok_text), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                //dialog.dismiss();
            }
        });
        alert.show();
    }
}
