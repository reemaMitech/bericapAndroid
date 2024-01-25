package com.example.admin.bericapdesign2.gcm;
/**
 * Class Name       :  <b>GcmMessageHandler.java<b/>
 * Purpose          :  GcmMessageHandler is class to handles the messages call.
 * Developed By     :  <b>@Raghu_android</b>
 * Created Date     :  <b>24.09.2015</b>
 * <p/>
 * Modified Reason  :  <b></b>
 * Modified By      :  <b></b>
 * Modified Date    :  <b></b>
 * <p/>
 */

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;

import com.example.admin.bericapdesign2.Activity.database.DataBaseConstants;
import com.example.admin.bericapdesign2.Activity.database.DataBaseHelper;
import com.example.admin.bericapdesign2.Activity.model.ModelNotification;
import com.example.admin.bericapdesign2.Activity.notification.NotificationActivity;
import com.example.admin.bericapdesign2.R;
import com.example.admin.bericapdesign2.Util.DTU;
import com.example.admin.bericapdesign2.Util.LTU;
import com.google.android.gms.gcm.GoogleCloudMessaging;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class GcmMessageHandler extends IntentService {

    public static final int MY_NOTIFICATION_ID = 1234;
    String ns = Context.NOTIFICATION_SERVICE;
    String userId;
    int UNIQUE_INT_PER_CALL;

    private Handler handler;

    public GcmMessageHandler() {
        super("GcmMessageHandler");
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        handler = new Handler();
    }

    @Override
    protected void onHandleIntent(final Intent intent) {
        Bundle extras = intent.getExtras();
        UNIQUE_INT_PER_CALL = new Random().nextInt((5 - 0) + 1) + 0;
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String strDatr = (extras.getString("price"));

        LTU.LE("strData", "strData:->" + strDatr);

        ModelNotification object = new ModelNotification();
        try {
            JSONArray jsonArray = new JSONArray(strDatr);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            object.setTitle(jsonObject.getString(DataBaseConstants.NotificationConstants.TITLE));
          //  object.setIs_view("0");
            object.setMessage(jsonObject.getString(DataBaseConstants.NotificationConstants.MESSAGE));
            DataBaseHelper db = new DataBaseHelper(this);
            DataBaseHelper.DBNotification.insert(object);
            showNotification(object, this);
            //showNotificationNew(object, this);
            GcmBroadcastReceiver.completeWakefulIntent(intent);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showNotification(final ModelNotification object, final Context context) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(this, NotificationActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        notificationIntent.putExtra(NotificationActivity.INTENT_EXTRA_NOTIFICATION_ID, MY_NOTIFICATION_ID);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        int color = getResources().getColor(R.color.colorPrimary);
        Bitmap bitmapMila = BitmapFactory.decodeResource(getResources(), R.drawable.app_icon);

        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(context)
                .setContentTitle(object.getTitle())
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setColor(color)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(object.getMessage()))
                .setContentTitle(object.getTitle())
                .setContentText(object.getMessage())
                .setSmallIcon(R.drawable.app_icon)
                .setContentIntent(contentIntent)
                .setLargeIcon(bitmapMila);


        Notification notification = mNotifyBuilder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;

        mNotificationManager.cancel(MY_NOTIFICATION_ID);
        if (object.getTitle() != null) {
            mNotificationManager.notify(MY_NOTIFICATION_ID, notification);
        }
    }
}
