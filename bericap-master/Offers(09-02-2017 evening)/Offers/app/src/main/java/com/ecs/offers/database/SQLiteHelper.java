package com.ecs.offers.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class Name        :  <b>SQLiteHelper.java<b/>
 * Purpose           :  SQLiteHelper is class related of create table.
 * Developed By      :  <b>@Nitin Malwadkar</b>
 * Created Date      :  <b>3.02.2017</b>
 * <p/>
 * Modified Reason   :  <b></b>
 * Modified By       :  <b></b>
 * Modified Date     :  <b></b>
 * <p/>
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context) {
        super(context,DataBaseConstants.DATABASE_NAME, null,DataBaseConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLiteQueries.tbl_card_details);
        db.execSQL(SQLiteQueries.tbl_card_type);
        db.execSQL(SQLiteQueries.tbl_category_details);
        db.execSQL(SQLiteQueries.tbl_dashboard);
        db.execSQL(SQLiteQueries.tbl_offer_details);
        db.execSQL(SQLiteQueries.tbl_trending_offers);
        db.execSQL(SQLiteQueries.tbl_payment_option_provider);
        db.execSQL(SQLiteQueries.tbl_my_payment_list);
        db.execSQL(SQLiteQueries.tbl_like_fav_share);
        db.execSQL(SQLiteQueries.tbl_sub_category_details);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TableNames.TBL_OFFER_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TableNames.TBL_TRENDING_OFFERS);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TableNames.TBL_CARD_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TableNames.TBL_DASHBOARD);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TableNames.TBL_CARD_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TableNames.TBL_CATEGORY_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TableNames.TBL_PAYMENT_OPTION_PROVIDER);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TableNames.TBL_MY_PAYMENT_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TableNames.TBL_LIKE_FAVORITE_SHARE);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TableNames.TBL_SUB_CATEGORY_DETAILS);

        onCreate(db);
    }
}
