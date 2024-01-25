package com.example.admin.bericapdesign2.Activity.database;

/**
 * Class Name        :  <b>S    SQLiteQueries.java<b/>
 * Purpose           :  SQLiteQueries is class related of queries.
 * Developed By      :  <b>@Priyanka_android</b>
 * Created Date      :  <b>24.09.2015</b>
 * <p/>
 * Modified Reason   :  <b></b>
 * Modified By       :  <b></b>
 * Modified Date     :  <b></b>
 * <p/>
 */


public class SQLiteQueries {


    public static final String CREATE_NOTIFICATION = "create table IF NOT EXISTS "
            + DataBaseConstants.TableNames.TBL_NOTIFICATION + "( "
            + DataBaseConstants.NotificationConstants.ID + " INTEGER primary key AUTOINCREMENT,"
            + DataBaseConstants.NotificationConstants.TITLE + " VARCHAR,"
            + DataBaseConstants.NotificationConstants.MESSAGE + " VARCHAR,"
            + DataBaseConstants.NotificationConstants.CREATED_TIME + " VARCHAR,"
            + DataBaseConstants.NotificationConstants.PHP_ID + " VARCHAR,"
            + DataBaseConstants.NotificationConstants.SHORTCODE + " VARCHAR,"
            + DataBaseConstants.NotificationConstants.MY_CREATED_TIME + " VARCHAR );";


}
