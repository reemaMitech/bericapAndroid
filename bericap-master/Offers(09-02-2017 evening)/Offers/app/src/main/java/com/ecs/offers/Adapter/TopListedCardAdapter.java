package com.ecs.offers.Adapter;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ecs.offers.Activity.SelectPaymentOptionsActivity;
import com.ecs.offers.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nitin Malwadkar on 06/02/2017.
 */
public class TopListedCardAdapter extends BaseAdapter {

    private final ArrayList arraylist;
    Context context;
    private LayoutInflater mInflater;
    private SharedPreferences sharedPreferences;

    public TopListedCardAdapter(Context mainActivity, ArrayList list) {
        context = mainActivity;
        this.arraylist = list;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub

        return arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub

        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = null;

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.top_selected_card_adapter, parent, false);
        holder = new Holder(convertView);
        convertView.setTag(holder);

        holder.cbTopListedCard.setText(arraylist.get(position).toString());
        holder.cbTopListedCard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SelectPaymentOptionsActivity.jsonArray.put(arraylist.get(position).toString());
                }
            }
        });
        convertView.setTag(holder);
        return convertView;
    }

    static class Holder {

        CheckBox cbTopListedCard;

        public Holder(View convertView) {
            // TODO Auto-generated constructor stub

            cbTopListedCard = (CheckBox) convertView.findViewById(R.id.cb_top_listed_card);
        }

    }


}