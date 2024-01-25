package com.ecs.offers.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.ecs.offers.util.Constants;
import com.ecs.offers.util.DTU;
import com.ecs.offers.util.LTU;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Class Name        :  <b>DataBaseHelper.java<b/>
 * Purpose           : DataBaseHelper is class related of database operation.
 * Developed By      :  <b>@Nitin Malwadkar</b>
 * Created Date      :  <b>3.02.2017</b>
 * <p/>
 * Modified Reason   :  <b></b>
 * Modified By       :  <b></b>
 * Modified Date     :  <b></b>
 * <p/>
 */
public class DataBaseHelper {
    public static SQLiteDatabase db;
    public Context context;
    private Cursor cursor;
    private SQLiteHelper sqliteopenhelper;
    private JSONArray jArray;
    private JSONObject json_data;
    private boolean isExist = false;
    private String str_column_name;
    private String TAG = "DataBaseHelper";

    public DataBaseHelper(Context context) {
        this.context = context;
        sqliteopenhelper = new SQLiteHelper(context);
        db = sqliteopenhelper.getWritableDatabase();
        db = sqliteopenhelper.getReadableDatabase();
    }

    //Used for insert downloaded data
    public void insertDownloadedData(String table, String result) {
        // TODO Auto-generated method stub only to insert new data update existing  @ 23.04.16
        isExist = false;

        try {
            jArray = new JSONArray(result);
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        for (int i = 0; i < jArray.length(); i++) {
            isExist = false;
            try {
                json_data = jArray.getJSONObject(i);

                Cursor c;
                c = db.rawQuery("Select * from " + table + " where  phpid='" + json_data.getString("id") + "' ", null);
                int count = c.getColumnCount();
                if (c != null) {
                    if (c.moveToFirst()) {
                        isExist = true;
                        c.close();
                    } else {
                        c.close();
                    }
                } else {

                }
                if (!isExist) {
                    ContentValues conV = new ContentValues();
                    conV.put("phpid", json_data.getString("id"));
                    for (int k = 1; k < count - 1; k++) {
                        str_column_name = c.getColumnName(k);
                        if (!str_column_name.equals("position") && !str_column_name.equals("is_uploaded") && !str_column_name.equals("phpid")) {

                                conV.put("" + str_column_name, json_data.getString(str_column_name));

                        } else {
                            if(!str_column_name.equals("phpid"))
                            conV.put("" + str_column_name, "0");

                        }
                    }
                    conV.put("is_uploaded", "true");

                    long count1 = db.insert(table, null, conV);
                    if (count1 != -1) {
                        Log.v("DataHelp", "Insert " + table + " Details Successfully");
                    } else {
                        Log.v("DataHelp", "Insert " + table + " Details Fail");
                    }

                } else {
                    ContentValues conV = new ContentValues();
                    for (int k = 1; k < count - 1; k++) {
                        str_column_name = c.getColumnName(k);
                        if (!str_column_name.equals("position") && !str_column_name.equals("is_uploaded") && !str_column_name.equals("phpid")) {
                            conV.put("" + str_column_name, json_data.getString(str_column_name));
                        } else {
                            if(!str_column_name.equals("phpid"))
                            conV.put("" + str_column_name, "0");
                        }
                    }
                    conV.put("is_uploaded", "true");

                    long count1 = db.update(table, conV, "phpid='" + json_data.getString("id") + "' ", null);
                    if (count1 != -1) {
                        Log.v("DataHelp", "Update " + table + " Details Successfully");
                    } else {
                        Log.v("DataHelp", "Update " + table + " Details Fail");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Cursor getData(String table_name){

        Cursor cursor = db.rawQuery("select * from " + table_name + "", null);
        if (cursor.getCount() > 0 )
            return cursor;
        else
            return null;
    }

    public Cursor getPaymentOptionData(String table_name,String flag, int payment_id){
        Cursor cursor;
        if(table_name.equals(DataBaseConstants.TableNames.TBL_PAYMENT_OPTION_PROVIDER))
            cursor = db.rawQuery("select * from " + table_name + " where top_listed = '"+flag+"' and payment_option_id = '"+payment_id+"' order by payment_opt_provider_name asc", null);
        else
            cursor = db.rawQuery("select * from " + table_name + " where top_listed = '"+flag+"' and payment_option_id = '"+payment_id+"' order by card_type_name asc", null);

        if (cursor.getCount() > 0 )
            return cursor;
        else
            return null;
    }

    public void deleteTable(String table) {
        try {
            long id = db.delete(table, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean setPaymentList(JSONObject jsonObject, String card_type) {

        try {
        ContentValues cv = new ContentValues();
        cv.put(DataBaseConstants.ConstantsTblMyPaymentList.BANK_ID, getID(DataBaseConstants.TableNames.TBL_PAYMENT_OPTION_PROVIDER,jsonObject.getString("bank_name"), jsonObject.getString("provider_id")));
        cv.put(DataBaseConstants.ConstantsTblMyPaymentList.PROVIDER_ID, jsonObject.getString("provider_id"));
        cv.put(DataBaseConstants.ConstantsTblMyPaymentList.CARD_ID, getID(DataBaseConstants.TableNames.TBL_CARD_TYPE,card_type, ""));
        cv.put(DataBaseConstants.ConstantsTblMyPaymentList.BANK_NAME, jsonObject.getString("bank_name"));
        cv.put(DataBaseConstants.ConstantsTblMyPaymentList.PROVIDER_NAME, jsonObject.getString("provider_name"));
        cv.put(DataBaseConstants.ConstantsTblMyPaymentList.CARD_TYPE, card_type);
        cv.put(DataBaseConstants.ConstantsTblMyPaymentList.CREATED_BY, "");
        cv.put(DataBaseConstants.ConstantsTblMyPaymentList.CREATED_TIME, "");
        cv.put(DataBaseConstants.ConstantsTblMyPaymentList.UPDATED_BY, "");
        cv.put(DataBaseConstants.ConstantsTblMyPaymentList.UPDATED_TIME, "");
        cv.put(DataBaseConstants.ConstantsTblMyPaymentList.IS_UPLOADED, "");
        cv.put(DataBaseConstants.ConstantsTblMyPaymentList.PHPID, "");

        try {
            long id = db.insert(DataBaseConstants.TableNames.TBL_MY_PAYMENT_LIST, null, cv);
            LTU.LI(TAG, "insert data added id  " + id);
            return true;
        } catch (Exception e) {
            LTU.LE(TAG, "Exception " + e.toString());
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;

    }

    public int getID(String table_name, String name,String provider_id){
        Cursor cursor;
        int id= 0;
        if(table_name.equals(DataBaseConstants.TableNames.TBL_CARD_TYPE))
            cursor = db.rawQuery("select id from " + DataBaseConstants.TableNames.TBL_CARD_TYPE + " where "+DataBaseConstants.ConstantsTblCardType.CARD_TYPE_NAME+"='"+name+"'", null);
        else
            cursor = db.rawQuery("select id from " + DataBaseConstants.TableNames.TBL_PAYMENT_OPTION_PROVIDER + " where "+DataBaseConstants.ConstantsTblPaymentOptionProvider.PAYMENT_OPT_PROVIDER_NAME+"='"+name+"' and "+DataBaseConstants.ConstantsTblPaymentOptionProvider.PAYMENT_OPTION_ID+"='"+provider_id+"'", null);
        while (cursor.moveToNext()){
            if(table_name.equals(DataBaseConstants.TableNames.TBL_CARD_TYPE))
                id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblCardType.ID)));
            else
                id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblPaymentOptionProvider.ID)));
        }

        return id;
    }

    public JSONArray getMyPaymentList() {

        JSONArray array = new JSONArray();
        JSONObject json = null;

        Cursor cursor = db.rawQuery("select * from " + DataBaseConstants.TableNames.TBL_MY_PAYMENT_LIST + "", null);

        try {
            while (cursor.moveToNext()){
                json = new JSONObject();
                json.put("id", cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblMyPaymentList.ID)));
                json.put("bank_name", cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblMyPaymentList.BANK_NAME)));
                json.put("provider_name", cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblMyPaymentList.PROVIDER_NAME)));
                json.put("card_type", cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblMyPaymentList.CARD_TYPE)));

                json.put("card_id", cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblMyPaymentList.CARD_ID)));
                json.put("bank_id", cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblMyPaymentList.BANK_ID)));
                json.put("provider_id", cursor.getString(cursor.getColumnIndex(DataBaseConstants.ConstantsTblMyPaymentList.PROVIDER_ID)));

                array.put(json);
            }

            return  array;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getOfferList() throws JSONException {

        JSONArray array = new JSONArray();
        JSONArray array1 = new JSONArray();
        array = getMyPaymentList();

        StringBuilder str_paymopt_provider_id = new StringBuilder("paymopt_provider_id IN (");
        StringBuilder str_payment_option_id = new StringBuilder("payment_option_id IN (");
        StringBuilder str_cardtype_id = new StringBuilder("cardtype_id IN (");
        JSONObject jsonObject;

        for(int i=0; i<array.length(); i++){
            jsonObject = array.getJSONObject(i);
            str_paymopt_provider_id.append("'"+jsonObject.getString("provider_id")+"',");
            str_payment_option_id.append("'"+jsonObject.getString("bank_id")+"',");
            str_cardtype_id.append("'"+jsonObject.getString("card_id")+"',");
        }

        if(str_paymopt_provider_id.toString().contains(","))
        str_paymopt_provider_id.deleteCharAt(str_paymopt_provider_id.length()-1);

        if(str_payment_option_id.toString().contains(","))
        str_payment_option_id.deleteCharAt(str_payment_option_id.length()-1);

        if(str_cardtype_id.toString().contains(","))
        str_cardtype_id.deleteCharAt(str_cardtype_id.length()-1);

        str_paymopt_provider_id.append(")");
        str_payment_option_id.append(")");
        str_cardtype_id.append(")");

        String condition = str_paymopt_provider_id+" and "+str_payment_option_id+" and "+str_cardtype_id;

        JSONObject json = null;

        Log.e("Query","select * from " + DataBaseConstants.TableNames.TBL_OFFER_DETAILS + " where "+condition+" and `active_offer`='1'");
        Cursor cursor = db.rawQuery("select * from " + DataBaseConstants.TableNames.TBL_OFFER_DETAILS + " where "+condition+" and `active_offer`='1'", null);

        try {
            while (cursor.moveToNext()){
                json = new JSONObject();

                for(int i = 0 ; i < cursor.getColumnCount(); i++){
                    json.put(cursor.getColumnName(i), cursor.getString(cursor.getColumnIndex(cursor.getColumnName(i))));
                }

                array1.put(json);
            }

            return  array1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getOfferListOnFilter(String category_name, String sub_category_name) throws JSONException {

        JSONArray array = new JSONArray();
        JSONObject json = null;
        //select * from tbl_offer_details where category_id IN (select id from tbl_category_details where category_name= 'gghhhhh') and subcategory_id IN (select id from  tbl_sub_category_details where sub_category_name= 'mmmmmmmmmmmm') and `active_offer`='1'
        Log.e("Query","select * from " + DataBaseConstants.TableNames.TBL_OFFER_DETAILS + " where category_id IN (select id from tbl_category_details where category_name= '"+category_name+"') and subcategory_id IN (select id from  tbl_sub_category_details where sub_category_name= '"+sub_category_name+"') and `active_offer`='1'");
        Cursor cursor = db.rawQuery("select * from " + DataBaseConstants.TableNames.TBL_OFFER_DETAILS + " where category_id IN (select id from tbl_category_details where category_name= '"+category_name+"') and subcategory_id IN (select id from  tbl_sub_category_details where sub_category_name= '"+sub_category_name+"') and `active_offer`='1'", null);

        try {
            while (cursor.moveToNext()){
                json = new JSONObject();
                for(int i = 0 ; i < cursor.getColumnCount(); i++){
                    json.put(cursor.getColumnName(i), cursor.getString(cursor.getColumnIndex(cursor.getColumnName(i))));
                }
                array.put(json);
            }

            return  array;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean deleteMyList(String id) {

         int result = db.delete(DataBaseConstants.TableNames.TBL_MY_PAYMENT_LIST , "id = '"+id+"'", null);
        if(result == 1)
            return true;
        else
            return false;

    }

    public ArrayList getCategorySubCategoryList(String type) {

        ArrayList list = new ArrayList();
        Cursor cursor;
        if(type.equals("category")) {
            cursor = db.rawQuery("select distinct(category_name) from " + DataBaseConstants.TableNames.TBL_CATEGORY_DETAILS + "", null);
            list.add("Select Category");
        }else{
            cursor = db.rawQuery("select distinct(sub_category_name) from " + DataBaseConstants.TableNames.TBL_SUB_CATEGORY_DETAILS + "", null);
            list.add("Select Sub category");
        }
        while (cursor.moveToNext()){

            if(type.equals("category"))
                list.add(cursor.getString(cursor.getColumnIndex("category_name")));
            else
                list.add(cursor.getString(cursor.getColumnIndex("sub_category_name")));
        }

        if(list.size()>1)
            return list;

        return null;
    }

    public int getLikeCount(String id) {

        int result = 0;
        Log.e("Query","select count(id) from " + DataBaseConstants.TableNames.TBL_LIKE_FAVORITE_SHARE + " where offer_id = '"+id+"' and type = 'Like'");
        Cursor cursor = db.rawQuery("select count(id) from " + DataBaseConstants.TableNames.TBL_LIKE_FAVORITE_SHARE + " where offer_id = '"+id+"' and type = 'Like'", null);

        if (cursor.moveToNext()){
            result = Integer.parseInt(cursor.getString(cursor.getColumnIndex("count(id)")));
        }
        return  result;

    }

    public void addLikeShareCount(String id, String type) {
        Log.e("datetime", ""+DTU.getCurrentDateTimeStamp("yyyy-mm-dd hh:mm:ss"));
        ContentValues cv = new ContentValues();
        cv.put(DataBaseConstants.ConstantsTblLikeFavDetails.OFFER_ID, id);
        cv.put(DataBaseConstants.ConstantsTblLikeFavDetails.USERID, "");
        cv.put(DataBaseConstants.ConstantsTblLikeFavDetails.TYPE, type);
        cv.put(DataBaseConstants.ConstantsTblLikeFavDetails.IS_DELETED, "N");
        cv.put(DataBaseConstants.ConstantsTblLikeFavDetails.CREATED_TIME, DTU.getCurrentDateTimeStamp("yyyy-mm-dd hh:mm:ss"));
        cv.put(DataBaseConstants.ConstantsTblLikeFavDetails.CREATED_BY, "");
        cv.put(DataBaseConstants.ConstantsTblLikeFavDetails.APPLICATION_ID, Constants.APPLICATION_ID);
        cv.put(DataBaseConstants.ConstantsTblLikeFavDetails.IS_UPLOADED, "false");
        cv.put(DataBaseConstants.ConstantsTblLikeFavDetails.PHPID, "");


        try {

            long ii = db.insert(DataBaseConstants.TableNames.TBL_LIKE_FAVORITE_SHARE, null, cv);
            LTU.LI(TAG, "insert data added id  " + ii);

        } catch (Exception e) {
            LTU.LE(TAG, "Exception " + e.toString());
        }

    }

    //for all
    public Cursor getDataToUpload(String table) {
        // TODO Auto-generated method stub
        Cursor c = null;
        c = db.rawQuery("select * from " + table + " where is_uploaded='false'", null);
        return c;
    }

    public int updateStatusAll(String table, String id, String code) {
        // TODO Auto-generated method stub
        int count;
        ContentValues conV = new ContentValues();
        conV.put("is_uploaded", "true");
        conV.put("phpid", code);
        return count = db.update(table, conV, "id='" + id + "'", null);
    }
}
