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
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ecs.offers.Activity.SelectPaymentOptionsActivity;
import com.ecs.offers.R;
import com.ecs.offers.database.DataBaseHelper;
import com.ecs.offers.util.LTU;
import com.ecs.offers.util.MU;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nitin Malwadkar on 06/02/2017.
 */
public class MyPaymentListAdapter extends BaseAdapter {

    private final JSONArray arraylist;
    Context context;
    private LayoutInflater mInflater;
    private SharedPreferences sharedPreferences;
    DataBaseHelper dh;
    boolean flag = false;

    public MyPaymentListAdapter(Context mainActivity, JSONArray list) {
        context = mainActivity;
        this.arraylist = list;
        dh = new DataBaseHelper(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub

        return arraylist.length();
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
        convertView = mInflater.inflate(R.layout.my_payment_list_adapter, parent, false);
        holder = new Holder(convertView);
        convertView.setTag(holder);

        try {
            final JSONObject jsonObj = arraylist.getJSONObject(position);
            holder.txtID.setText(String.valueOf(position+1));
            holder.txtBankName.setText(jsonObj.getString("bank_name")+"  "+jsonObj.getString("card_type")+" "+jsonObj.getString("provider_name"));

            holder.btnDeleteMyList.setOnClickListener(new View.OnClickListener()
            {

                @TargetApi(Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    try {
                        arraylist.remove(position);
                        flag = dh.deleteMyList(jsonObj.getString("id"));

                        if(flag) {
                            LTU.TOAST_L(context, MU.DELETE_RECORD);
                            MyPaymentListAdapter.this.notifyDataSetChanged();
                        }else
                            LTU.TOAST_L(context, MU.DELETE_ERROR);

                    } catch (Exception e)
                    {
                        e.printStackTrace();

                    }
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

        convertView.setTag(holder);
        return convertView;
    }

    static class Holder {

        TextView txtBankName, txtCardType, txtID, txtProvider;
        ImageButton btnDeleteMyList;

        public Holder(View convertView) {
            // TODO Auto-generated constructor stub

            txtBankName = (TextView) convertView.findViewById(R.id.txt_bank_name);
            //txtCardType = (TextView) convertView.findViewById(R.id.txt_card_type);
            txtID = (TextView) convertView.findViewById(R.id.txt_id);
            btnDeleteMyList = (ImageButton) convertView.findViewById(R.id.btn_delete_my_list);
            //txtProvider = (TextView) convertView.findViewById(R.id.txt_provider);
        }

    }


}