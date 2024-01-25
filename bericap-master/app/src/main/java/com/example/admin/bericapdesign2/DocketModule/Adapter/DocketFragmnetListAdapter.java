package com.example.admin.bericapdesign2.DocketModule.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.bericapdesign2.Activity.DocketDetailsActivity;
import com.example.admin.bericapdesign2.DocketModule.Activities.UnLoadingDocketDetailsActivity;
import com.example.admin.bericapdesign2.DocketModule.Model.DocketFragmentListModel;
import com.example.admin.bericapdesign2.R;
import com.example.admin.bericapdesign2.Util.Constants;
import com.example.admin.bericapdesign2.Util.DTU;

import java.util.ArrayList;

public class DocketFragmnetListAdapter extends RecyclerView.Adapter<DocketFragmnetListAdapter.ViewHolder> implements Filterable {
    private ArrayList<DocketFragmentListModel> mArrayList;
    private ArrayList<DocketFragmentListModel> mFilteredList;
    String flag;
    Context context;
    String sDate = "", sTime = "";

    public DocketFragmnetListAdapter(Context context, ArrayList<DocketFragmentListModel> arrayList, String sFlag) {
        mArrayList = arrayList;
        mFilteredList = arrayList;
        this.context = context;
        this.flag = sFlag;
    }

    @Override
    public DocketFragmnetListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_doketlist, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DocketFragmnetListAdapter.ViewHolder holder, final int position) {

        try {
            if (flag.equals(Constants.ACTVIE)) {
                String date = mFilteredList.get(position).get_DOCKET_CREATED_TIME();
                String sep[] = date.split("\\s");
                sDate = sep[0];
                sTime = sep[1];
                holder.llContent.setBackgroundColor(Color.parseColor("#0EBFE9"));
                holder.llContent.setTag(Constants.ACTVIE);
                holder.txtDocketCreatedDate.setText("Docket Created Date: " + DTU.getddmmyyDate(sDate));
                holder.txtDocketCreatedTime.setText("Docket Created Time: " + sTime);
                holder.txtCustomerName.setText("Customer Name: " + mFilteredList.get(position).get_CUST_NAME());
                holder.tvCustomerDestination.setText("Customer Destination: " + mFilteredList.get(position).get_CUSTOMER_ADDRESS_1());
                holder.txtDocketName.setText("Docket No: " + mFilteredList.get(position).get_DOCKET_NO() + "-" + mFilteredList.get(position).get_DOCKET_ID());
                holder.tvTruckNo.setText("Truck No: " + mFilteredList.get(position).get_TRUCK_NO());
                holder.tvdocketlocation.setText("Loading Location: " + mFilteredList.get(position).get_LOCATION_NAME());
                holder.tvdocketstatus.setText("Status: " + mFilteredList.get(position).get_DOCK_STATUS());
                holder.tvdockettype.setText("Load Type: " + mFilteredList.get(position).get_LOAD_TYP());
            } else {
                String date = mFilteredList.get(position).get_DOCKET_CREATED_TIME();
                String sep[] = date.split("\\s");
                sDate = sep[0];
                sTime = sep[1];
                holder.llContent.setTag(Constants.RECENT);
                holder.llContent.setBackgroundColor(Color.parseColor("#808080"));
                holder.txtDocketCreatedDate.setText("Docket Created Date: " + DTU.getddmmyyDate(sDate));
                holder.txtDocketCreatedTime.setText("Docket Created Time: " + sTime);
                holder.txtCustomerName.setText("Customer Name: " + mFilteredList.get(position).get_CUST_NAME());
                holder.tvCustomerDestination.setText("Customer Destination: " + mFilteredList.get(position).get_CUSTOMER_ADDRESS_1());
                holder.txtDocketName.setText("Docket No: " + mFilteredList.get(position).get_DOCKET_NO() + "-" + mFilteredList.get(position).get_DOCKET_ID());
                holder.tvTruckNo.setText("Truck No: " + mFilteredList.get(position).get_TRUCK_NO());
                holder.tvdocketlocation.setText("Loading Location: " + mFilteredList.get(position).get_LOCATION_NAME());
                holder.tvdocketstatus.setText("Status: " + mFilteredList.get(position).get_DOCK_STATUS());
                holder.tvdockettype.setText("Load Type: " + mFilteredList.get(position).get_LOAD_TYP());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sTag = "";
                sTag = String.valueOf(view.getTag());
                Intent intent = new Intent(context, DocketDetailsActivity.class);
                try {
                    intent.putExtra("flag", sTag);
                    intent.putExtra("docket_no", mFilteredList.get(position).get_DOCKET_NO() + "-" + mFilteredList.get(position).get_DOCKET_ID());
                    intent.putExtra("docket_id", mFilteredList.get(position).get_DOCKET_ID());
                    intent.putExtra("truck_no", mFilteredList.get(position).get_TRUCK_NO());
                    intent.putExtra("driver_name", mFilteredList.get(position).get_DRIVER_NAME());
                    intent.putExtra("customer_name", mFilteredList.get(position).get_CUST_NAME());
                    intent.putExtra("docket_status", mFilteredList.get(position).get_DOCK_STATUS());
                    intent.putExtra("POST_LOAD_IMG", Constants.IMAGE_URL + mFilteredList.get(position).get_POST_LOAD_IMG());
                    intent.putExtra("PRE_LOAD_IMG", Constants.IMAGE_URL + mFilteredList.get(position).get_PRE_LOAD_IMG());
                    intent.putExtra("SEAL_1_IMG", Constants.IMAGE_URL + mFilteredList.get(position).get_SEAL_1_IMG());
                    intent.putExtra("DEL_DOC_NO", mFilteredList.get(position).get_DEL_DOC_NO());
                    intent.putExtra("LOAD_TYP", mFilteredList.get(position).get_LOAD_TYP());
                    intent.putExtra("SEAL_1_NO", mFilteredList.get(position).get_SEAL_1_NO());
                    intent.putExtra("SEAL_2_NO", mFilteredList.get(position).getSEAL_2_NO());
                    intent.putExtra("REMARKS_OVERALL", mFilteredList.get(position).getREMARKS_OVERALL());
                  //  intent.putExtra("GRN_NO", mFilteredList.get(position).getGRN_NO());
                   // intent.putExtra("INVOICE_NO", mFilteredList.get(position).getINVOICE_NO());
                    //intent.putExtra("UNLOADING_STATUS", mFilteredList.get(position).getUNLOADING_STATUS());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                context.startActivity(intent);
            }
        });

    }

    public void updateList(ArrayList<DocketFragmentListModel> list) {
        mFilteredList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList;
                } else {

                    ArrayList<DocketFragmentListModel> filteredList = new ArrayList<>();

                    for (DocketFragmentListModel androidVersion : mArrayList) {


                        if (androidVersion.get_DEL_DOC_NO().trim().toLowerCase().contains(charString.trim().toLowerCase()) || androidVersion.get_CUST_NAME().toLowerCase().trim().contains(charString.trim().toLowerCase())) {
                            filteredList.add(androidVersion);
                        } else {
                        }
                    }
                    mFilteredList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                // notifyDataSetChanged();

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<DocketFragmentListModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDocketCreatedDate, txtDocketCreatedTime, txtDocketName, txtCustomerName, tvTruckNo, tvdocketstatus, tvdocketlocation, tvdockettype, tvCustomerDestination;
        LinearLayout llContent;

        public ViewHolder(View convertView) {
            super(convertView);

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