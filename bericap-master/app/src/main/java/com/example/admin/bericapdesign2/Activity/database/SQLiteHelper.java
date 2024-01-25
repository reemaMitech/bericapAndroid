package com.example.admin.bericapdesign2.Activity.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.bericapdesign2.Util.LTU;


/**
 * Class Name        :  <b>SQLiteHelper.java<b/>
 * Purpose           :  SQLiteHelper is class related of create table.
 * Developed By      :  <b>@Priyanka_android</b>
 * Created Date      :  <b>24.09.2015</b>
 * <p/>
 * Modified Reason   :  <b></b>
 * Modified By       :  <b></b>
 * Modified Date     :  <b></b>
 * <p/>
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context) {
        super(context, DataBaseConstants.DATABASE_NAME, null, DataBaseConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQLiteQueries.CREATE_NOTIFICATION);//New Added the for temp
        } catch (SQLException e) {
            LTU.LE("SQLiteHelper ", e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TableNames.TBL_NOTIFICATION);
            onCreate(db);
        } catch (SQLException e) {
            LTU.LE("SQLiteHelper ", e.toString());
            e.printStackTrace();
        }
    }
}
