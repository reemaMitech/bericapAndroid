package com.example.admin.bericapdesign2.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.admin.bericapdesign2.AsyncTask.AsyncResponse;
import com.example.admin.bericapdesign2.AsyncTask.MyAsyncTask;
import com.example.admin.bericapdesign2.BuildConfig;
import com.example.admin.bericapdesign2.CustomClasses.CustomButton;
import com.example.admin.bericapdesign2.CustomClasses.CustomTextView;
import com.example.admin.bericapdesign2.R;
import com.example.admin.bericapdesign2.Services.AccessWebServices;
import com.example.admin.bericapdesign2.Util.ACU;
import com.example.admin.bericapdesign2.Util.BU;
import com.example.admin.bericapdesign2.Util.Connectivity;
import com.example.admin.bericapdesign2.Util.Constants;
import com.example.admin.bericapdesign2.Util.IU;
import com.example.admin.bericapdesign2.Util.LTU;
import com.example.admin.bericapdesign2.Util.MU;
import com.example.admin.bericapdesign2.Util.VU;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static pub.devrel.easypermissions.EasyPermissions.hasPermissions;

public class DocketDetailsActivity extends AppCompatActivity implements AsyncResponse {

    String TAG = "DocketDetailsActivity";
    Toolbar toolbar;
    Context context;
    Bitmap bitmap1, bitmap2, bitmap3;
    ImageView ivPreLoadCapture, ivPostLoadCapture, ivSealOneCaptured;

    CustomButton btnPreLoad, btnPostLoad, btnSealOne, btnSubmit, btnupload1, btnclickagain1, btnclickagain2, btnclickagain3;
    LinearLayout llPreLoad, llPostLoad, llSealOne, llSealTwo;
    CustomTextView tvDocketNo, tvDocketNo1, tvTruckNo, tvTruckNo1, tvDriverName, tvDriverName1, tvCustomerName, tvCustomerName1, tvdeldocno, tvpostload, tvpostloadcloseddoor, tvSealOne, tvSealTwo, tvRemark;
    String sFlag = "", sSealOneNo = "", sSealTwoNo = "", sRemark = "", sAddRemark = "", sDocketNo = "", sTruckNo = "", sDriverName = "", sCustomerName = "", sDocketId = "", docket_status = "", sDelDocNo = "", sPostLoad = "", sPreLoad = "", sSealOne = "", sLoadType = "";
    private static int quality = 60;
    private static int density = 80;
    EditText edtDeliveryDocketNo, edtSeal1, edtSeal2, edtRemark, edtAddRemark;
    private URI mImageUri;
    File photo;
    private String pictureImagePath = "";
    final int MY_REQUEST_CODE = 1888;
    final int MY_REQUEST_CODE_STORAGE = 142;
    Uri currentImageUri;
    int nCamerraaaIntentForRequest = 0;
    private static final int WRITE_PERMISSION = 0x01;

    public static String resizedBitmapEncodeToBase64(Bitmap image) {
        System.gc();
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[0];
        try {
            immagex.setDensity(density);
            immagex.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            b = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docket_details);
        context = DocketDetailsActivity.this;
        getIntentData();

        setToolBar();
        if (Build.VERSION.SDK_INT >= 23) {
            requestWritePermission();
        }

        intializeUi();
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestWritePermission() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERMISSION);
        }
    }

    public void intializeUi() {
        tvDocketNo = (CustomTextView) findViewById(R.id.docket_detail_tv_doketno);
        tvDocketNo1 = (CustomTextView) findViewById(R.id.docket_detail_tv_doketno1);
        tvDocketNo1.setText("" + sDocketNo);
        tvTruckNo = (CustomTextView) findViewById(R.id.docket_detail_tv_truckno);
        tvTruckNo1 = (CustomTextView) findViewById(R.id.docket_detail_tv_truckno1);
        tvTruckNo1.setText("" + sTruckNo);
        tvDriverName = (CustomTextView) findViewById(R.id.docket_detail_tv_drivername);
        tvDriverName1 = (CustomTextView) findViewById(R.id.docket_detail_tv_drivername1);
        tvDriverName1.setText("" + sDriverName);
        tvCustomerName = (CustomTextView) findViewById(R.id.docket_detail_tv_customername);
        tvCustomerName1 = (CustomTextView) findViewById(R.id.docket_detail_tv_customername1);
        tvCustomerName1.setText("" + sCustomerName);
        tvpostload = (CustomTextView) findViewById(R.id.docket_detail_tv_post_load_open_door);
        tvpostloadcloseddoor = (CustomTextView) findViewById(R.id.docket_detail_tv_post_load_closed_door);

        tvSealOne = (CustomTextView) findViewById(R.id.docketdetail_tv_seal_one_num);
        tvSealTwo = (CustomTextView) findViewById(R.id.docketdetail_tv_seal_two_num);
        tvdeldocno = (CustomTextView) findViewById(R.id.docket_details_tv_deliverydocno);

        btnPreLoad = (CustomButton) findViewById(R.id.capture_btn1);
        btnPostLoad = (CustomButton) findViewById(R.id.capture_btn2);
        btnSealOne = (CustomButton) findViewById(R.id.capture_btn3);
        btnSubmit = (CustomButton) findViewById(R.id.submit_btn5);
        btnupload1 = (CustomButton) findViewById(R.id.docket_Detail_btn_preloadupload1);

        btnupload1 = (CustomButton) findViewById(R.id.docket_Detail_btn_preloadupload1);
        btnclickagain1 = (CustomButton) findViewById(R.id.docket_Detail_btn_preloadclickagain1);
        btnclickagain2 = (CustomButton) findViewById(R.id.docket_Detail_btn_post_load_open_door_clickagain2);
        btnclickagain3 = (CustomButton) findViewById(R.id.docket_Detail_btn_post_load_closed_door_clickagain3);


        llPreLoad = (LinearLayout) findViewById(R.id.content_doket_ll_preload);
        llPostLoad = (LinearLayout) findViewById(R.id.content_doket_ll_post_load_open_door);
        llSealOne = (LinearLayout) findViewById(R.id.content_doket_ll_post_load_closed_door);
        edtDeliveryDocketNo = (EditText) findViewById(R.id.docket_detail_edt_deliverydocno);
        edtSeal1 = (EditText) findViewById(R.id.docket_detail_edt_seal1_num);
        edtSeal2 = (EditText) findViewById(R.id.docket_detail_edt_seal2_num);
        edtRemark = (EditText) findViewById(R.id.docket_detail_edt_remarksdocno);
        edtAddRemark = (EditText) findViewById(R.id.docket_detail_edt_remarkadd);

        edtRemark.setText(sRemark);
        edtRemark.setFocusable(false);
        edtRemark.setFocusableInTouchMode(false);


        if (sLoadType.equals("Full Truck Load")) {
            tvpostload.setText("Post Load Open Door *");
            tvpostloadcloseddoor.setText("Post Load Closed Door *");
            tvSealOne.setText("Seal 1 *");
            tvSealTwo.setText("Seal 2 *");
        }
        ivPreLoadCapture = (ImageView) findViewById(R.id.doketdetail_iv_capture);
        ivPostLoadCapture = (ImageView) findViewById(R.id.doketdetail_iv_capture2);
        ivSealOneCaptured = (ImageView) findViewById(R.id.doketdetail_iv_capture3);

        if (sFlag.equals("recent")) {
            getRecentImage(sPreLoad, ivPreLoadCapture, llPreLoad, btnPreLoad, btnclickagain1);
            getRecentImage(sPostLoad, ivPostLoadCapture, llPostLoad, btnPostLoad, btnclickagain2);
            getRecentImage(sSealOne, ivSealOneCaptured, llSealOne, btnSealOne, btnclickagain3);
        }

        btnPreLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capturedClicked(btnPreLoad, 1);
            }
        });

        btnupload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePreUpload();
            }
        });
        btnPostLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capturedClicked(btnPostLoad, 2);
            }
        });
        btnSealOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capturedClicked(btnSealOne, 3);
            }
        });


        btnclickagain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capturedClicked(btnPreLoad, 1);

            }
        });

        btnclickagain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capturedClicked(btnPreLoad, 2);
            }
        });

        btnclickagain3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capturedClicked(btnPreLoad, 3);
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (sLoadType.equals("Full Truck Load")) {
                        if (validateData()) {
                            if (BU.isConnectingToInternet(context)) {
                                if (llPostLoad.getVisibility() != View.VISIBLE || llSealOne.getVisibility() != View.VISIBLE) {
                                    setPostDialog();

                                } else {
                                    setDialog();

                                }
                            }
                        }
                    } else {
                        Log.e("elsee", "elsee");
                        setDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        if (sFlag.equals("active")) {
            setOldPreImage();
        }
    }

    private void setOldPreImage() {
        String encodedpreImage = ACU.MySP.getPreImage(getApplicationContext(), "pre_image" + sDocketNo, "data");
        if (!encodedpreImage.equals("data")) {
            byte[] decodedString = Base64.decode(encodedpreImage, Base64.DEFAULT);
            bitmap1 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            ivPreLoadCapture.setImageBitmap(bitmap1);
            if (!docket_status.equals("LOADED") && !docket_status.equals("DEL_DOC")) {
                btnPreLoad.setVisibility(View.GONE);
                llPreLoad.setVisibility(View.VISIBLE);
            } else {
                btnPreLoad.setVisibility(View.GONE);
                llPreLoad.setVisibility(View.VISIBLE);
                btnupload1.setVisibility(View.GONE);
                btnclickagain1.setVisibility(View.GONE);
                btnSubmit.setVisibility(View.GONE);
                if (docket_status.equals("DEL_DOC")) {
                    tvdeldocno.setVisibility(View.VISIBLE);
                    String doc_no = ACU.MySP.getDeliveryDocketNumber(getApplicationContext(), "docket_no" + sDocketNo, "");
                    tvdeldocno.setText("Delivery Docket Number : " + doc_no);
                } else {
                    tvdeldocno.setVisibility(View.GONE);
                }
                edtDeliveryDocketNo.setVisibility(View.GONE);
            }

        }
        String encodedpostImage = ACU.MySP.getPostImage(getApplicationContext(), "post_image" + sDocketNo, "data");
        if (!encodedpostImage.equals("data")) {
            byte[] decodedString = Base64.decode(encodedpostImage, Base64.DEFAULT);
            bitmap2 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            ivPostLoadCapture.setImageBitmap(bitmap2);
            if (!docket_status.equals("LOADED") && !docket_status.equals("DEL_DOC")) {
                btnPostLoad.setVisibility(View.GONE);
                llPostLoad.setVisibility(View.VISIBLE);
            } else {
                btnPostLoad.setVisibility(View.GONE);
                llPostLoad.setVisibility(View.VISIBLE);
                btnclickagain2.setVisibility(View.GONE);
            }
        }

        String encodedseal1Image = ACU.MySP.getSeal1Image(getApplicationContext(), "seal1_image" + sDocketNo, "data");
        if (!encodedseal1Image.equals("data")) {
            byte[] decodedString = Base64.decode(encodedseal1Image, Base64.DEFAULT);
            bitmap3 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            ivSealOneCaptured.setImageBitmap(bitmap3);
            if (!docket_status.equals("LOADED") && !docket_status.equals("DEL_DOC")) {
                btnSealOne.setVisibility(View.GONE);
                llSealOne.setVisibility(View.VISIBLE);
            } else {
                btnSealOne.setVisibility(View.GONE);
                llSealOne.setVisibility(View.VISIBLE);
                btnclickagain3.setVisibility(View.GONE);
            }
        }
    }


    //TODO Method of Custom CustomSignIn
    private boolean validateData() {
        if (VU.isEmpty(edtSeal1)) {
            edtSeal1.requestFocus();
            edtSeal1.setError(getResources().getString(R.string.sealone_text));
            return false;
        } else if (VU.isEmpty(edtSeal2)) {
            edtSeal2.requestFocus();
            edtSeal2.setError(getResources().getString(R.string.sealtwo_text));
            return false;
        }
        return true;
    }

    public void getRecentImage(String sImagePath, ImageView ivImage, LinearLayout llLayout, Button btnCaptured, Button clickAgain) {

        try {
            Log.e("sImagePath:->", sImagePath);
            tvpostload.setText("Post Load Open Door");
            tvpostloadcloseddoor.setText("Post Load Closed Door");
            tvSealOne.setText("Seal 1");
            tvSealTwo.setText("Seal 2");
            edtSeal1.setText(sSealOneNo);
            edtSeal1.setFocusable(false);
            edtSeal1.setFocusableInTouchMode(false);

            edtSeal2.setText(sSealTwoNo);
            edtSeal2.setFocusable(false);
            edtSeal2.setFocusableInTouchMode(false);

            edtAddRemark.setText(sAddRemark);
            edtAddRemark.setFocusable(false);
            edtAddRemark.setFocusableInTouchMode(false);

            ivImage.setVisibility(View.VISIBLE);
            llLayout.setVisibility(View.VISIBLE);

            btnCaptured.setVisibility(View.GONE);
            IU.ImageLoaderWith(context, sImagePath, ivImage, R.drawable.ic_noimage);
            btnSubmit.setVisibility(View.GONE);
            btnupload1.setVisibility(View.GONE);
            clickAgain.setVisibility(View.GONE);
            edtDeliveryDocketNo.setText(sDelDocNo);
            edtDeliveryDocketNo.setFocusable(false);
            edtDeliveryDocketNo.setFocusableInTouchMode(false);
            if (!sDelDocNo.equals("")) {
                tvdeldocno.setVisibility(View.VISIBLE);
                edtDeliveryDocketNo.setVisibility(View.VISIBLE);
            } else {
                edtDeliveryDocketNo.setVisibility(View.GONE);
                tvdeldocno.setVisibility(View.GONE);
            }
            tvRemark.setText("Remark");
            edtRemark.setText(sRemark);
            edtRemark.setFocusable(false);
            edtRemark.setFocusableInTouchMode(false);


        } catch (Exception e) {

        }
    }


    public void setToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);             //For setting tool bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setPostDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DocketDetailsActivity.this);
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Post Load Open Door* And Post Load Closed Door* Photos are Mandatory");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }


    public void setDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DocketDetailsActivity.this);
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("You will not be able to submit/edit the photos after submitting");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                imagePostUpload();
            }
        });


        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You clicked on CANCEL", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    public void capturedClicked(Button clickBtn, int nCameraRequest) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        nCamerraaaIntentForRequest = nCameraRequest;
        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {Manifest.permission.CAMERA};
            if (!hasPermissions(context, PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) context, PERMISSIONS, MY_REQUEST_CODE);
            } else {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = timeStamp + ".jpg";
               /* File storageDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM);*/

                File storageDir = new File(getCacheDir(), "Bericap");

                pictureImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;
                File file = new File(pictureImagePath);

                currentImageUri = Uri.fromFile(file);

                Intent intentPicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intentPicture.putExtra(MediaStore.EXTRA_OUTPUT, currentImageUri); // set the image file name
                // start the image capture Intent
                startActivityForResult(intentPicture, nCameraRequest);  // 1 for REQUEST_CAMERA and 2 for REQUEST_CAMERA_ATT
                //open camera
            }
        } else {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = timeStamp + ".jpg";
            /*File storageDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM);*/

            File storageDir = new File(getCacheDir(), "Bericap");
            //deleteRecursive(storageDir);
            pictureImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;
            File file = new File(pictureImagePath);
            //   Uri outputFileUri = Uri.fromFile(file);
           /* currentImageUri = FileProvider.getUriForFile(DocketDetailsActivity.this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    file);*/
            currentImageUri = Uri.fromFile(file);
            Intent intentPicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intentPicture.putExtra(MediaStore.EXTRA_OUTPUT, currentImageUri); // set the image file name
            startActivityForResult(intentPicture, nCameraRequest);  // 1 for REQUEST_CAMERA and 2 for REQUEST_CAMERA_ATT
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_REQUEST_CODE: {
                Log.e("grantResults[0]", "" + grantResults[0]);
                Log.e("grantResults[0]", "" + grantResults.length);
                Log.e("PackageManager", "" + PackageManager.PERMISSION_GRANTED);
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.e("REqqq_iff", "REqqq_iff");
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String imageFileName = timeStamp + ".jpg";
                    File storageDir = Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DCIM);
                    // deleteRecursive(storageDir);
                    pictureImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;
                    File file = new File(pictureImagePath);
                    currentImageUri = Uri.fromFile(file);
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, currentImageUri);
                    startActivityForResult(cameraIntent, nCamerraaaIntentForRequest);

                }
            }
        }
    }


    private void getIntentData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            try {
                sFlag = extras.getString("flag");
                sDocketNo = extras.getString("docket_no");
                sDocketId = extras.getString("docket_id");
                sTruckNo = extras.getString("truck_no");
                sDriverName = extras.getString("driver_name");
                sCustomerName = extras.getString("customer_name");
                docket_status = extras.getString("docket_status");
                sPostLoad = extras.getString("POST_LOAD_IMG");
                sPreLoad = extras.getString("PRE_LOAD_IMG").trim();
                sSealOne = extras.getString("SEAL_1_IMG").trim();
                sDelDocNo = extras.getString("DEL_DOC_NO");
                sLoadType = extras.getString("LOAD_TYP");
                sSealOneNo = extras.getString("SEAL_1_NO");
                sSealTwoNo = extras.getString("SEAL_2_NO");
                sRemark = extras.getString("REMARKS_OVERALL");
                Log.e("sRemark", sRemark);
                if (sRemark.contains("@")) {
                    String sep[] = sRemark.split("@");
                    sRemark = sep[0];
                    sAddRemark = sep[1];
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {                              //For setting back button on tool bar
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                try {
                    File imgFile = new File(pictureImagePath);

                    if (imgFile.exists()) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 8;
                        //bitmap2 = BitmapFactory.decodeFile(imgFile.getAbsolutePath(),options);
                        bitmap1 = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);
                        ivPreLoadCapture.setImageBitmap(bitmap1);

                    }
                    btnPreLoad.setVisibility(View.GONE);
                    llPreLoad.setVisibility(View.VISIBLE);
                    LTU.TIS(context, "DeliverActivity", "Image set successfully");

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] bytes = baos.toByteArray();
                    String encodedImage = Base64.encodeToString(bytes, Base64.DEFAULT);

                    ACU.MySP.setPreImage(getApplicationContext(), "pre_image" + sDocketNo, encodedImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                LTU.TIS(context, "DeliverActivity", "Image Capture Cancel");
            }
        } else if (requestCode == 2) {
            try {
                if (resultCode == RESULT_OK) {
                    File imgFile = new File(pictureImagePath);
                    if (imgFile.exists()) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 8;
                        bitmap2 = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);
                        ivPostLoadCapture.setImageBitmap(bitmap2);
                    }
                    btnPostLoad.setVisibility(View.GONE);
                    llPostLoad.setVisibility(View.VISIBLE);
                    LTU.TIS(context, "DeliverActivity", "Image set successfully");

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] bytes = baos.toByteArray();
                    String encodedPostImage = Base64.encodeToString(bytes, Base64.DEFAULT);
                    ACU.MySP.setPostImage(getApplicationContext(), "post_image" + sDocketNo, encodedPostImage);
                } else {

                    LTU.TIS(context, "DeliverActivity", "Image Capture Cancel");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (requestCode == 3) {
            try {
                if (resultCode == RESULT_OK) {
                    File imgFile = new File(pictureImagePath);
                    if (imgFile.exists()) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 8;
                        bitmap3 = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);
                        Log.e("captureImageHeightWidth", bitmap3.getWidth() + "-" + bitmap3.getHeight());
                        ivSealOneCaptured.setImageBitmap(bitmap3);
                    }
                    btnSealOne.setVisibility(View.GONE);
                    llSealOne.setVisibility(View.VISIBLE);
                    LTU.TIS(context, "DeliverActivity", "Image set successfully");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap3.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] bytes = baos.toByteArray();
                    String encodedPostImage = Base64.encodeToString(bytes, Base64.DEFAULT);
                    ACU.MySP.setSeal1Image(getApplicationContext(), "seal1_image" + sDocketNo, encodedPostImage);
                } else {

                    LTU.TIS(context, "DeliverActivity", "Image Capture Cancel");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {

        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            Log.e("width", "" + width);
            height = (int) (width / bitmapRatio);
            Log.e("height", "" + height);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private void imagePreUpload() {

        final List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("SOURCE", "mobile"));
        params.add(new BasicNameValuePair("DOCKET_ID", sDocketId));
        //  params.add(new BasicNameValuePair("PRE_LOAD_IMG", resizedBitmapEncodeToBase64(bitmap1)));
        if (bitmap1 != null) {
            params.add(new BasicNameValuePair("PRE_LOAD_IMG", resizedBitmapEncodeToBase64(getResizedBitmap(bitmap1, 1200))));
        } else {
            params.add(new BasicNameValuePair("PRE_LOAD_IMG", ""));
        }

        params.add(new BasicNameValuePair("UPDATED_BY", ACU.MySP.getFromSP(context, ACU.MySP.USER_ID, "")));
        params.add(new BasicNameValuePair("APPLICATION_ID", ACU.MySP.getFromSP(context, ACU.MySP.APPLICATION_ID, "")));


        String url = Constants.SERVER_URL + Constants.IMAGE_PRELOAD;
        uploadData(url, params, "post", Constants.IMAGE_PRELOAD, "Y");
    }

    private void imagePostUpload() {
        final List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("SOURCE", "mobile"));
        params.add(new BasicNameValuePair("DOCKET_ID", sDocketId));
        params.add(new BasicNameValuePair("DEL_DOC_NO", edtDeliveryDocketNo.getText().toString().trim()));
        // params.add(new BasicNameValuePair("POST_LOAD_IMG", resizedBitmapEncodeToBase64(bitmap2)));
        if (bitmap2 != null) {
            params.add(new BasicNameValuePair("POST_LOAD_IMG", resizedBitmapEncodeToBase64(getResizedBitmap(bitmap2, 1200))));
        } else {
            params.add(new BasicNameValuePair("POST_LOAD_IMG", ""));
        }
        if (bitmap3 != null) {
            params.add(new BasicNameValuePair("SEAL_1_IMG", resizedBitmapEncodeToBase64(getResizedBitmap(bitmap3, 1200))));
        } else {
            params.add(new BasicNameValuePair("SEAL_1_IMG", ""));
        }

        //params.add(new BasicNameValuePair("SEAL_1_IMG", resizedBitmapEncodeToBase64(bitmap3)));
        params.add(new BasicNameValuePair("UPDATED_BY", ACU.MySP.getFromSP(context, ACU.MySP.USER_ID, "")));
        params.add(new BasicNameValuePair("APPLICATION_ID", ACU.MySP.getFromSP(context, ACU.MySP.APPLICATION_ID, "")));
        params.add(new BasicNameValuePair("SEAL_1_NO", edtSeal1.getText().toString().trim()));
        params.add(new BasicNameValuePair("SEAL_2_NO", edtSeal2.getText().toString().trim()));
        params.add(new BasicNameValuePair("REMARKS_OVERALL", edtRemark.getText().toString().trim() + "@" + edtAddRemark.getText().toString().trim()));
        String url = Constants.SERVER_URL + Constants.IMAGE_POSTLOAD;
        uploadData(url, params, "post", Constants.IMAGE_POSTLOAD, "Y");
    }

    public void uploadData(String url, List<NameValuePair> params, String method, String function, String isProgressbar) {
        if (Connectivity.isConnectedFast(context)) {
            new MyAsyncTask(this, context, method, url, null, params, function, isProgressbar).execute();
        } else {
            LTU.TEL(context, MU.NO_INERTNET_CONNECTION, MU.NO_INERTNET_CONNECTION);
        }
    }



    @Override
    public void processFinish(String result, String functName) {
        if (functName.equals(Constants.IMAGE_PRELOAD)) {
            Log.e("result", result);
            try {
                if (result != null && !result.equals("fail")) {
                    LTU.TIS(context, TAG, "PreLoad image Upload Successfully");
                } else {
                    LTU.TIS(context, TAG, "Error to Data Upload");
                }
            } catch (Exception e) {
                e.printStackTrace();
                LTU.TIS(context, TAG, "Error to Data Upload");
            }
        } else {
            Log.e("result post load", result);
            try {
                if (result != null && !result.equals("fail")) {
                    ACU.MySP.setDeliveryDocketNumber(getApplicationContext(), "docket_no" + sDocketNo, edtDeliveryDocketNo.getText().toString().trim());
                    LTU.TIS(context, TAG, "Image PostLoad Upload Successfully");
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    LTU.TIS(context, TAG, "Error to Data Upload");
                }
            } catch (Exception e) {
                e.printStackTrace();
                LTU.TIS(context, TAG, "Error to Data Upload");
            }
        }
    }

    public static void deleteRecursive(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }
        fileOrDirectory.delete();
    }
}
