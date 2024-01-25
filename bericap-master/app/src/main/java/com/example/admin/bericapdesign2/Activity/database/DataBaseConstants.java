package com.example.admin.bericapdesign2.Activity.database;

/**
 * Class Name        :  <b>DataBaseConstants.java<b/>
 * Purpose           :  DataBaseConstants is class related of constants.
 * Developed By      :  <b>@Priyanka_android</b>
 * Created Date      :  <b>24.09.2015</b>
 * <p/>
 * Modified Reason   :  <b></b>
 * Modified By       :  <b></b>
 * Modified Date     :  <b></b>
 * <p/>
 */
public class DataBaseConstants {
    public static final String DATABASE_NAME = "bericap.db";
    public static final int DATABASE_VERSION = 1;

    public static class TableNames {
        public static final String TBL_PATIENT_REGISTRATION = "tbl_patient_registration";
        public static final String TBL_MASTER_EMPLOYEE = "tbl_Master_Employee";
        public static final String TBL_REFER_DOCTOR = "RefferDoctor";
        public static final String TBL_MASTER_CHARGES = "tbl_Master_Charges";
        public static final String TBL_MASTER_OPD_SURGICAL_PROCEDURE = "tbl_master_OPD_surgical_procedure";
        public static final String TBL_OPD_SURGICAL_PROCEDURE = "tbl_OPD_surgical_procedure";
        public static final String TBL_MASTER_EMPLOYEE_APPOINTMENT = "tbl_Master_Employee_appointment";
        public static final String TBL_SURGICAL_PROCEDURE = "tbl_surgical_procedure";
        public static final String TBL_NOTIFICATION = "notification";
    }

    public static class Constants {
        public static final String ID = "id";
        public static final String APPLICATION_ID = "application_id";
        public static final String CREATED_BY = "created_by";
        public static final String UPDATED_BY = "updated_by";
        public static final String CREATED_TIME = "created_date_time";
        public static final String UPDATED_TIME = "updated_date_time";
        public static final String PHP_ID = "phpid";
        public static final String ISCONSULTANT = "IsConsultant";
        public static final String IS_UPLOADED = "is_uploaded";
        public static final String IS_ACTIVE = "is_active";
        public static final String FLAG = "Flag";
        public static final String SERVER_ID = "ServerID";
    }

    public static class ConstantsEMP extends Constants {
        public static final String NAME = "name";
        public static final String PAN_NUMBER = "pan_number";
        public static final String CHARGES = "FirstCharges";
        public static final String ADDRESS = "address";
        public static final String MOBILE_NO = "mobile_no";
        public static final String FOLLOW_UP_CHARGES = "FollowupCharges";
        public static final String USER_NAME = "UserName";
        public static final String PASSWORD = "Password";
        public static final String ROLL_ID = "RollID";
        public static final String DOB = "DOB";
        public static final String JOINING_DATE = "JoiningDate";
        public static final String QUALIFICATION = "Qualification";
        public static final String REGISTRATION_NO = "RegistrationNo";
        public static final String DO_MARRIAGE = "DOMarriage";
        public static final String AGE = "Age";
        public static final String SEX = "Sex";
    }

    public static class ConstantsMasterCharges extends Constants {
        public static final String CHARGES_CATEGORY_ID = "ChargesCategoryId";
        public static final String CHARGES_NAME_CODE = "ChargesNameCode";
        public static final String CHARGES_NAME = "ChargesName";
        public static final String AMOUNT = "Amount";
    }

    public static class ConstantsSurgicalProcedure extends Constants {
        public static final String SURGICAL_ADMIN_ID = "Surgical_admin_id";
        public static final String CHARGES = "Charges";
        public static final String NAME = "Name";
        public static final String QTY = "QTY";
        public static final String TOTAL = "total";
    }

    public static class ConstantsPatient extends Constants {
        public static final String DATE = "tdate";
        public static final String PATIENT_ID = "PatientID";
        public static final String NAME = "name";
        public static final String MOBILE_NO = "mobile_no";
        public static final String ADDRESS = "address";
        public static final String EMAIL = "email";
        public static final String AGE = "age";
        public static final String GENDER = "gender";
        public static final String DATE_OF_BIRTH = "DateOfBirth";
        public static final String WEIGHT = "Weight";

    }

    //TODO set of constant of the Recruiter
    public static class NotificationConstants {

        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String MESSAGE = "message";
        public static final String SHORTCODE = "shortcode";
        public static final String CREATED_TIME = "created_time";
        public static final String PHP_ID = "phpid";
        public static final String CREATED_By = "created_by";
        public static final String UPDATED_BY = "updated_by";
        public static final String UPDATED_TIME = "updated_time";
        public static final String MY_CREATED_TIME = "my_created_time";
        public static final String IS_ACTIVE = "is_active";
        public static final String IS_DELETED = "is_deleted";
    }
    public static class DrReferConstants {

        public static final String REFERREDID = "ReferredID";
        public static final String NAME = "Name";

    }

    public static class ConstantsAppointment extends Constants {
        public static final String EMP_ID = "EmpID";
        public static final String PATIENT_ID = "PatientID";
        public static final String APPOINTMENT_TIME = "appointmentTime";
        public static final String DATE = "Date";
        public static final String WEEK_DAYS = "WeekDays";
        //public static final String SERVER_ID = "ServerID";
        //public static final String IS_UPLOAD = "Isupload";
    }
}
