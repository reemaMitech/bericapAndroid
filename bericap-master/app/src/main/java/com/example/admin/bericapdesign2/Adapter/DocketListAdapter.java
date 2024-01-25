package com.example.admin.bericapdesign2.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.bericapdesign2.Activity.DocketDetailsActivity;
import com.example.admin.bericapdesign2.R;
import com.example.admin.bericapdesign2.Services.AccessWebServices;
import com.example.admin.bericapdesign2.Util.Constants;
import com.example.admin.bericapdesign2.Util.DTU;

import org.json.JSONArray;
import org.json.JSONException;

public class DocketListAdapter extends BaseAdapter {
    private final String flag;
    private Activity activity;
    private JSONArray array;
    private Context context;
    String sDate = "",sTime = "";

    public DocketListAdapter(Activity activity, JSONArray array, String flag) {
        // TODO Auto-generated constructor stub
        super();
        this.array = array;
        this.activity = activity;
        this.flag = flag;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (array != null && array.length() > 0) {
            return array.length();
        }
        return 0;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final int positionFinal = position;
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_item_doketlist, null);
        holder = new ViewHolder(convertView);
        convertView.setTag(holder);


        try {
            if (flag.equals("active")) {
                String date =  array.getJSONObject(position).getString("DOCKET_CREATED_TIME");
                String sep[] = date.split("\\s");
                sDate = sep[0];
                sTime = sep[1];

                holder.llContent.setBackgroundColor(Color.parseColor("#0EBFE9"));
                holder.llContent.setTag("active");
                holder.txtDocketCreatedDate.setText("Docket Created Date: " + DTU.getddmmyyDate(sDate));
                holder.txtDocketCreatedTime.setText("Docket Created Time: " + sTime);
                holder.txtCustomerName.setText("Customer Name: " + array.getJSONObject(position).getString("CUST_NAME"));
                holder.tvCustomerDestination.setText("Customer Destination: " + array.getJSONObject(position).getString("CUSTOMER_ADDRESS_1"));
                holder.txtDocketName.setText("Docket No: " + array.getJSONObject(position).getString("DOCKET_NO") + "-" + array.getJSONObject(position).getString("DOCKET_ID"));
                holder.tvTruckNo.setText("Truck No: " + array.getJSONObject(position).getString("TRUCK_NO"));
                holder.tvdocketlocation.setText("Loading Location: " + array.getJSONObject(position).getString("LOCATION_NAME"));
                holder.tvdocketstatus.setText("Status: " + array.getJSONObject(position).getString("DOCK_STATUS"));
                holder.tvdockettype.setText("Load Type: " + array.getJSONObject(position).getString("LOAD_TYP"));


            } else {

                String date =  array.getJSONObject(position).getString("DOCKET_CREATED_TIME");
                String sep[] = date.split("\\s");
                sDate = sep[0];
                sTime = sep[1];
                holder.llContent.setTag("recent");
                holder.llContent.setBackgroundColor(Color.parseColor("#808080"));
                holder.txtDocketCreatedDate.setText("Docket Created Date: " + DTU.getddmmyyDate(sDate));
                holder.txtDocketCreatedTime.setText("Docket Created Time: " + sTime);
                holder.txtCustomerName.setText("Customer Name: " + array.getJSONObject(position).getString("CUST_NAME"));
                holder.tvCustomerDestination.setText("Customer Destination: " + array.getJSONObject(position).getString("CUSTOMER_ADDRESS_1"));
                holder.txtDocketName.setText("Docket No: " + array.getJSONObject(position).getString("DOCKET_NO") + "-" + array.getJSONObject(position).getString("DOCKET_ID"));
                holder.tvTruckNo.setText("Truck No: " + array.getJSONObject(position).getString("TRUCK_NO"));
                holder.tvdocketlocation.setText("Loading Location: " + array.getJSONObject(position).getString("LOCATION_NAME"));
                holder.tvdocketstatus.setText("Status: " + array.getJSONObject(position).getString("DOCK_STATUS"));
                holder.tvdockettype.setText("Load Type: " + array.getJSONObject(position).getString("LOAD_TYP"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error", e.toString());
        }

        holder.llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sTag = "";
                sTag = String.valueOf(view.getTag());
                Intent intent = new Intent(activity, DocketDetailsActivity.class);
                try {
                    intent.putExtra("flag", sTag);
                    intent.putExtra("docket_no", array.getJSONObject(positionFinal).getString("DOCKET_NO") + "-" + array.getJSONObject(positionFinal).getString("DOCKET_ID"));
                    intent.putExtra("docket_id", array.getJSONObject(positionFinal).getString("DOCKET_ID"));
                    intent.putExtra("truck_no", array.getJSONObject(positionFinal).getString("TRUCK_NO"));
                    intent.putExtra("driver_name", array.getJSONObject(positionFinal).getString("DRIVER_NAME"));
                    intent.putExtra("customer_name", array.getJSONObject(positionFinal).getString("CUST_NAME"));
                    intent.putExtra("docket_status", array.getJSONObject(positionFinal).getString("DOCK_STATUS"));
                    Log.e("ImageUrl", Constants.IMAGE_URL + array.getJSONObject(positionFinal).getString("POST_LOAD_IMG"));
                    intent.putExtra("POST_LOAD_IMG", Constants.IMAGE_URL + array.getJSONObject(positionFinal).getString("POST_LOAD_IMG"));
                    intent.putExtra("PRE_LOAD_IMG", Constants.IMAGE_URL + array.getJSONObject(positionFinal).getString("PRE_LOAD_IMG"));
                    intent.putExtra("SEAL_1_IMG", Constants.IMAGE_URL + array.getJSONObject(positionFinal).getString("SEAL_1_IMG"));
                    //intent.putExtra("SEAL_2_IMG", AccessWebServices.URL+array.getJSONObject(positionFinal).getString("SEAL_2_IMG"));
                    intent.putExtra("DEL_DOC_NO", array.getJSONObject(positionFinal).getString("DEL_DOC_NO"));
                    intent.putExtra("LOAD_TYP", array.getJSONObject(positionFinal).getString("LOAD_TYP"));
                    intent.putExtra("SEAL_1_NO", array.getJSONObject(positionFinal).getString("SEAL_1_NO"));
                    intent.putExtra("SEAL_2_NO", array.getJSONObject(positionFinal).getString("SEAL_2_NO"));
                    //intent.putExtra("SEAL_2_NO", array.getJSONObject(positionFinal).getString("SEAL_2_NO"));
                    intent.putExtra("REMARKS_OVERALL", array.getJSONObject(positionFinal).getString("REMARKS_OVERALL"));

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                activity.startActivity(intent);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView txtDocketCreatedDate,txtDocketCreatedTime,txtDocketName, txtCustomerName, tvTruckNo, tvdocketstatus, tvdocketlocation, tvdockettype, tvCustomerDestination;
        LinearLayout llContent;

        public ViewHolder(View convertView) {
            this.txtDocketCreatedDate = (TextView) convertView.findViewById(R.id.row_docketlist_tv_docket_date);
            this.txtDocketCreatedTime = (TextView) convertView.findViewById(R.id.row_docketlist_tv_docket_time);
            this.txtDocketName = (TextView) convertView.findViewById(R.id.row_docketlist_tv_docketname);
            this.txtCustomerName = (TextView) convertView.findViewById(R.id.row_docketlist_tv_customername);
            this.tvCustomerDestination = (TextView) convertView.findViewById(R.id.row_docketlist_tv_destinationname);
            this.tvTruckNo = (TextView) convertView.findViewById(R.id.row_docketlist_tv_truckno);
            this.tvdocketlocation = (TextView) convertView.findViewById(R.id.row_docketlist_tv_docketloaction);
            this.tvdocketstatus = (TextView) convertView.findViewById(R.id.row_docketlist_tv_docketstatus);
            this.tvdockettype = (TextView) convertView.findViewById(R.id.row_docketlist_tv_dockettype);
            this.llContent = (LinearLayout) convertView.findViewById(R.id.row_item_ll_content);
        }
    }
}