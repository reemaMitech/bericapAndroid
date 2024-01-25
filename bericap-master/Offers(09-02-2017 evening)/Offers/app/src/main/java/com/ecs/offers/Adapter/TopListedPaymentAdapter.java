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
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ecs.offers.Activity.SelectPaymentOptionsActivity;
import com.ecs.offers.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nitin Malwadkar on 04/02/2017.
 */
public class TopListedPaymentAdapter extends BaseAdapter {

    private final ArrayList arraylist;
    Context context;
    private LayoutInflater mInflater;
    private SharedPreferences sharedPreferences;
    private RadioButton mSelectedRB;
    private int mSelectedPosition = -1;
    SelectPaymentOptionsActivity selectPaymentOptionsActivity;

    public TopListedPaymentAdapter(Context mainActivity, ArrayList list) {
        context = mainActivity;
        this.arraylist = list;
        selectPaymentOptionsActivity = new SelectPaymentOptionsActivity();
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
        convertView = mInflater.inflate(R.layout.top_listed_payment_adapter, parent, false);
        holder = new Holder(convertView);
        convertView.setTag(holder);
        holder.rbTopListed.setText(arraylist.get(position).toString());
        final Holder finalHolder = holder;
        holder.rbTopListed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {

                    selectPaymentOptionsActivity.jsonObject.put("bank_name",""+arraylist.get(position).toString());

                    if(!SelectPaymentOptionsActivity.jsonObject.getString("provider_name").equals("E-Wallet") && !SelectPaymentOptionsActivity.jsonObject.getString("provider_name").equals("NetBanking")) {
                        if(SelectPaymentOptionsActivity.jsonObject.has("provider_name")) {
                            if (SelectPaymentOptionsActivity.jsonObject.getString("provider_name").equals("Credit Card")) {
                                selectPaymentOptionsActivity.setCardLayout(context, 1);
                            } else {
                                selectPaymentOptionsActivity.setCardLayout(context, 2);
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if ((position != mSelectedPosition && mSelectedRB != null)) {
                    mSelectedRB.setChecked(false);
                    Log.e("kkk","7");
                    //holder.rbTopListed.setChecked(false);
                }else{
                    Log.e("kkk","8");
                    finalHolder.rbTopListed.setChecked(true);
                    mSelectedPosition = position;
                }

                mSelectedPosition = position;
                mSelectedRB = (RadioButton) v;
                mSelectedRB = finalHolder.rbTopListed;
            }
        });
       /* Log.e("kkk","0");
        if (mSelectedPosition != position) {
            Log.e("kkk","1");
            holder.rbTopListed.setChecked(false);
        } else {
            Log.e("kkk","2");
            holder.rbTopListed.setChecked(true);
            mSelectedPosition = position;
            if (mSelectedRB != null && holder.rbTopListed != mSelectedRB) {
                Log.e("kkk","3");
                mSelectedRB = holder.rbTopListed;
            }
        }
*/

        convertView.setTag(holder);
        return convertView;
    }

    static class Holder {

        RadioButton rbTopListed;

        public Holder(View convertView) {
            // TODO Auto-generated constructor stub

            rbTopListed = (RadioButton) convertView.findViewById(R.id.rb_top_listed);
        }

    }


}