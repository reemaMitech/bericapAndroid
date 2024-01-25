package com.ecs.offers.database;

/**
 * Class Name        :  <b>S    SQLiteQueries.java<b/>
 * Purpose           :  SQLiteQueries is class related of queries.
 * Developed By      :  <b>@Nitin Malwadkar</b>
 * Created Date      :  <b>3.02.2017</b>
 * <p/>
 * Modified Reason   :  <b></b>
 * Modified By       :  <b></b>
 * Modified Date     :  <b></b>
 * <p/>
 */


public class SQLiteQueries {
    public static final String tbl_card_details = "create table IF NOT EXISTS "
            + DataBaseConstants.TableNames.TBL_CARD_DETAILS + "("
            + DataBaseConstants.ConstantsTblCardDetails.ID + " INTEGER primary key AUTOINCREMENT,"
            + DataBaseConstants.ConstantsTblCardDetails.PHPID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardDetails.CARD_NAME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardDetails.CARD_TYPE + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardDetails.CARD_NUMBER + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardDetails.BANK_NAME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardDetails.CARD_IMAGE_URL + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardDetails.APPLICATION_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardDetails.CREATED_BY + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardDetails.UPDATED_BY + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardDetails.CREATED_TIME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardDetails.UPDATED_TIME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardDetails.IS_DELETED + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardDetails.IS_UPLOADED + " VARCHAR" + ");";

    public static final String tbl_card_type = "create table IF NOT EXISTS "
            + DataBaseConstants.TableNames.TBL_CARD_TYPE + "("
            + DataBaseConstants.ConstantsTblCardType.ID + " INTEGER primary key AUTOINCREMENT,"
            + DataBaseConstants.ConstantsTblCardType.PAYMENT_OPTION_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardType.PAYMENTOPT_PROV_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardType.CARD_TYPE_NAME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardType.CARD_TYPE_IMAGE + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardType.ACTIVE_CARD_TYPE + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardType.IS_DELETED + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardType.TOP_LISTED + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardType.APPLICATION_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardType.CREATED_BY + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardType.UPDATED_BY + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardType.CREATED_TIME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardType.UPDATED_TIME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardType.IS_UPLOADED + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCardType.PHPID + " VARCHAR"+ ");";

    public static final String tbl_category_details = "create table IF NOT EXISTS "
            + DataBaseConstants.TableNames.TBL_CATEGORY_DETAILS + "("
            + DataBaseConstants.ConstantsTblCategoryDetails.ID + " INTEGER primary key AUTOINCREMENT,"
            + DataBaseConstants.ConstantsTblCategoryDetails.CATEGORY_NAME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCategoryDetails.CATEGORY_DETAILS + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCategoryDetails.CATEGORY_TYPE + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCategoryDetails.CATEGORY_IMAGE + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCategoryDetails.ACTIVE_CATEGORY + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCategoryDetails.APPLICATION_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCategoryDetails.CREATED_BY + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCategoryDetails.UPDATED_BY + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCategoryDetails.CREATED_TIME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCategoryDetails.UPDATED_TIME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCategoryDetails.IS_DELETED + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCategoryDetails.IS_UPLOADED + " VARCHAR,"
            + DataBaseConstants.ConstantsTblCategoryDetails.PHPID + " VARCHAR" + ");";


    public static final String tbl_offer_details = "create table IF NOT EXISTS "
            + DataBaseConstants.TableNames.TBL_OFFER_DETAILS + "("
            + DataBaseConstants.ConstantsTblOfferDetails.ID + " INTEGER primary key AUTOINCREMENT,"
            + DataBaseConstants.ConstantsTblOfferDetails.PHPID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.OFFER_NAME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.OFFER_DETAIL + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.TERM_CONDITION + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.OFFER_IMAGE + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.ACTIVE_OFFER + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.CATEGORY_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.SUBCATEGORY_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.PAYMOPT_PROVIDER_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.CARDTYPE_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.PAYMENT_OPTION_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.DISCOUNT + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.IS_ACTIVE + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.UPDATED_BY + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.SERVERID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.IS_DELETED + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.APPLICATION_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.CREATED_BY + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.CREATED_DATE_TIME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.UPDATED_DATE_TIME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.SOURCE + " VARCHAR,"
            + DataBaseConstants.ConstantsTblOfferDetails.IS_UPLOADED + " VARCHAR" + ");";

    public static final String tbl_dashboard = "create table IF NOT EXISTS "
            + DataBaseConstants.TableNames.TBL_DASHBOARD + "("
            + DataBaseConstants.ConstantsTblDashboard.ID + " INTEGER primary key AUTOINCREMENT,"
            + DataBaseConstants.ConstantsTblDashboard.PHPID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblDashboard.SLIDER_IMAGE_URL + " VARCHAR,"
            + DataBaseConstants.ConstantsTblDashboard.SLIDER_NAME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblDashboard.CARD_IMAGE_URL + " VARCHAR,"
            + DataBaseConstants.ConstantsTblDashboard.CARD_NAME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblDashboard.CATEGORY_IMAGE_URL + " VARCHAR,"
            + DataBaseConstants.ConstantsTblDashboard.CATEGORY_NAME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblDashboard.TRENDING_IMAGE_URL + " VARCHAR,"
            + DataBaseConstants.ConstantsTblDashboard.TRENDING_OFFER_NAME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblDashboard.IMAGE_TYPE + " VARCHAR,"
            + DataBaseConstants.ConstantsTblDashboard.CREATED_DATE_TIME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblDashboard.IS_ACTIVE + " VARCHAR,"
            + DataBaseConstants.ConstantsTblDashboard.APPLICATION_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblDashboard.IS_UPLOADED + " VARCHAR" + ");";

    public static final String tbl_trending_offers = "create table IF NOT EXISTS "
            + DataBaseConstants.TableNames.TBL_TRENDING_OFFERS + "("
            + DataBaseConstants.ConstantsTblTrendingOffers.ID + " INTEGER primary key AUTOINCREMENT,"
            + DataBaseConstants.ConstantsTblTrendingOffers.PHPID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblTrendingOffers.OFFER_NAME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblTrendingOffers.TRENDING_IMAGE_URL + " VARCHAR,"
            + DataBaseConstants.ConstantsTblTrendingOffers.CREATED_DATE_TIME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblTrendingOffers.IS_DELETED + " VARCHAR,"
            + DataBaseConstants.ConstantsTblTrendingOffers.IS_ACTIVE + " VARCHAR,"
            + DataBaseConstants.ConstantsTblTrendingOffers.IS_UPLOADED + " VARCHAR" + ");";

    public static final String tbl_payment_option_provider = "create table IF NOT EXISTS "
            + DataBaseConstants.TableNames.TBL_PAYMENT_OPTION_PROVIDER + "("
            + DataBaseConstants.ConstantsTblPaymentOptionProvider.ID + " INTEGER primary key AUTOINCREMENT,"
            + DataBaseConstants.ConstantsTblPaymentOptionProvider.PHPID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblPaymentOptionProvider.PAYMENT_OPTION_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblPaymentOptionProvider.PAYMENT_OPT_PROVIDER_IMAGE + " VARCHAR,"
            + DataBaseConstants.ConstantsTblPaymentOptionProvider.PAYMENT_OPT_PROVIDER_NAME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblPaymentOptionProvider.ACTIVE_PAY_OPT_PROVIDER + " VARCHAR,"
            + DataBaseConstants.ConstantsTblPaymentOptionProvider.TOP_LISTED + " VARCHAR,"
            + DataBaseConstants.ConstantsTblPaymentOptionProvider.IS_DELETED + " VARCHAR,"
            + DataBaseConstants.ConstantsTblPaymentOptionProvider.CREATED_BY + " VARCHAR,"
            + DataBaseConstants.ConstantsTblPaymentOptionProvider.CREATED_TIME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblPaymentOptionProvider.UPDATED_BY + " VARCHAR,"
            + DataBaseConstants.ConstantsTblPaymentOptionProvider.UPDATED_TIME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblPaymentOptionProvider.APPLICATION_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblPaymentOptionProvider.IS_UPLOADED + " VARCHAR" + ");";

    public static final String tbl_my_payment_list = "create table IF NOT EXISTS "
            + DataBaseConstants.TableNames.TBL_MY_PAYMENT_LIST + "("
            + DataBaseConstants.ConstantsTblMyPaymentList.ID + " INTEGER primary key AUTOINCREMENT,"
            + DataBaseConstants.ConstantsTblMyPaymentList.PROVIDER_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblMyPaymentList.BANK_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblMyPaymentList.CARD_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblMyPaymentList.PROVIDER_NAME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblMyPaymentList.BANK_NAME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblMyPaymentList.CARD_TYPE + " VARCHAR,"
            + DataBaseConstants.ConstantsTblMyPaymentList.CREATED_BY + " VARCHAR,"
            + DataBaseConstants.ConstantsTblMyPaymentList.CREATED_TIME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblMyPaymentList.UPDATED_BY + " VARCHAR,"
            + DataBaseConstants.ConstantsTblMyPaymentList.UPDATED_TIME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblMyPaymentList.IS_UPLOADED + " VARCHAR,"
            + DataBaseConstants.ConstantsTblMyPaymentList.PHPID + " VARCHAR" + ");";

    public static final String tbl_like_fav_share = "create table IF NOT EXISTS "
            + DataBaseConstants.TableNames.TBL_LIKE_FAVORITE_SHARE + "("
            + DataBaseConstants.ConstantsTblLikeFavDetails.ID + " INTEGER primary key AUTOINCREMENT,"
            + DataBaseConstants.ConstantsTblLikeFavDetails.OFFER_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblLikeFavDetails.USERID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblLikeFavDetails.TYPE + " VARCHAR,"
            + DataBaseConstants.ConstantsTblLikeFavDetails.IS_DELETED + " VARCHAR,"
            + DataBaseConstants.ConstantsTblLikeFavDetails.CREATED_BY + " VARCHAR,"
            + DataBaseConstants.ConstantsTblLikeFavDetails.CREATED_TIME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblLikeFavDetails.APPLICATION_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblLikeFavDetails.IS_UPLOADED + " VARCHAR,"
            + DataBaseConstants.ConstantsTblLikeFavDetails.PHPID + " VARCHAR" + ");";


    public static final String tbl_sub_category_details = "create table IF NOT EXISTS "
            + DataBaseConstants.TableNames.TBL_SUB_CATEGORY_DETAILS + "("
            + DataBaseConstants.ConstantsTblSubCategoryDetails.ID + " INTEGER primary key AUTOINCREMENT,"
            + DataBaseConstants.ConstantsTblSubCategoryDetails.CATEGORY_ID + " VARCHAR,"
            + DataBaseConstants.ConstantsTblSubCategoryDetails.SUB_CATEGORY_NAME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblSubCategoryDetails.SUB_CATEGORY_TYPE + " VARCHAR,"
            + DataBaseConstants.ConstantsTblSubCategoryDetails.SUB_CATEGORY_DETAILS + " VARCHAR,"
            + DataBaseConstants.ConstantsTblSubCategoryDetails.SUBCATEGORY_IMAGE + " VARCHAR,"
            + DataBaseConstants.ConstantsTblSubCategoryDetails.ACTIVE_SUBCATEGORY + " VARCHAR,"
            + DataBaseConstants.ConstantsTblSubCategoryDetails.CREATED_BY + " VARCHAR,"
            + DataBaseConstants.ConstantsTblSubCategoryDetails.UPDATED_BY + " VARCHAR,"
            + DataBaseConstants.ConstantsTblSubCategoryDetails.CREATED_TIME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblSubCategoryDetails.UPDATED_TIME + " VARCHAR,"
            + DataBaseConstants.ConstantsTblSubCategoryDetails.IS_DELETED + " VARCHAR,"
            + DataBaseConstants.ConstantsTblSubCategoryDetails.IS_UPLOADED + " VARCHAR,"
            + DataBaseConstants.ConstantsTblSubCategoryDetails.PHPID + " VARCHAR" + ");";

}
