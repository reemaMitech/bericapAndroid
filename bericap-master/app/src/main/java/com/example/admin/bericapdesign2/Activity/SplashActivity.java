package com.example.admin.bericapdesign2.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.bericapdesign2.R;
import com.example.admin.bericapdesign2.Supply.Activities.SupllyrTabActivity;
import com.example.admin.bericapdesign2.Util.ACU;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    String TAG = "SplashActivity";
    Context context;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = SplashActivity.this;

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(SPLASH_TIME_OUT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (ACU.MySP.getSPBoolean(SplashActivity.this, ACU.MySP.LOGIN_STATUS, false)) {
                        if (ACU.MySP.getFromSP(context, ACU.MySP.ROLE_ID, "").equals("6")) {
                            Intent intent = new Intent(context, SupllyrTabActivity.class);
                            finish();
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(context, MainActivity.class);
                            finish();
                            startActivity(intent);
                        }
                    } else {
                        startActivity(new Intent(context, LoginActivity.class));
                        finish();
                    }

                }
            }
        };
        timerThread.start();
    }
}
