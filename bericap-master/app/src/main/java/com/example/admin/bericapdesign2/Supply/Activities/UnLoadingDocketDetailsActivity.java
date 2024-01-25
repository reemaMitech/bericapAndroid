package com.example.admin.bericapdesign2.Supply.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.admin.bericapdesign2.AsyncTask.UploadAsyncTask;
import com.example.admin.bericapdesign2.CustomClasses.CustomButton;
import com.example.admin.bericapdesign2.CustomClasses.CustomTextView;
import com.example.admin.bericapdesign2.R;
import com.example.admin.bericapdesign2.Util.ACU;
import com.example.admin.bericapdesign2.Util.Constants;
import com.example.admin.bericapdesign2.Util.IU;
import com.example.admin.bericapdesign2.Util.LTU;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static pub.devrel.easypermissions.EasyPermissions.hasPermissions;

public class UnLoadingDocketDetailsActivity extends AppCompatActivity {

    String TAG = "UnLoadingDocketDetailsActivity";
    Toolbar toolbar;
    Context context;

    CustomTextView docket_ul_docketno, docket_ul_truckno, docket_detail_tv_drivername1, docket_ul_vendorname,
            docket_ul_status;

    CustomButton capture_btn1, capture_btn2, btn_postload_clickagain, btn_preload_clickagain;

    LinearLayout content_doket_ll_preload, content_doket_ll_post_load_open_door;
    ImageView iv_preload, iv_postload;

    EditText edt_security_remarks, edt_unloader_remarks, edt_grn, edt_ivoiceno;

    CustomButton submit_btn;

    String sFlag = "", sSealOneNo = "", sSealTwoNo = "", sRemark = "", sAddRemark = "", sDocketNo = "", sTruckNo = "", sDriverName = "", sCustomerName = "", sDocketId = "", docket_status = "", sDelDocNo = "", sPostLoad = "", sPreLoad = "", sSealOne = "", sLoadType = "";
    String GRN_NO = "", INVOICE_NO = "", sUnLoadingStatus = "";
    Bitmap bitmap1, bitmap2;

    private String pictureImagePath = "", preLoadImagePath = "", postLoadImgPath = "";
    final int MY_REQUEST_CODE = 1888;
    Uri currentImageUri;
    int nCamerraaaIntentForRequest = 0;
    private static final int WRITE_PERMISSION = 0x01;

    private static int quality = 60;
    private static int density = 80;

    Spinner spn_status;
    ArrayList statusList;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unloading_details);
        // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        context = UnLoadingDocketDetailsActivity.this;
        if (Build.VERSION.SDK_INT >= 23) {
            requestWritePermission();
        }

        setToolBar();
        getIntentData();
        intializeUi();
        hideKeyboard();
        /*getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );*/
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(context);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


    public void intializeUi() {
        statusList = new ArrayList();
        docket_ul_docketno = (CustomTextView) findViewById(R.id.docket_ul_docketno);
        docket_ul_truckno = (CustomTextView) findViewById(R.id.docket_ul_truckno);
        docket_detail_tv_drivername1 = (CustomTextView) findViewById(R.id.docket_detail_tv_drivername1);
        docket_ul_vendorname = (CustomTextView) findViewById(R.id.docket_ul_vendorname);
        docket_ul_status = (CustomTextView) findViewById(R.id.docket_ul_status);

        capture_btn1 = (CustomButton) findViewById(R.id.capture_btn1);
        capture_btn2 = (CustomButton) findViewById(R.id.capture_btn2);

        btn_preload_clickagain = (CustomButton) findViewById(R.id.btn_preload_clickagain);
        btn_postload_clickagain = (CustomButton) findViewById(R.id.btn_postload_clickagain);

        content_doket_ll_preload = (LinearLayout) findViewById(R.id.content_doket_ll_preload);
        content_doket_ll_post_load_open_door = (LinearLayout) findViewById(R.id.content_doket_ll_post_load_open_door);

        iv_preload = (ImageView) findViewById(R.id.iv_preload);
        iv_postload = (ImageView) findViewById(R.id.iv_postload);

        edt_security_remarks = (EditText) findViewById(R.id.edt_security_remarks);
        edt_unloader_remarks = (EditText) findViewById(R.id.edt_unloader_remarks);
        edt_grn = (EditText) findViewById(R.id.docket_detail_edt_grn);
        edt_ivoiceno = (EditText) findViewById(R.id.edt_ivoiceno);

        spn_status = (Spinner) findViewById(R.id.spn_status);
        statusList.add("Select");
        statusList.add("Accepted");
        statusList.add("Partially-rejected");
        statusList.add("Fully-rejected");

        if (statusList.size() > 0) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
                    android.R.layout.simple_spinner_item, statusList);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spn_status.setAdapter(dataAdapter);
        }

        submit_btn = (CustomButton) findViewById(R.id.submit_btn);

        setButtonOnClicked();
        setOldData();
    }

    private void setButtonOnClicked() {
        capture_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capturedClicked(1);
            }
        });
        capture_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capturedClicked(2);
            }
        });
        btn_preload_clickagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capturedClicked(1);
            }
        });
        btn_postload_clickagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capturedClicked(2);
            }
        });
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
        spn_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i != 0) {
                    sUnLoadingStatus = adapterView.getItemAtPosition(i).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void setOldData() {
        docket_ul_docketno.setText(sDocketNo);
        docket_ul_truckno.setText(sTruckNo);
        docket_detail_tv_drivername1.setText(sDriverName);
        docket_ul_vendorname.setText(sCustomerName);
        docket_ul_status.setText(docket_status);
        edt_security_remarks.setText(sAddRemark);
        edt_grn.setText(GRN_NO);
        edt_ivoiceno.setText(INVOICE_NO);
        edt_unloader_remarks.setText(sRemark);

        int nSpinnerPosition1 = 0;
        for (int i = 0; i < statusList.size(); i++) {
            if (statusList.get(i).toString().trim().equals(sUnLoadingStatus.trim())) {
                nSpinnerPosition1 = i;
            }
        }
        spn_status.setSelection(nSpinnerPosition1);

        if (sFlag.equals(Constants.ACTVIE)) {
            setOldPreImage();
            submit_btn.setVisibility(View.VISIBLE);
            edt_security_remarks.setEnabled(true);
            edt_unloader_remarks.setEnabled(true);
            edt_ivoiceno.setEnabled(true);
            edt_grn.setEnabled(true);


            if (sPreLoad.contains("upload_incoming_docket_image")) {
                capture_btn1.setVisibility(View.GONE);
                content_doket_ll_preload.setVisibility(View.VISIBLE);
                IU.ImageLoaderWith(context, sPreLoad, iv_preload, R.drawable.ic_noimage);
            }

            if (sPostLoad.trim().contains("upload_incoming_docket_image")) {
                capture_btn2.setVisibility(View.GONE);
                content_doket_ll_post_load_open_door.setVisibility(View.VISIBLE);
                IU.ImageLoaderWith(context, sPostLoad, iv_postload, R.drawable.ic_noimage);
            }
        } else {
            submit_btn.setVisibility(View.GONE);

            edt_security_remarks.setEnabled(false);
            edt_unloader_remarks.setEnabled(false);
            edt_ivoiceno.setEnabled(false);
            edt_grn.setEnabled(false);
            btn_preload_clickagain.setVisibility(View.GONE);
            btn_postload_clickagain.setVisibility(View.GONE);

            if (sPreLoad.contains("upload_incoming_docket_image")) {
                capture_btn1.setVisibility(View.GONE);
                content_doket_ll_preload.setVisibility(View.VISIBLE);
                IU.ImageLoaderWith(context, sPreLoad, iv_preload, R.drawable.ic_noimage);
            } else {
                capture_btn1.setVisibility(View.GONE);
                iv_preload.setVisibility(View.VISIBLE);
                content_doket_ll_preload.setVisibility(View.VISIBLE);
            }
            if (sPostLoad.trim().contains("upload_incoming_docket_image")) {
                capture_btn2.setVisibility(View.GONE);
                content_doket_ll_post_load_open_door.setVisibility(View.VISIBLE);
                IU.ImageLoaderWith(context, sPostLoad, iv_postload, R.drawable.ic_noimage);
            } else {
                capture_btn2.setVisibility(View.GONE);
                iv_postload.setVisibility(View.VISIBLE);
                content_doket_ll_post_load_open_door.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);             //For setting tool bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                sPostLoad = extras.getString("POST_LOAD_IMG").trim();
                sPreLoad = extras.getString("PRE_LOAD_IMG").trim();
                sSealOne = extras.getString("SEAL_1_IMG").trim();
                sDelDocNo = extras.getString("DEL_DOC_NO");
                sLoadType = extras.getString("LOAD_TYP");
                sSealOneNo = extras.getString("SEAL_1_NO");
                sSealTwoNo = extras.getString("SEAL_2_NO");
                sRemark = extras.getString("REMARKS_OVERALL");
                GRN_NO = extras.getString("GRN_NO");
                INVOICE_NO = extras.getString("INVOICE_NO");
                sUnLoadingStatus = extras.getString("UNLOADING_STATUS");
                if (sRemark.contains("@")) {
                    String sep[] = sRemark.split("@");
                    sRemark = sep[0];
                    sAddRemark = sep[1];
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setOldPreImage() {
        String encodedpreImage = ACU.MySP.getPreImage(getApplicationContext(), "pre_image" + sDocketNo, "data");
        if (!encodedpreImage.equals("data")) {
            byte[] decodedString = Base64.decode(encodedpreImage, Base64.DEFAULT);
            bitmap1 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            iv_preload.setImageBitmap(bitmap1);
            if (!docket_status.equals("LOADED") && !docket_status.equals("DEL_DOC")) {
                capture_btn1.setVisibility(View.GONE);
                content_doket_ll_preload.setVisibility(View.VISIBLE);
            } else {
                capture_btn1.setVisibility(View.GONE);
                content_doket_ll_preload.setVisibility(View.VISIBLE);

                submit_btn.setVisibility(View.GONE);
            }

        }
        String encodedpostImage = ACU.MySP.getPostImage(getApplicationContext(), "post_image" + sDocketNo, "data");
        if (!encodedpostImage.equals("data")) {
            byte[] decodedString = Base64.decode(encodedpostImage, Base64.DEFAULT);
            bitmap2 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            iv_postload.setImageBitmap(bitmap2);
            if (!docket_status.equals("LOADED") && !docket_status.equals("DEL_DOC")) {
                capture_btn2.setVisibility(View.GONE);
                content_doket_ll_post_load_open_door.setVisibility(View.VISIBLE);
            } else {
                capture_btn2.setVisibility(View.GONE);
                content_doket_ll_post_load_open_door.setVisibility(View.VISIBLE);
            }
        }
    }

    public void getRecentImage(String sImagePath, ImageView ivImage, LinearLayout llLayout) {
        Log.e("ImagePAth:->", sImagePath);
        try {
            capture_btn1.setVisibility(View.GONE);
            capture_btn2.setVisibility(View.GONE);

            ivImage.setVisibility(View.VISIBLE);
            llLayout.setVisibility(View.VISIBLE);


            IU.ImageLoaderWith(context, sImagePath, ivImage, R.drawable.ic_noimage);
            //  submit_btn.setVisibility(View.GONE);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void capturedClicked(int nCameraRequest) {
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
                File storageDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM);


                // deleteRecursive(storageDir);
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
            File storageDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM);
            pictureImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;
            File file = new File(pictureImagePath);

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

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestWritePermission() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERMISSION);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                try {
                    File imgFile = new File(pictureImagePath);
                    preLoadImagePath = pictureImagePath;
                    if (imgFile.exists()) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 8;
                        //bitmap2 = BitmapFactory.decodeFile(imgFile.getAbsolutePath(),options);
                        bitmap1 = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);
                        iv_preload.setImageBitmap(bitmap1);

                    }
                    capture_btn1.setVisibility(View.GONE);
                    content_doket_ll_preload.setVisibility(View.VISIBLE);
                    LTU.TIS(context, "DeliverActivity", "Image set successfully");

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] bytes = baos.toByteArray();
                    String encodedImage = Base64.encodeToString(bytes, Base64.DEFAULT);
                    //preLoadImagePath=encodedImage;
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
                    postLoadImgPath = pictureImagePath;
                    if (imgFile.exists()) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 8;
                        bitmap2 = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);
                        iv_postload.setImageBitmap(bitmap2);
                    }
                    capture_btn2.setVisibility(View.GONE);
                    content_doket_ll_post_load_open_door.setVisibility(View.VISIBLE);
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

        }
    }

    private void saveData() {
      /*  final List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("SOURCE", "mobile"));
        params.add(new BasicNameValuePair("INCOMING_DOCKET_ID", sDocketId));
        params.add(new BasicNameValuePair("INCOMING_DOCKET_NO", docket_ul_docketno.getText().toString().trim()));
        params.add(new BasicNameValuePair("GRN_NO", edt_grn.getText().toString().trim()));
        params.add(new BasicNameValuePair("INVOICE_NO", edt_ivoiceno.getText().toString().trim()));
        params.add(new BasicNameValuePair("PRE_LOAD_IMG", preLoadImagePath));
        params.add(new BasicNameValuePair("POST_LOAD_IMG", preLoadImagePath));;
        params.add(new BasicNameValuePair("UPDATED_BY", ACU.MySP.getFromSP(context, ACU.MySP.USER_ID, "")));
        params.add(new BasicNameValuePair("APPLICATION_ID", ACU.MySP.getFromSP(context, ACU.MySP.APPLICATION_ID, "")));
        params.add(new BasicNameValuePair("REMARKS_OVERALL", edt_security_remarks.getText().toString().trim() + "@" + edt_unloader_remarks.getText().toString().trim()));
*/
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SOURCE", "mobile");
            jsonObject.put("INCOMING_DOCKET_ID", sDocketId);

            jsonObject.put("INCOMING_DOCKET_NO", docket_ul_docketno.getText().toString().trim());
            jsonObject.put("GRN_NO", edt_grn.getText().toString().trim());
            jsonObject.put("INVOICE_NO", edt_ivoiceno.getText().toString().trim());
            jsonObject.put("PRE_LOAD_IMG", preLoadImagePath);
            jsonObject.put("POST_LOAD_IMG", postLoadImgPath);
            //jsonObject.put("PRE_LOAD_IMG", resizedBitmapEncodeToBase64(getResizedBitmap(bitmap1, 1200)));
            //jsonObject.put("POST_LOAD_IMG", resizedBitmapEncodeToBase64(getResizedBitmap(bitmap2, 1200)));

            jsonObject.put("UPDATED_BY", ACU.MySP.getFromSP(context, ACU.MySP.USER_ID, ""));
            jsonObject.put("APPLICATION_ID", ACU.MySP.getFromSP(context, ACU.MySP.APPLICATION_ID, ""));
            jsonObject.put("REMARKS_OVERALL", edt_unloader_remarks.getText().toString().trim() + "@" + edt_security_remarks.getText().toString().trim());
            jsonObject.put("UNLOADING_STATUS", sUnLoadingStatus.trim());
            saveMulitpart(jsonObject, "Y");
        } catch (Exception e) {

        }
    }

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

    private void saveMulitpart(JSONObject object, String sFlag) {
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        try {

            StringBody stringBody1 = new StringBody(object.getString("SOURCE"), ContentType.MULTIPART_FORM_DATA);
            StringBody stringBody2 = new StringBody(object.getString("INCOMING_DOCKET_NO"), ContentType.MULTIPART_FORM_DATA);
            StringBody stringBody3 = new StringBody(object.getString("APPLICATION_ID"), ContentType.MULTIPART_FORM_DATA);
            StringBody stringBody4 = new StringBody(object.getString("GRN_NO"), ContentType.MULTIPART_FORM_DATA);
            StringBody stringBody5 = new StringBody(object.getString("UPDATED_BY"), ContentType.MULTIPART_FORM_DATA);
            StringBody stringBody6 = new StringBody(object.getString("INVOICE_NO"), ContentType.MULTIPART_FORM_DATA);
            StringBody stringBody7 = new StringBody(object.getString("REMARKS_OVERALL"), ContentType.MULTIPART_FORM_DATA);
            StringBody stringBody8 = new StringBody(object.getString("INCOMING_DOCKET_ID"), ContentType.MULTIPART_FORM_DATA);
            StringBody stringBody11 = new StringBody(object.getString("UNLOADING_STATUS"), ContentType.MULTIPART_FORM_DATA);
            StringBody sSavePath = new StringBody(Constants.UPLOAD_INCOMING_DOCKET_IMAGE, ContentType.MULTIPART_FORM_DATA);
            StringBody sTableName = new StringBody(Constants.TBL_INCOMING_DOCKET, ContentType.MULTIPART_FORM_DATA);
            StringBody sImageBaseUrl = new StringBody(Constants.IMAGE_URL, ContentType.MULTIPART_FORM_DATA);


            if (!object.getString("PRE_LOAD_IMG").trim().equals("")) {
                File file = new File(object.getString("PRE_LOAD_IMG"));
                multipartEntityBuilder.addPart("PRE_LOAD_IMG", new FileBody(file));
            } else {
                StringBody stringBody9 = new StringBody("", ContentType.MULTIPART_FORM_DATA);

                multipartEntityBuilder.addPart("PRE_LOAD_IMG", stringBody9);
            }

            if (!object.getString("POST_LOAD_IMG").trim().equals("")) {
                File file = new File(object.getString("POST_LOAD_IMG"));
                multipartEntityBuilder.addPart("POST_LOAD_IMG", new FileBody(file));
            } else {
                StringBody stringBody10 = new StringBody("", ContentType.MULTIPART_FORM_DATA);

                multipartEntityBuilder.addPart("POST_LOAD_IMG", stringBody10);
            }

            multipartEntityBuilder.addPart("SOURCE", stringBody1);
            // multipartEntityBuilder.addPart("INCOMING_DOCKET_NO", stringBody2);
            multipartEntityBuilder.addPart("APPLICATION_ID", stringBody3);
            multipartEntityBuilder.addPart("GRN_NO", stringBody4);
            multipartEntityBuilder.addPart("UPDATED_BY", stringBody5);
            multipartEntityBuilder.addPart("INVOICE_NO", stringBody6);
            multipartEntityBuilder.addPart("REMARKS_OVERALL", stringBody7);
            multipartEntityBuilder.addPart("INCOMING_DOCKET_ID", stringBody8);
            multipartEntityBuilder.addPart("SAVE_PATH", sSavePath);
            multipartEntityBuilder.addPart("TABLE_NAME", sTableName);
            multipartEntityBuilder.addPart("BASE_URL", sImageBaseUrl);
            multipartEntityBuilder.addPart("UNLOADING_STATUS", stringBody11);

            String SERVER_PATH = Constants.SERVER_URL + Constants.UPLOAD_IMAGE_DOCS;
            UploadAsyncTask asyncTask = new UploadAsyncTask(context, sFlag, SERVER_PATH, multipartEntityBuilder);
            asyncTask.execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
