package com.ecs.offers.util;

import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DELL on 09/30/2015.
 */
public class VU {
    public static final String MOBILE_PATTERN = "[0-9]{10}";
    public static final String PHONE_PATTERN = "[0-9]{11}";
    public static final String LAND_LINE_PATTERN = "[0-9]{11}";
    public static final String EMAIL_PATTERN = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+";
    public static final String VEHICLE_NO_PATTERN = "[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)?[0-9]{4}";
    private final static Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9._-]+@[a-z-a-z]+\\.+[a-z]+");
    public static Pattern pattern;
    public static String contactstring;
    static Matcher matcher;

    public static boolean isEmpty(EditText editText) {
        // TODO method to check edit text is fill or no
        // return true when edit text is empty
        if (editText.getText().toString().trim().equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isDotOnly(EditText editText) {
        // TODO method to check edit text is fill or no
        if (editText.getText().toString().length() == 1 && editText.getText().toString().charAt(0) == '.') {
            return true;
        }
        if (editText.getText().toString().trim().equals(".")) {
            return true;
        }


        return false;
    }

    public static boolean isDot(EditText editText) {
        // TODO method to check edit text is fill or no
        if (editText.getText().toString().length() == 1 && editText.getText().toString().trim().equals(".")) {
            return true;
        }

        if (editText.getText().toString().length() > 1 && editText.getText().toString().charAt(1) == '.') {
            return true;
        }
        return false;
    }

    public static boolean isAutocompleteEmpty(AutoCompleteTextView editText) {
        // TODO method to check edit text is fill or no
        if (editText.getText().toString().trim().equals("")) {
            return true;
        }
        return false;
    }

    public static boolean checkEmail(EditText edittext) {
        return EMAIL_ADDRESS_PATTERN.matcher(edittext.getText().toString().trim()).matches();
    }

    public static boolean isEmailId(EditText editText) {
        // method to check edit text is fill or no
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(editText.getText().toString().trim());
        if (matcher.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isPhoneNo(EditText edittext) {
        //method to check Phone no string
        if (edittext.getText().toString().trim().length() != 0 && edittext.getText().toString().trim().length() < 10 || edittext.getText().toString().trim().length() > 11) {
            return true;
        }
        return false;
    }

    public static boolean isConfirPassWord(EditText edtPassword,
                                           EditText edtConfirPassword) {
        if (edtPassword.getText().toString()
                .equals(edtConfirPassword.getText().toString())) {
            return true;
        }
        return false;
    }

    public static boolean isVehicleNo(EditText editText) {
        if (editText.getText().toString().matches(VEHICLE_NO_PATTERN)) {
            return true;
        }
        return false;


    }

    public static boolean isContactNo(EditText editText) {
        if (editText.getText().toString().trim().length() != 0 && editText.getText().toString().trim().length() != 10) {
            if (editText.getText().toString().trim().length() == 11) {
                if (editText.getText().toString().trim().charAt(0) != '0') {
                    return true;
                }
                return false;
            } else {
                return true;
            }

        }


        return false;
    }

}
