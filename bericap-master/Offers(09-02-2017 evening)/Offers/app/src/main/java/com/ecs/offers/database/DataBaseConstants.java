package com.ecs.offers.database;

/**
 * Class Name        :  <b>DataBaseConstants.java<b/>
 * Purpose           :  DataBaseConstants is class related of constants.
 * Developed By      :  <b>@Nitin Malwadkar</b>
 * Created Date      :  <b>3.02.2017</b>
 * <p/>
 * Modified Reason   :  <b></b>
 * Modified By       :  <b></b>
 * Modified Date     :  <b></b>
 * <p/>
 */
public class DataBaseConstants {
    public static final String DATABASE_NAME = "dealsofferguide";
    public static final int DATABASE_VERSION = 20;

    public static class TableNames {
        public static final String TBL_CARD_DETAILS = "tbl_card_details";
        public static final String TBL_CARD_TYPE = "tbl_card_type";
        public static final String TBL_CATEGORY_DETAILS = "tbl_category_details";
        public static final String TBL_SUB_CATEGORY_DETAILS = "tbl_sub_category_details";
        public static final String TBL_OFFER_DETAILS = "tbl_offer_details";
        public static final String TBL_DASHBOARD = "tbl_dashboard";
        public static final String TBL_TRENDING_OFFERS = "tbl_trending_offers";
        public static final String TBL_PAYMENT_OPTION_PROVIDER = "tbl_payment_option_provider";
        public static final String TBL_MY_PAYMENT_LIST = "tbl_my_payment_list";
        public static final String TBL_LIKE_FAVORITE_SHARE = "tbl_like_favorite_share";

    }

    public static class ConstantsTblCardDetails {
        public static final String ID = "id";
        public static final String CARD_NAME = "card_name";
        public static final String CARD_TYPE = "card_type";
        public static final String CARD_NUMBER = "card_number";
        public static final String BANK_NAME = "bank_name";
        public static final String CARD_IMAGE_URL = "card_image_url";
        public static final String IS_DELETED = "is_deleted";
        public static final String APPLICATION_ID = "application_id";
        public static final String CREATED_BY = "created_by";
        public static final String CREATED_TIME = "created_time";
        public static final String UPDATED_BY = "updated_by";
        public static final String UPDATED_TIME = "updated_time";
        public static final String IS_UPLOADED = "is_uploaded";
        public static final String PHPID = "phpid";
    }

    public static class ConstantsTblCardType {
        public static final String ID = "id";
        public static final String PAYMENT_OPTION_ID = "payment_option_id";
        public static final String PAYMENTOPT_PROV_ID = "paymentopt_prov_id";
        public static final String CARD_TYPE_NAME = "card_type_name";
        public static final String CARD_TYPE_IMAGE = "card_type_image";
        public static final String ACTIVE_CARD_TYPE = "active_card_type";
        public static final String IS_DELETED = "is_deleted";
        public static final String TOP_LISTED = "top_listed";
        public static final String APPLICATION_ID = "application_id";
        public static final String CREATED_BY = "created_by";
        public static final String CREATED_TIME = "created_time";
        public static final String UPDATED_BY = "updated_by";
        public static final String UPDATED_TIME = "updated_time";
        public static final String IS_UPLOADED = "is_uploaded";
        public static final String PHPID = "phpid";
    }

    public static class ConstantsTblCategoryDetails {
        public static final String ID = "id";
        public static final String CATEGORY_NAME = "category_name";
        public static final String CATEGORY_TYPE = "category_type";
        public static final String CATEGORY_DETAILS = "category_details";
        public static final String CATEGORY_IMAGE = "category_image";
        public static final String ACTIVE_CATEGORY = "active_category";
        public static final String IS_DELETED = "is_deleted";
        public static final String APPLICATION_ID = "application_id";
        public static final String CREATED_BY = "created_by";
        public static final String CREATED_TIME = "created_time";
        public static final String UPDATED_BY = "updated_by";
        public static final String UPDATED_TIME = "updated_time";
        public static final String IS_UPLOADED = "is_uploaded";
        public static final String PHPID = "phpid";
    }

    public static class ConstantsTblSubCategoryDetails {
        public static final String ID = "id";
        public static final String CATEGORY_ID = "category_id";
        public static final String SUB_CATEGORY_NAME = "sub_category_name";
        public static final String SUB_CATEGORY_TYPE = "sub_category_type";
        public static final String SUB_CATEGORY_DETAILS = "sub_category_details";
        public static final String SUBCATEGORY_IMAGE = "subcategory_image";
        public static final String ACTIVE_SUBCATEGORY = "active_subcategory";
        public static final String IS_DELETED = "is_deleted";
        public static final String APPLICATION_ID = "application_id";
        public static final String CREATED_BY = "created_by";
        public static final String CREATED_TIME = "created_time";
        public static final String UPDATED_BY = "updated_by";
        public static final String UPDATED_TIME = "updated_time";
        public static final String IS_UPLOADED = "is_uploaded";
        public static final String PHPID = "phpid";
    }

    public static class ConstantsTblOfferDetails  {
        public static final String ID = "id";
        public static final String OFFER_NAME = "offer_name";
        public static final String OFFER_DETAIL = "offer_detail";
        public static final String TERM_CONDITION = "term_condition";
        public static final String OFFER_IMAGE = "offer_image";
        public static final String ACTIVE_OFFER = "active_offer";
        public static final String CATEGORY_ID = "category_id";
        public static final String SUBCATEGORY_ID = "subcategory_id";
        public static final String PAYMOPT_PROVIDER_ID = "paymopt_provider_id";
        public static final String CARDTYPE_ID = "cardtype_id";
        public static final String DISCOUNT = "discount";
        public static final String PAYMENT_OPTION_ID = "payment_option_id";
        public static final String IS_ACTIVE = "is_active";
        public static final String IS_DELETED = "is_deleted";
        public static final String APPLICATION_ID = "application_id";
        public static final String CREATED_BY = "created_by";
        public static final String CREATED_DATE_TIME = "created_date_time";
        public static final String UPDATED_BY = "updated_by";
        public static final String UPDATED_DATE_TIME = "updated_date_time";
        public static final String SERVERID = "serverid";
        public static final String SOURCE = "source";
        public static final String IS_UPLOADED = "is_uploaded";
        public static final String PHPID = "phpid";
    }

    public static class ConstantsTblDashboard {
        public static final String ID = "id";
        public static final String SLIDER_IMAGE_URL = "slider_image_url";
        public static final String SLIDER_NAME = "slider_name";
        public static final String CARD_IMAGE_URL = "card_image_url";
        public static final String CARD_NAME = "card_name";
        public static final String CATEGORY_IMAGE_URL = "category_image_url";
        public static final String CATEGORY_NAME = "category_name";
        public static final String TRENDING_IMAGE_URL = "trending_image_url";
        public static final String TRENDING_OFFER_NAME = "trending_offer_name";
        public static final String IMAGE_TYPE = "image_type";
        public static final String CREATED_DATE_TIME = "created_date_time";
        public static final String IS_DELETED = "is_deleted";
        public static final String IS_ACTIVE = "is_active";
        public static final String APPLICATION_ID = "application_id";
        public static final String IS_UPLOADED = "is_uploaded";
        public static final String PHPID = "phpid";
    }

    public static class ConstantsTblTrendingOffers {
        public static final String ID = "id";
        public static final String OFFER_NAME = "offer_name";
        public static final String TRENDING_IMAGE_URL = "trending_image_url";
        public static final String CREATED_DATE_TIME = "created_date_time";
        public static final String IS_DELETED = "is_deleted";
        public static final String IS_ACTIVE = "is_active";
        public static final String IS_UPLOADED = "is_uploaded";
        public static final String PHPID = "phpid";
    }


    public static class ConstantsTblPaymentOptionProvider {
        public static final String ID = "id";
        public static final String PAYMENT_OPTION_ID = "payment_option_id";
        public static final String PAYMENT_OPT_PROVIDER_IMAGE = "paymentopt_provider_image";
        public static final String PAYMENT_OPT_PROVIDER_NAME = "payment_opt_provider_name";
        public static final String ACTIVE_PAY_OPT_PROVIDER = "active_payopt_provider";
        public static final String TOP_LISTED = "top_listed";
        public static final String IS_DELETED = "is_deleted";
        public static final String APPLICATION_ID = "application_id";
        public static final String CREATED_BY = "created_by";
        public static final String CREATED_TIME = "created_time";
        public static final String UPDATED_BY = "updated_by";
        public static final String UPDATED_TIME = "updated_time";
        public static final String IS_UPLOADED = "is_uploaded";
        public static final String PHPID = "phpid";
    }

    public static class ConstantsTblMyPaymentList {
        public static final String ID = "id";
        public static final String PROVIDER_ID = "provider_id";
        public static final String BANK_ID = "bank_id";
        public static final String CARD_ID = "card_id";
        public static final String PROVIDER_NAME = "provider_name";
        public static final String BANK_NAME = "bank_name";
        public static final String CARD_TYPE = "card_type";
        public static final String CREATED_BY = "created_by";
        public static final String CREATED_TIME = "created_time";
        public static final String UPDATED_BY = "updated_by";
        public static final String UPDATED_TIME = "updated_time";
        public static final String IS_UPLOADED = "is_uploaded";
        public static final String PHPID = "phpid";
    }

    public static class ConstantsTblLikeFavDetails {
        public static final String ID = "id";
        public static final String OFFER_ID = "offer_id";
        public static final String USERID = "userid";
        public static final String TYPE = "type";
        public static final String IS_DELETED = "is_deleted";
        public static final String CREATED_TIME = "created_time";
        public static final String CREATED_BY = "created_by";
        public static final String APPLICATION_ID = "application_id";
        public static final String IS_UPLOADED = "is_uploaded";
        public static final String PHPID = "phpid";
    }
}
