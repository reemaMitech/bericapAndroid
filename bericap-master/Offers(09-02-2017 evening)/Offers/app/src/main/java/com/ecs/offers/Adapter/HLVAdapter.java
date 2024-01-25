package com.ecs.offers.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.ecs.offers.Activity.CategoryDetailActivity;
import com.ecs.offers.Activity.SelectPaymentOptionsActivity;
import com.ecs.offers.R;
import com.ecs.offers.util.DU;

import java.util.ArrayList;

/**
 * Created by deepa on 1/25/2017.
 */
public class HLVAdapter extends RecyclerView.Adapter<HLVAdapter.ViewHolder> {

    ArrayList<String> alName;
    ArrayList<String> alImage;
    Context context;


    public HLVAdapter(Context context, ArrayList<String> alName, ArrayList<String> alImage) {
        super();
        this.context = context;
        this.alName = alName;
        this.alImage = alImage;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_item, viewGroup, false);
        v.setBackgroundColor(Color.parseColor("#fbdcbb"));
        v.setBackgroundResource(R.drawable.grid_item_border_payment);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvSpecies.setText(alName.get(i));
        //viewHolder.imgThumbnail.setImageResource(alImage.get(i));
        DU.ImageLoader(context, alImage.get(i), viewHolder.imgThumbnail);

    }

    @Override
    public int getItemCount() {
        return alName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgThumbnail;
        public TextView tvSpecies;



        public ViewHolder(View itemView) {
            super(itemView);

            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            tvSpecies = (TextView) itemView.findViewById(R.id.tv_species);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SelectPaymentOptionsActivity.class);
                    context.startActivity(intent);

            }
            });

        }







    }



}
