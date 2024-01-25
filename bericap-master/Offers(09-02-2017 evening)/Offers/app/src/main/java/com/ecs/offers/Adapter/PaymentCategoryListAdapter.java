package com.ecs.offers.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecs.offers.R;

/**
 * Created by dell on 1/23/2017.
 */
public class PaymentCategoryListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;
    String sName = "",sFlag="";

    public PaymentCategoryListAdapter(Activity context, String[] itemname, Integer[] imgid,String sFlag) {
        super(context, R.layout.row_payment_category_item, itemname);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.itemname = itemname;
        this.imgid = imgid;
        this.sFlag = sFlag;
    }

    public View getView(int position, View view, ViewGroup parent) {
        int i = position;
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.row_payment_category_item, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.textView1);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        ImageView ivFvrt = (ImageView) rowView.findViewById(R.id.favourite);
        ImageView ivCompare = (ImageView) rowView.findViewById(R.id.compare);
        LinearLayout llContent = (LinearLayout) rowView.findViewById(R.id.ll_content);

        if(sFlag.equals("offers"))
        {
           // ivFvrt.setVisibility(View.VISIBLE);
            //ivCompare.setVisibility(View.VISIBLE);
            llContent.setVisibility(View.VISIBLE);
        }
        txtTitle.setText(itemname[position]);
        sName = itemname[position];
        imageView.setImageResource(imgid[position]);

        RelativeLayout rlMain = (RelativeLayout) rowView.findViewById(R.id.main);

        return rowView;
    }

}
