package com.example.admin.bericapdesign2.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;


import com.example.admin.bericapdesign2.Activity.database.DataBaseHelper;
import com.example.admin.bericapdesign2.Activity.model.ModelNotification;
import com.example.admin.bericapdesign2.Activity.notification.NotificationActivity;
import com.example.admin.bericapdesign2.R;
import com.example.admin.bericapdesign2.Util.DTU;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by ECS-Android on 10/4/2016.
 */
public class GCMPushReceiver extends GcmListenerService {

    //This method will be called on every new message received
    @Override
    public void onMessageReceived(String from, Bundle data) {
        //Getting the message from the bundle
        String message = data.getString("message");
        String title = data.getString("title");
        //Displaying a notiffication with the message
        setNotification(title,message);
        sendNotification(title,message);
    }

    private void setNotification(String title, String message) {
        ModelNotification object = new ModelNotification();
        try {
//            JSONArray jsonArray = new JSONArray(strDatr);
//            JSONObject jsonObject = jsonArray.getJSONObject(0);
            object.setTitle(title);
            object.setIs_view("0");
            object.setMessage(message);
            object.setType("");
            object.setDate_time(DTU.getCurrentDateTimeStamp(DTU.YMD_HMS));
            object.setId("");
            object.setCreated_time(DTU.getCurrentDateTimeStamp(DTU.YMD));
            DataBaseHelper db = new DataBaseHelper(this);
            DataBaseHelper.DBNotification.insert(object);
            //showNotification(object, this);
            //showNotificationNew(object, this);
           // GcmBroadcastReceiver.completeWakefulIntent(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //This method is generating a notification and displaying the notification
    private void sendNotification(String title,String message) {
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        int requestCode = 0;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.app_icon)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, noBuilder.build()); //0 = ID of notification
    }
}

