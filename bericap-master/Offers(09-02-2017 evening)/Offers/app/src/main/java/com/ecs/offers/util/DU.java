package com.ecs.offers.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ImageView;

import com.ecs.offers.R;
import com.squareup.picasso.Picasso;

public class DU {
	public static void showDialog11(Context appContext, String msg) {
		// TODO show Dialog...........
		AlertDialog.Builder alert = new AlertDialog.Builder(appContext);
		alert.setTitle("Alert");
		alert.setMessage(msg);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//dialog.dismiss();
			}
		});
		alert.show();

	}

	public static void showDialog(Context appContext, String msg)
	{
		android.support.v7.app.AlertDialog.Builder dlgAlert  = new android.support.v7.app.AlertDialog.Builder(appContext);
		dlgAlert.setMessage(msg);
		dlgAlert.setTitle("Message");
		dlgAlert.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		dlgAlert.setCancelable(true);
		dlgAlert.create().show();
	}

	public static void ImageLoader(Context context, String url, ImageView imgView) {
		int placeholder = R.drawable.ic_about;
		Picasso.with(context)
				.load(url)
				.placeholder(placeholder)
				.error(placeholder)
				.into(imgView);

	}
}
