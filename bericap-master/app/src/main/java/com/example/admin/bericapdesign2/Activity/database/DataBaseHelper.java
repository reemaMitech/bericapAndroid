package com.example.admin.bericapdesign2.Activity.database;

/**
 * Class Name        :  <b>DataBaseHelper.java<b/>
 * Purpose           : DataBaseHelper is class related of database operation.
 * Developed By      :  <b>@Priyanka_android</b>
 * Created Date      :  <b>24.09.2015</b>
 * <p/>
 * Modified Reason   :  <b></b>
 * Modified By       :  <b></b>
 * Modified Date     :  <b></b>
 * <p/>
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.admin.bericapdesign2.Activity.model.ModelNotification;
import com.example.admin.bericapdesign2.Util.LTU;

import java.util.ArrayList;


public class DataBaseHelper {

    public static SQLiteDatabase db;
    public Context context;
    private SQLiteHelper sqLiteHelper;

    public DataBaseHelper(Context context) {
        this.context = context;
        sqLiteHelper = new SQLiteHelper(context);
        db = sqLiteHelper.getWritableDatabase();
        db = sqLiteHelper.getReadableDatabase();
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


    public static class DBNotification {
        static String TAG = "DBNotification ";

        //TODO Primary methods
        public static boolean insert(ModelNotification object) {
            try {
                Log.e("Title","Title"+object.getTitle());
                Log.e("MESSAGE","MESSAGE"+object.getMessage());
                ContentValues cv = new ContentValues();
                cv.put(DataBaseConstants.NotificationConstants.TITLE, object.getTitle());
                cv.put(DataBaseConstants.NotificationConstants.MESSAGE, object.getMessage());
                try {
                    long id = db.insert(DataBaseConstants.TableNames.TBL_NOTIFICATION, null, cv);
                    //  LTU.LI(TAG, "insert data added id  " + id);
                } catch (Exception e) {
                    // LTU.LE(TAG, "Class Name " + TAG + " method Name " + " insert " + " Exception occurred due to :..............................");
                    // LTU.LE(TAG, "Exception " + e.toString());
                }
            } catch (Exception e) {
                Log.e("error", e.toString());
            }
            return true;
        }

        //TODO: method to get list of notification
        public static ArrayList<ModelNotification> getList() {
            ArrayList<ModelNotification> list = new ArrayList<ModelNotification>();
            try {
                Cursor cursor = null;
                cursor = db.rawQuery("SELECT * FROM " + DataBaseConstants.TableNames.TBL_NOTIFICATION , null);
                list = new ArrayList<ModelNotification>();
                while (cursor.moveToNext()) {
                    ModelNotification object = new ModelNotification();
                    object.setTitle(cursor.getString(cursor.getColumnIndex(DataBaseConstants.NotificationConstants.TITLE)));
                    object.setMessage(cursor.getString(cursor.getColumnIndex(DataBaseConstants.NotificationConstants.MESSAGE)));
                    list.add(object);
                }
                LTU.LI(TAG, "getManageRoutes " + list.toString());
                return list;
            } catch (Exception e) {
                LTU.LE(TAG, "Class Name " + TAG + " method Name " + " getList " + " Exception occurred due to :..............................");
                LTU.LE(TAG, "Exception " + e.toString());
                return null;
            }

        }
    }
}
