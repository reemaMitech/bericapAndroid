package com.example.admin.bericapdesign2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.admin.bericapdesign2.Activity.model.ModelNotification;
import com.example.admin.bericapdesign2.R;
import com.example.admin.bericapdesign2.Util.LTU;

import java.util.ArrayList;

/**
 * Created by ECS on 3/1/2016.
 */
public class NotificationAdapter extends ArrayAdapter<ModelNotification> {
    private static final String TAG = "NotificationAdapter";
    public ArrayList<ModelNotification> list;
    private Context context;
    private LayoutInflater inflater;

    public NotificationAdapter(Context context, int resource, ArrayList<ModelNotification> list) {
        super(context, resource, list);
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.row_notification, parent, false);
        final ViewHolder hold = new ViewHolder(convertView);
        try {
            convertView.setTag(hold);
            hold.txtTitle.setText(list.get(position).getTitle());
            hold.txtMessages.setText(list.get(position).getMessage());
        } catch (Exception e) {
            LTU.LE(TAG, "Class Name " + TAG + " method Name " + " getView() " + " Exception occurred due to :..............................");
            LTU.LE(TAG, "Exception " + e.toString());
        }

        return convertView;

    }

    class ViewHolder {
        public TextView txtTitle, txtMessages;
        public LinearLayout llRoot;

        public ViewHolder() {
        }

        public ViewHolder(View convertView) {
            txtTitle = (TextView) convertView.findViewById(R.id.row_notification_txt_title);
            txtMessages = (TextView) convertView.findViewById(R.id.row_notification_txt_message);

        }


        public ViewHolder(TextView txtMessages, TextView txtTitle, TextView txtDateTime, LinearLayout llRoot) {
            this.txtMessages = txtMessages;
            this.txtTitle = txtTitle;

        }
    }

}
