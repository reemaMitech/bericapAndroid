package com.ecs.offers.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecs.offers.R;
import com.ecs.offers.util.DU;

import java.util.ArrayList;

/**
 * Created by Nitin on 1/25/2017.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    ArrayList<String> catregoryalName;
    ArrayList<String> categoryalImage;
    Context context;

    public CategoryAdapter(Context context, ArrayList<String> alName, ArrayList<String> alImage) {
        super();
        this.context = context;
        this.catregoryalName = alName;
        this.categoryalImage = alImage;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_item_category, viewGroup, false);
        v.setBackgroundColor(Color.parseColor("#fbdcbb"));
        v.setBackgroundResource(R.drawable.grid_item_border_category);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvImageName.setText(catregoryalName.get(i));
        DU.ImageLoader(context, categoryalImage.get(i).toString(), viewHolder.ivCategoryImg);

    }

    @Override
    public int getItemCount() {
        return catregoryalName.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivCategoryImg;
        public TextView tvImageName;



        public ViewHolder(View itemView) {
            super(itemView);

            ivCategoryImg = (ImageView) itemView.findViewById(R.id.category_img_thumbnail);
            tvImageName = (TextView) itemView.findViewById(R.id.category_tv_imgname);

        }
    }
}
