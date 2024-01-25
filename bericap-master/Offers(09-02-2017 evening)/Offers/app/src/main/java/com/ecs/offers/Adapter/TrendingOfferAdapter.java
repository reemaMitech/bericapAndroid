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
 * Created by dell on 1/25/2017.
 */
public class TrendingOfferAdapter extends RecyclerView.Adapter<TrendingOfferAdapter.ViewHolder> {

    ArrayList<String> trendingalName;
    ArrayList<String> trendingalImage;
    Context context;


    public TrendingOfferAdapter(Context context, ArrayList<String> alName, ArrayList<String> alImage) {
        super();
        this.context = context;
        this.trendingalName = alName;
        this.trendingalImage = alImage;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_item_trending_offer, viewGroup, false);
        v.setBackgroundColor(Color.parseColor("#fbdcbb"));
        v.setBackgroundResource(R.drawable.grid_item_border_trending);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvtrendingImageName.setText(trendingalName.get(i));
        //viewHolder.ivtrendingImg.setImageResource(trendingalImage.get(i));
        DU.ImageLoader(context, trendingalImage.get(i).toString(), viewHolder.ivtrendingImg);
//        viewHolder.setClickListener(new ItemClickListener() {
//            @Override
//            public void onClick(View view, int position, boolean isLongClick) {
//                if (isLongClick) {
//                    Toast.makeText(context, "#" + position + " - " + alName.get(position) + " (Long click)", Toast.LENGTH_SHORT).show();
//                    context.startActivity(new Intent(context, MainActivity.class));
//                } else {
//                    Toast.makeText(context, "#" + position + " - " + alName.get(position), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return trendingalName.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivtrendingImg;
        public TextView tvtrendingImageName;



        public ViewHolder(View itemView) {
            super(itemView);

            ivtrendingImg = (ImageView) itemView.findViewById(R.id.trending_img_thumbnail);
            tvtrendingImageName = (TextView) itemView.findViewById(R.id.trending_tv_imgname);

        }
    }
}
