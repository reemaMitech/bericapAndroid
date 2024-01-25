package com.example.admin.bericapdesign2.Activity.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.admin.bericapdesign2.Activity.database.DataBaseHelper;
import com.example.admin.bericapdesign2.Activity.model.ModelNotification;
import com.example.admin.bericapdesign2.Adapter.NotificationAdapter;
import com.example.admin.bericapdesign2.R;
import com.example.admin.bericapdesign2.gcm.GcmMessageHandler;


import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    private Context context;  public static String INTENT_EXTRA_NOTIFICATION_ID = "NotificationActivity";
    private String TAG = "NotificationActivity";
    private ListView listView;
    private ProgressBar progressBar;
    private TextView txtError;
    public static DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initUI();
        setNotificationStatus();
        setListAdapter();
    }

    private void initUI() {
        context = NotificationActivity.this;
        db = new DataBaseHelper(context);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listView);
        txtError = (TextView) findViewById(R.id.txtError);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    //TODO: method to remove the pending notification in the status bar
    private void setNotificationStatus() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(GcmMessageHandler.MY_NOTIFICATION_ID);
    }

    //TODO method to set List view Adapter
    private void setListAdapter() {
        txtError.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        ArrayList<ModelNotification> list = DataBaseHelper.DBNotification.getList();
        if (list != null && list.size() > 0) {
            listView.setVisibility(View.VISIBLE);
            txtError.setVisibility(View.GONE);
            NotificationAdapter adapter = new NotificationAdapter(context, R.layout.row_notification, list);
            listView.setAdapter(adapter);
        } else {
            listView.setVisibility(View.GONE);
            txtError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
