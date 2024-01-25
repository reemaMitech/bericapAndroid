package com.example.admin.bericapdesign2.gcm;
/**
 * Class Name       :  <b>GcmBroadcastReceiver.java<b/>
 * Purpose          :  GcmBroadcastReceiver is to receive gcm messages.
 * Developed By     :  <b>@Raghu_android</b>
 * Created Date     :  <b>24.09.2015</b>
 * <p/>
 * Modified Reason  :  <b></b>
 * Modified By      :  <b></b>
 * Modified Date    :  <b></b>
 * <p/>
 */

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.example.admin.bericapdesign2.Util.LTU;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();
        String fromUserId = null;
        String actionType = extras.getString("gcm_action");

        LTU.LE("GCM ", "STRING " + actionType);

        ComponentName comp = new ComponentName(context.getPackageName(), GcmMessageHandler.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}
