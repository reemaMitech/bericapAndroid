package com.ecs.offers.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.ecs.offers.Adapter.MyPaymentListAdapter;
import com.ecs.offers.Adapter.TopListedCardAdapter;
import com.ecs.offers.Adapter.TopListedPaymentAdapter;
import com.ecs.offers.CustomClasses.CustomButton;
import com.ecs.offers.R;
import com.ecs.offers.database.DataBaseConstants;
import com.ecs.offers.database.DataBaseHelper;
import com.ecs.offers.util.BU;
import com.ecs.offers.util.Constants;
import com.ecs.offers.util.LTU;
import com.ecs.offers.util.MU;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SelectPaymentOptionsActivity extends AppCompatActivity {
    RadioButton rbCreditCard, rbDebitCard, rbEWallet, rbNetBanking;
    View view;
    AlertDialog aDialog;
    LinearLayout llBottom;
    static LinearLayout llSelectCardType;
    Context context;
    static DataBaseHelper dh;
    ArrayList arrayList;
    static Cursor cursor;
    static Spinner spinnePaymentSelectCardType;
    Spinner spinnePaymentSelectBankName;
    GridView gvTopListedPayment, gvTopListedCard;
    ImageView imageCedit, imageDebit, imageEwallet,imageNetbanking;
    View dialoglayout;
    public static JSONObject jsonObject;
    public static JSONArray jsonArray;
    ListView lvMyPaymentList;
    TextView btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_payment_options);
        context = SelectPaymentOptionsActivity.this;
        dh = new DataBaseHelper(context);
        jsonObject = new JSONObject();
        jsonArray = new JSONArray();
        Initialize();

    }

    private void Initialize() {

        imageCedit = (ImageView) findViewById(R.id.image_credit);
        imageDebit = (ImageView) findViewById(R.id.image_debit);
        imageEwallet = (ImageView) findViewById(R.id.image_ewallet);
        imageNetbanking = (ImageView) findViewById(R.id.image_netbanking);

        rbCreditCard = (RadioButton) findViewById(R.id.rb_creditcard);
        lvMyPaymentList = (ListView) findViewById(R.id.my_payment_list);
        rbDebitCard = (RadioButton) findViewById(R.id.rb_debitcard);
        rbEWallet = (RadioButton) findViewById(R.id.rb_ewalletcard);
        rbNetBanking = (RadioButton) findViewById(R.id.rb_netbankingcard);

        btnNext = (TextView) findViewById(R.id.btn_next);

        setImageView();
        setRadioButtonView();
        setMyPaymentList();
        setNextButton();

    }

    private void setNextButton() {

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectPaymentOptionsActivity.this, OffersListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setRadioButtonView() {

        rbCreditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialgForCard("creditcard",1);
                try {
                    jsonObject.put("provider_name","Credit Card");
                    jsonObject.put("provider_id",1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                checkFalse(rbCreditCard, rbDebitCard, rbNetBanking, rbEWallet);
            }
        });

        rbDebitCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFalse(rbDebitCard, rbCreditCard, rbNetBanking, rbEWallet);
                try {
                    jsonObject.put("provider_name","Debit Card");
                    jsonObject.put("provider_id",2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                showDialgForCard("debitvard",2);
            }
        });

        rbEWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFalse(rbEWallet, rbDebitCard, rbCreditCard, rbNetBanking);
                try {
                    jsonObject.put("provider_name","E-Wallet");
                    jsonObject.put("provider_id",3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                showDialgForCard("ewallet",3);
            }
        });

        rbNetBanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFalse(rbNetBanking, rbDebitCard, rbCreditCard, rbEWallet);
                try {
                    jsonObject.put("provider_name","NetBanking");
                    jsonObject.put("provider_id",4);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                showDialgForCard("netbanking",4);
            }
        });

    }

    private void setImageView() {

        imageCedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialgForCard("creditcard",1);
                try {
                    jsonObject.put("provider_name","Credit Card");
                    jsonObject.put("provider_id",1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                checkFalse(rbCreditCard, rbDebitCard, rbNetBanking, rbEWallet);
            }
        });

        imageDebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFalse(rbDebitCard, rbCreditCard, rbNetBanking, rbEWallet);
                try {
                    jsonObject.put("provider_name","Debit Card");
                    jsonObject.put("provider_id",2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                showDialgForCard("debitvard",2);
            }
        });

        imageEwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFalse(rbEWallet, rbDebitCard, rbCreditCard, rbNetBanking);
                try {
                    jsonObject.put("provider_name","E-Wallet");
                    jsonObject.put("provider_id",3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                showDialgForCard("ewallet",3);
            }
        });

        imageNetbanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFalse(rbNetBanking, rbDebitCard, rbCreditCard, rbEWallet);
                try {
                    jsonObject.put("provider_name","NetBanking");
                    jsonObject.put("provider_id",4);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                showDialgForCard("netbanking",4);
            }
        });
    }

    public void setMyPaymentList() {
        JSONArray array = null;
        array = dh.getMyPaymentList();

        if(array!=null) {
            if (array.length() > 0) {
                lvMyPaymentList.setAdapter(new MyPaymentListAdapter(context, array));
                lvMyPaymentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> adapterView, View arg1, int position,
                                            long arg3) {

                    }
                });
            }
        }
    }



    private void checkFalse(RadioButton selectBtn, RadioButton rb1, RadioButton rb2, RadioButton rb3) {
        selectBtn.setChecked(true);
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
    }


    private void showDialgForCard(final String sCard, final int payment_id) {
        try {

            LayoutInflater inflater = getLayoutInflater();
            dialoglayout = inflater.inflate(R.layout.popup_select_payment_option, null);

            llSelectCardType = (LinearLayout) dialoglayout.findViewById(R.id.ll_select_card_type);
            spinnePaymentSelectCardType = (Spinner) dialoglayout.findViewById(R.id.paymentoption_spinner_selectcardtype);
            spinnePaymentSelectBankName = (Spinner) dialoglayout.findViewById(R.id.paymentoption_spinner_selectbankname);
            gvTopListedPayment = (GridView) dialoglayout.findViewById(R.id.gv_top_listed_payment);
            gvTopListedCard = (GridView) dialoglayout.findViewById(R.id.gv_top_listed_card);
            Constants.gvTopListedCard = gvTopListedCard;

            setSpinnerPaymentBankName(sCard, payment_id);
            setTopListedPaymentAdapter(payment_id);

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(dialoglayout);

            //   aDialog.getWindow().setLayout(580, 700); //Controlling width and height.
            aDialog = builder.show();
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();

            lp.copyFrom(aDialog.getWindow().getAttributes());
            lp.width = 600;
            // lp.height = 800;
            lp.x = -170;
            lp.y = 100;
            aDialog.getWindow().setAttributes(lp);

            TextView btnAdd = (TextView) dialoglayout.findViewById(R.id.detailspay_option_btn_add);
            btnAdd.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {
                        boolean result = false;
                        Log.e("jsonObject",""+jsonObject);
                        Log.e("jsonArray",""+jsonArray);
                        if (validate()) {
                            if (!jsonObject.get("provider_name").equals("E-Wallet") && !jsonObject.get("provider_name").equals("NetBanking")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    result = dh.setPaymentList(jsonObject, jsonArray.get(i).toString());
                                }
                            } else {
                                result = dh.setPaymentList(jsonObject, "");
                            }

                            if (result) {
                                LTU.TOAST_L(context, MU.SUCCESS_UPLOAD);
                                //setMyPaymentList();
                                //aDialog.dismiss();
                                Intent intent = new Intent(SelectPaymentOptionsActivity.this, SelectPaymentOptionsActivity.class);
                                startActivity(intent);
                                //jsonArray = [];
                                //finish();
                            } else {
                                LTU.TOAST_L(context, MU.ERROR_UPLOAD);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSpinnerPaymentBankName(final String sCard, final int payment_id) {

        arrayList = new ArrayList();
        cursor = dh.getPaymentOptionData(DataBaseConstants.TableNames.TBL_PAYMENT_OPTION_PROVIDER, "N", payment_id);
        if(cursor != null)
            arrayList = getSelectedList(cursor, DataBaseConstants.TableNames.TBL_PAYMENT_OPTION_PROVIDER,"N");

        if (arrayList != null) {

            BU.SetAdapter(context, spinnePaymentSelectBankName, arrayList);

            spinnePaymentSelectBankName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if (position != 0) {
                        try {
                            jsonObject.put("bank_name", "" + spinnePaymentSelectBankName.getSelectedItem().toString());
                            setTopListedPaymentAdapter(payment_id);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(!sCard.equals("netbanking") && !sCard.equals("ewallet")) {
                            setCardLayout(context, payment_id);
                        }

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    private void setTopListedPaymentAdapter(int payment_id) {

        ArrayList arrayList1 = new ArrayList();
        cursor = dh.getPaymentOptionData(DataBaseConstants.TableNames.TBL_PAYMENT_OPTION_PROVIDER, "Y", payment_id);
        if(cursor != null)
            arrayList1 = getSelectedList(cursor, DataBaseConstants.TableNames.TBL_PAYMENT_OPTION_PROVIDER,"Y");

        if(arrayList1.size()>0) {

            gvTopListedPayment.setAdapter(new TopListedPaymentAdapter(context, arrayList1));
            gvTopListedPayment.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> adapterView, View arg1, int position,
                                        long arg3) {
                        /*if (position != 0)
                            jsonArray.put(gvTopListedPayment.getSelectedItem().toString());*/

                    //Log.e("data",""+jsonArray);

                }
            });
        }
    }

    private boolean validate() {
        if(!jsonObject.has("bank_name")){
            LTU.TOAST_L(context,MU.SELECT_BANK);
            return false;
        }else{
            return true;
        }

    }

    public  void setCardLayout(Context context, int payment_id) {

        llSelectCardType.setVisibility(View.VISIBLE);
        spinnePaymentSelectCardType.setVisibility(View.VISIBLE);

        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();

        cursor = dh.getPaymentOptionData(DataBaseConstants.TableNames.TBL_CARD_TYPE, "N", payment_id);
        //arrayList = null;
        if(cursor != null)
            arrayList2 = getSelectedList(cursor, DataBaseConstants.TableNames.TBL_CARD_TYPE, "N");

        if (arrayList2 != null) {

            BU.SetAdapter(context, spinnePaymentSelectCardType, arrayList2);

            spinnePaymentSelectCardType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if (position != 0) {
                        llSelectCardType.setVisibility(View.VISIBLE);
                        spinnePaymentSelectCardType.setVisibility(View.VISIBLE);
                        if (position != 0)
                            jsonArray.put(spinnePaymentSelectCardType.getSelectedItem().toString());
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        cursor = dh.getPaymentOptionData(DataBaseConstants.TableNames.TBL_CARD_TYPE, "Y", payment_id);
        if(cursor != null)
            arrayList3 = getSelectedList(cursor, DataBaseConstants.TableNames.TBL_CARD_TYPE, "Y");

        if(arrayList3.size()>0) {

            Constants.gvTopListedCard.setAdapter(new TopListedCardAdapter(context, arrayList3));
            Constants.gvTopListedCard.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> adapterView, View arg1, int position,
                                        long arg3) {

                }
            });
        }
    }


    private static ArrayList getSelectedList(Cursor cursor, String table_name, String flag) {

        ArrayList list = new ArrayList();
        if(flag.equals("N"))
            list .add("Select");

        while (cursor.moveToNext()){

            if(table_name.equals(DataBaseConstants.TableNames.TBL_CARD_TYPE))
                list.add(String.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblCardType.CARD_TYPE_NAME))));

            if(table_name.equals(DataBaseConstants.TableNames.TBL_PAYMENT_OPTION_PROVIDER))
                list.add(String.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblPaymentOptionProvider.PAYMENT_OPT_PROVIDER_NAME))));

        }

        if (list.size()>0)
            return list;

        return null;
    }

}
