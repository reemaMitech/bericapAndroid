package com.example.admin.bericapdesign2.Util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.admin.bericapdesign2.R;
import com.squareup.picasso.Picasso;


//import com.squareup.picasso.Picasso;


public class IU {
    public static final String IMAGE_DIRECTORY_NAME = "Groupool";
    public static final int SELECT_PICTURE = 1;
    public static final int CAMERA_REQUEST = 2;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    public static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int SELECT_PICTURE_REQUEST_CODE = 200;
    public static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 300;

    public static void ResizeImageLoader(Context context, String url, int placeholder, ImageView imgView) {
        Picasso.with(context).load(url)
                .placeholder(placeholder)
                .resize(600, 220)
                .into(imgView);

    }

    public static void ResizeImageLoader(Context context, String url, int placeholder, ImageView imgView, ProgressBar progressBar) {
        try {
            Picasso.with(context).load(url)
                    .placeholder(placeholder)
                    .resize(550, 250)
                    .into(imgView);
            progressBar.setVisibility(View.GONE);
        } catch (Exception e) {
            progressBar.setVisibility(View.GONE);
        }
    }

    public static void ImageLoader(Context context, String url, ImageView imgView) {
        int placeholder = 1;
        Picasso.with(context)
                .load(url)
                .placeholder(placeholder)
                .error(placeholder)
                .into(imgView);

    }
    public static void ImageLoaderWith(Context context, String url, ImageView imgView) {
        try {
            int user = R.drawable.ic_bericap_logo;
            Picasso.with(context)
                    .load(url)
                    .placeholder(user)
                    .error(user)
                    .into(imgView);
//            Picasso.with(context).load(url)
//                    .into(imgView);
        } catch (Exception e) {
        }
    }

    public static void ImageLoaderWith(Context context, String url, ImageView imgView,int images ){
        try {
            Picasso.with(context)
                    .load(url)
                    .placeholder(images)
                    .error(images)
                    .into(imgView);
//            Picasso.with(context).load(url)
//                    .into(imgView);
        } catch (Exception e) {
        }
    }

    public static void ImageLoader(Context context, String url, ImageView imgView, boolean isBoy) {
//		int placeholder=1;
//		int placeholderBoy= R.drawable.ic_user;
//		int placeholderGirl=R.drawable.ic_user;
//		Picasso.with(context)
//				.load(url)
//				.placeholder(placeholderBoy)
//				.error(placeholderBoy)
//				.into(imgView);

    }


}
