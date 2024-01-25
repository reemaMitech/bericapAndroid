package com.ecs.offers.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ecs.offers.Activity.SelectPaymentOptionsActivity;
import com.ecs.offers.CustomClasses.CustomTextView;
import com.ecs.offers.R;
import com.ecs.offers.WebServicesUtil.AccessWebServices;
import com.ecs.offers.database.DataBaseConstants;
import com.ecs.offers.database.DataBaseHelper;
import com.ecs.offers.util.BU;
import com.ecs.offers.util.DU;
import com.ecs.offers.util.LTU;
import com.ecs.offers.util.MU;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nitin on 8/2/2017.
 */
public class OfferListAdapter extends BaseAdapter {

    private final Activity context;
    private final JSONArray jsonArray;
    int Resource;
    DataBaseHelper dh;
    CustomTextView tvDiscountText,tvOfferName,tvOfferInformation , txtLike;
    ImageView ivOffer,ivdiscountTag, imgLikeBtn, imgCompareBtn, imgFavoriteBtn, imgShareBtn;


    public OfferListAdapter(Activity context, JSONArray array) {

        this.context=context;
        this.jsonArray = array;
        dh = new DataBaseHelper(context);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub

        return jsonArray.length();
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

    public View getView(int position,View view,ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.offer_list_adapter, null, true);
        try {

            final JSONObject jsonObject = jsonArray.getJSONObject(position);

            tvOfferName = (CustomTextView) rowView.findViewById(R.id.payment_options_tv_offer_name);
            tvOfferInformation = (CustomTextView) rowView.findViewById(R.id.payment_options_tv_desc);
            tvDiscountText = (CustomTextView) rowView.findViewById(R.id.paymentoffer_tv_discounttag_txt);
            ivOffer = (ImageView) rowView.findViewById(R.id.payment_offer_image);

            imgLikeBtn = (ImageView) rowView.findViewById(R.id.img_like_btn);
            imgCompareBtn = (ImageView) rowView.findViewById(R.id.img_compare);
            imgFavoriteBtn = (ImageView) rowView.findViewById(R.id.img_favorite);
            imgShareBtn = (ImageView) rowView.findViewById(R.id.img_share);

            txtLike = (CustomTextView) rowView.findViewById(R.id.txt_like);

            tvOfferName.setText(jsonObject.getString("offer_name"));
            tvOfferInformation.setText(jsonObject.getString("offer_detail"));
            tvDiscountText.setText(jsonObject.getString("discount")+"% Discount");
            DU.ImageLoader(context, jsonObject.getString("offer_image"), ivOffer);
            int likeCount = dh.getLikeCount(jsonObject.getString("id"));
            txtLike.setText("Like "+likeCount);
            Log.e("likeCount",""+likeCount);

            setAllButtonsAction(jsonObject);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        // imageView.setImageResource(DiscountTagImage[position]);
        return rowView;
    }

    private void setAllButtonsAction(final JSONObject jsonObject) {

        imgLikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dh.addLikeShareCount(jsonObject.getString("id"), "Like");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (BU.isConnectingToInternet(context)) {
                    new UploadData().execute();

                } else {
                    LTU.TOAST_L(context, MU.DATA_ADD_SUCCESS);
                }
                OfferListAdapter.this.notifyDataSetChanged();
            }
        });

        imgCompareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dh.addLikeShareCount(jsonObject.getString("id"), "Compare");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (BU.isConnectingToInternet(context)) {
                    new UploadData().execute();

                } else {
                    LTU.TOAST_L(context, MU.DATA_ADD_SUCCESS);
                }
                OfferListAdapter.this.notifyDataSetChanged();
            }
        });

        imgFavoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dh.addLikeShareCount(jsonObject.getString("id"), "Favorite");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (BU.isConnectingToInternet(context)) {
                    new UploadData().execute();

                } else {
                    LTU.TOAST_L(context, MU.DATA_ADD_SUCCESS);
                }
                OfferListAdapter.this.notifyDataSetChanged();
            }
        });

        imgShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String msg = "\nOffer Name : "+jsonObject.getString("offer_name")+"\n\n Offer Details : "+jsonObject.getString("offer_detail")+"\n\n"+"Offer Discount : "+jsonObject.getString("discount")+"% Discount";
                    Intent sharingIntent = new Intent(
                            android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, msg);
                    context.startActivity(Intent.createChooser(sharingIntent, context.getResources()
                            .getString(R.string.share_using)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /*//dh.addLikeShareCount(id, "Favorite");
                if (BU.isConnectingToInternet(context)) {
                    new UploadData().execute();

                } else {
                    LTU.TOAST_L(context, MU.DATA_ADD_SUCCESS);
                }
                OfferListAdapter.this.notifyDataSetChanged();*/
            }
        });
    }

    ;

    class UploadData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return AccessWebServices.UploadDataAll(DataBaseConstants.TableNames.TBL_LIKE_FAVORITE_SHARE,dh);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.e("upload result",""+result);

        }
    }

}
