package com.example.admin.bericapdesign2.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;


/**
 * Created by ECS-Android on 10/4/2016.
 */
public class GCMTokenRefreshListener extends InstanceIDListenerService {

    //If the token is changed registering the device again
    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, GCMRegistrationIntent.class);
        startService(intent);
    }
}
