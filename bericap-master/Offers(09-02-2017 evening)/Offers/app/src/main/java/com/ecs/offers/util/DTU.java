package com.ecs.offers.util;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DTU {
    // TODO Time...........

    public final static String YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    public final static String DMY = "dd-MM-yyyy";
    public final static String YMD = "yyyy-MM-dd";
    public final static String ymd = "yyyyMMdd";
    public final static String HMS = "HH:mm:ss";
    public final static String HM = "HH:mm";
    public static final DateFormat TWELVE_TF = new SimpleDateFormat("hh:mma");
    // Replace with kk:mm if you want 1-24 interval
    public static final DateFormat TWENTY_FOUR_TF = new SimpleDateFormat(
            "HH:mm");
    public static String time;
    public static int currentHour, currentMinute, currentSeconds;
    public static int currentYear, currentMonth, currentDay;
    public static int myFlg = 0;
    public static String cmpftime, cmpttime, cmpfdate, cmptdate, aTime = "";

    // TODO date..........

    public static String showTimePickerDialog(final Context appContext,
                                              final TextView eStartTime) {

        final Calendar c = Calendar.getInstance();
        currentHour = c.get(Calendar.HOUR_OF_DAY);
        currentMinute = c.get(Calendar.MINUTE);
        currentSeconds = c.get(Calendar.SECOND);
        TimePickerDialog tpd = new TimePickerDialog(appContext,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minutes) {
                        int hour = hourOfDay;
                        int minute = minutes;
                        String time = "" + hourOfDay + "" + minutes + "00";
                        DTU.time = time;
                        int flg = 0;
                        String strHour, strMinutes, strAMPM;

                        if (hour > 12) {
                            flg = 1;
                            hour = hour - 12;
                            strAMPM = "PM";
                        } else {
                            strAMPM = "AM";
                        }
                        if (hour < 10) {
                            strHour = "0" + hour;
                        } else {
                            strHour = "" + hour;
                        }
                        if (minute < 10) {
                            strMinutes = "0" + minute;
                        } else {
                            strMinutes = "" + minute;
                        }
                        eStartTime
                                .setText(strHour + ":" + strMinutes + strAMPM);

                    }
                }, currentHour, currentMinute, false);
        tpd.show();

        return "";
    }

    public static String showTime24HourPickerDialog(final Context appContext,
                                                    final TextView eStartTime) {

        final Calendar c = Calendar.getInstance();
        currentHour = c.get(Calendar.HOUR_OF_DAY);
        currentMinute = c.get(Calendar.MINUTE);
        currentSeconds = c.get(Calendar.SECOND);
        TimePickerDialog tpd = new TimePickerDialog(appContext,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minutes) {
                        int hour = hourOfDay;
                        int minute = roundTo5(minutes);
                        if (minute == 60) {
                            minute = 0;
                            hour = hour + 1;
                        }
                        String time = "" + hourOfDay + "" + minutes + "00";

                        eStartTime
                                .setText(hour + ":" + minute);

                    }
                }, currentHour, currentMinute, false);
        tpd.show();

        return "";
    }

    public static String showTime24HourPickerDialog(final Context appContext, String inputTime,
                                                    final TextView eStartTime) {

        final Calendar c = Calendar.getInstance();
        String[] spiltedTime = inputTime.split(":");

        currentHour = Integer.parseInt(spiltedTime[0]);
        currentMinute = Integer.parseInt(spiltedTime[1]);
        currentSeconds = 00;
//		currentHour = c.get(Calendar.HOUR_OF_DAY);
//		currentMinute = c.get(Calendar.MINUTE);
//		currentSeconds = c.get(Calendar.SECOND);
        TimePickerDialog tpd = new TimePickerDialog(appContext,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minutes) {
                        int hour = hourOfDay;
                        int minute = roundTo5(minutes);
                        if (minute == 60) {
                            minute = 0;
                            hour = hour + 1;
                        }
                        String time = "" + hourOfDay + "" + minutes + "00";
                        if (minute < 10) {
                            eStartTime.setText(hour + ":0" + minute);
                        } else {
                            eStartTime.setText(hour + ":" + minute);
                        }

                    }
                }, currentHour, currentMinute, true);
        tpd.show();

        return "";
    }

    public static String showTime24HourPickerDialog(final Context appContext, String inputTime,
                                                    final EditText eStartTime) {

        final Calendar c = Calendar.getInstance();
        String[] spiltedTime = inputTime.split(":");

        currentHour = Integer.parseInt(spiltedTime[0]);
        currentMinute = Integer.parseInt(spiltedTime[1]);
        currentSeconds = 00;
//		currentHour = c.get(Calendar.HOUR_OF_DAY);
//		currentMinute = c.get(Calendar.MINUTE);
//		currentSeconds = c.get(Calendar.SECOND);
        TimePickerDialog tpd = new TimePickerDialog(appContext,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minutes) {
                        int hour = hourOfDay;
                        int minute = roundTo5(minutes);
                        if (minute == 60) {
                            minute = 0;
                            hour = hour + 1;
                        }
                        String time = "" + hourOfDay + "" + minutes + "00";
                        if (minute < 10) {
                            eStartTime.setText(hour + ":0" + minute);
                        } else {
                            eStartTime.setText(hour + ":" + minute);
                        }

                    }
                }, currentHour, currentMinute, true);
        tpd.show();

        return "";
    }

    public static String showFutureTimePickerDialog(final Context appContext, String inputTime,
                                                    final TextView eStartTime) {

        final Calendar c = Calendar.getInstance();
        String[] spiltedTime = inputTime.split(":");

        currentHour = Integer.parseInt(spiltedTime[0]);
        currentMinute = Integer.parseInt(spiltedTime[1]);
        currentSeconds = 00;
//		currentHour = c.get(Calendar.HOUR_OF_DAY);
//		currentMinute = c.get(Calendar.MINUTE);
//		currentSeconds = c.get(Calendar.SECOND);
        TimePickerDialog tpd = new TimePickerDialog(appContext,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minutes) {
                        int hour = hourOfDay;
                        int minute = roundTo5(minutes);
                        if (minute == 60) {
                            minute = 0;
                            hour = hour + 1;
                        }

                        String time = "" + hourOfDay + "" + minutes + "00";
                        final Calendar c = Calendar.getInstance();
                        currentHour = c.get(Calendar.HOUR_OF_DAY);
                        currentMinute = c.get(Calendar.MINUTE);
                        currentSeconds = c.get(Calendar.SECOND);

                        if (hour > currentHour) {
                            if (minute < 10) {

                                eStartTime.setText(hour + ":0" + minute);
                            } else {

                                eStartTime.setText(hour + ":" + minute);
                            }

                        } else if (hour == currentHour) {
                            if (minute >= currentMinute) {
                                if (minute < 10) {

                                    eStartTime.setText(hour + ":0" + minute);
                                } else {

                                    eStartTime.setText(hour + ":" + minute);
                                }
                            }
                        } else {
                            LTU.TIS(appContext, "", "Selected time has already passed!");
                        }

                    }
                }, currentHour, currentMinute, true);
        tpd.show();

        return "";
    }

    public static String showFutureTimePickerDialog(final Context appContext, String inputTime,
                                                    final EditText eStartTime) {

        final Calendar c = Calendar.getInstance();
        String[] spiltedTime = inputTime.split(":");

        currentHour = Integer.parseInt(spiltedTime[0]);
        currentMinute = Integer.parseInt(spiltedTime[1]);
        currentSeconds = 00;

        TimePickerDialog tpd = new TimePickerDialog(appContext,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minutes) {
                        int hour = hourOfDay;
                        int minute = roundTo5(minutes);
                        if (minute == 60) {
                            minute = 0;
                            hour = hour + 1;
                        }
                        String time = "" + hourOfDay + "" + minutes + "00";
                        final Calendar c = Calendar.getInstance();
                        currentHour = c.get(Calendar.HOUR_OF_DAY);
                        currentMinute = c.get(Calendar.MINUTE);
                        currentSeconds = c.get(Calendar.SECOND);
                        if (hour > currentHour) {
                            if (minute < 10) {
                                eStartTime.setText(hour + ":0" + minute);
                            } else {
                                eStartTime.setText(hour + ":" + minute);
                            }
                        } else if (hour == currentHour) {
                            if (minute >= currentMinute) {
                                if (minute < 10) {
                                    eStartTime.setText(hour + ":0" + minute);
                                } else {
                                    eStartTime.setText(hour + ":" + minute);
                                }
                            }
                        } else {
                            LTU.TIS(appContext, "", "Selected time has already passed!");
                        }

                    }
                }, currentHour, currentMinute, true);
        tpd.show();

        return "";
    }

    public static String showTime24HourPickerDialog(
            final Context appContext, String inputTime, final EditText eStartTime, String strTime) {

        final Calendar c = Calendar.getInstance();
        String[] time = strTime.split(":");
        currentHour = Integer.parseInt(time[0]);
        currentMinute = Integer.parseInt(time[1]);
        currentSeconds = 00;
        TimePickerDialog tpd = new TimePickerDialog(appContext,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minutes) {
                        int hour = hourOfDay;
                        int minute = minutes;
                        String time = "" + hourOfDay + "" + minutes + "00";

                        eStartTime
                                .setText(hour + ":" + minute);

                    }
                }, currentHour, currentMinute, true);
        tpd.show();

        return "";
    }

    public static void showTimePickerDialogForFixTime(final Context appContext,
                                                      final TextView eStartTime, final String startTime,
                                                      final String endTime) {
        final Calendar c = Calendar.getInstance();
        currentHour = c.get(Calendar.HOUR_OF_DAY);
        currentMinute = c.get(Calendar.MINUTE);
        currentSeconds = c.get(Calendar.SECOND);
        TimePickerDialog tpd = new TimePickerDialog(appContext,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minutes) {
                        int hour = hourOfDay;
                        int minute = minutes;
                        String time = "" + hourOfDay + "" + minutes + "00";
                        DTU.time = time;
                        int flg = 0;
                        String strHour, strMinutes, strAMPM;

                        if (hour > 12) {
                            flg = 1;
                            hour = hour - 12;
                            strAMPM = "PM";
                        } else {
                            strAMPM = "AM";
                        }
                        if (hour < 10) {
                            strHour = "0" + hour;
                        } else {
                            strHour = "" + hour;
                        }
                        if (minute < 10) {
                            strMinutes = "0" + minute;
                        } else {
                            strMinutes = "" + minute;
                        }
                        eStartTime
                                .setText(strHour + ":" + strMinutes + strAMPM);

                    }
                }, currentHour, currentMinute, false);
        tpd.show();
    }

    public static String getCurrentDateTimeStamp(String format) {

        DateFormat dateFormatter = new SimpleDateFormat(format);
        dateFormatter.setLenient(false);
        Date today = new Date();
        String s = dateFormatter.format(today);
        return s;
    }

    public static String getTimeAdd(String format, int addMin) {

        Date today = new Date();
        Calendar calender = Calendar.getInstance();
        calender.setTime(today);
        if(today.getHours() != 23) {
            calender.add(Calendar.MINUTE, addMin);
        }
        today = calender.getTime();

        DateFormat dateFormatter = new SimpleDateFormat(DTU.HM);
        dateFormatter.setLenient(false);
        int roundMin = roundTo5(today.getMinutes());
        if(roundMin>=60)
            roundMin = 55;
        today.setMinutes(roundMin);
        String s = dateFormatter.format(today);
        //String[] spitedTime;
        return s;
    }

    public static String getTime() {

        Date date = new Date();
        String time = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();

        return time;
    }


    public static String getDateAdd(String format, int day) {

        Date today = new Date();
        Calendar calender = Calendar.getInstance();
        calender.setTime(today);
        calender.add(Calendar.DATE, day);
        today = calender.getTime();

        DateFormat dateFormatter = new SimpleDateFormat(format);
        dateFormatter.setLenient(false);
        String s = dateFormatter.format(today);
        //String[] spitedTime;
        return s;
    }

    public static String getCurrentDate() {
        try {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int monthOfYear = c.get(Calendar.MONTH);
            int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
            String date_selected = "";

            if ((monthOfYear >= 0 && monthOfYear < 9)
                    && (dayOfMonth > 0 && dayOfMonth < 10))
                date_selected = String.valueOf(year) + "-0"
                        + String.valueOf(monthOfYear + 1) + "-0"
                        + String.valueOf(dayOfMonth);
            else if (monthOfYear >= 0 && monthOfYear < 9)
                date_selected = String.valueOf(year) + "-0"
                        + String.valueOf(monthOfYear + 1) + "-"
                        + String.valueOf(dayOfMonth);
            else if (dayOfMonth > 0 && dayOfMonth < 10)
                date_selected = String.valueOf(year) + "-"
                        + String.valueOf(monthOfYear + 1) + "-0"
                        + String.valueOf(dayOfMonth);
            else
                date_selected = String.valueOf(year) + "-"
                        + String.valueOf(monthOfYear + 1) + "-"
                        + String.valueOf(dayOfMonth);
            return date_selected;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getddmmyyDate(String dt) {
        // Converts mm-dd-yy format to dd-mm-yy Added on 05/12/2013
        String dd = "", mm = "", yy = "";
        int i = 0;
        try {
            for (String retval : dt.split("-")) {
                if (i == 0)
                    yy = retval;
                else if (i == 1)
                    mm = retval;
                else
                    dd = retval;

                i++;
            }
            return (dd + "-" + mm + "-" + yy).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }



    public static String getddmmyyyyDate(String dt, String dateTime) {
        try {
            // Converts mm-dd-yy format to dd-mm-yy Added on 05/12/2013
            String dd = "", mm = "", yy = "", time = "";
            if (dateTime != null || dateTime.equals("dateTime")) {
                String dtarray[] = dt.split(" ");

                dt = dtarray[0];// 2015-02-23
                time = dtarray[1];// 11:30:30

            }

            int i = 0;

            for (String retval : dt.split("-")) {
                if (i == 0)
                    yy = retval;
                else if (i == 1)
                    mm = retval;
                else
                    dd = retval;

                i++;
            }
            return (dd + "-" + mm + "-" + yy).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getyymmddDate(String dt)
    // Converts mm-dd-yy format to dd-mm-yy Added on 05/12/2013
    {
        String dd = "", mm = "", yy = "";
        int i = 0;
        try {
            for (String retval : dt.split("-")) {
                if (i == 0)
                    dd = retval;
                else if (i == 1)
                    mm = retval;
                else
                    yy = retval;
                i++;
            }
            return (yy + "-" + mm + "-" + dd).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void showDatePickerDialog(Context context, final int dateFlg,
                                            final EditText dateEditText) {
        // Displays Date picker
        final Calendar c = Calendar.getInstance();
        currentYear = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH);
        currentDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datepicker = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear,
                                          int monthOfYear, int dayOfMonth) {
                        int year = selectedYear;
                        int month = monthOfYear;
                        int day = dayOfMonth;
                        if (dateFlg == FU.FLAG_ONLY_NEW) {
                            if ((year != currentYear)
                                    || (month < currentMonth && year == currentYear)
                                    || (day < currentDay && year == currentYear && month <= currentMonth)) {
                                // showToastShort(appContext, "ECS",
                                // "Please select proper date.");
                                dateEditText
                                        .setText(getCurrentDateTimeStamp(""));

                            } else {
                                String date_selected;
                                if ((monthOfYear >= 0 && monthOfYear < 9)
                                        && (dayOfMonth > 0 && dayOfMonth < 10))
                                    date_selected = "0"
                                            + String.valueOf(dayOfMonth) + "-0"
                                            + String.valueOf(monthOfYear + 1)
                                            + "-"
                                            + String.valueOf(selectedYear);

                                else if (monthOfYear >= 0 && monthOfYear < 9)
                                    date_selected = String.valueOf(dayOfMonth)
                                            + "-0"
                                            + String.valueOf(monthOfYear + 1)
                                            + "-"
                                            + String.valueOf(selectedYear);

                                else if (dayOfMonth > 0 && dayOfMonth < 10)
                                    date_selected = "0"
                                            + String.valueOf(dayOfMonth) + "-"
                                            + String.valueOf(monthOfYear + 1)
                                            + "-"
                                            + String.valueOf(selectedYear);

                                else
                                    date_selected = String.valueOf(dayOfMonth)
                                            + "-"
                                            + String.valueOf(monthOfYear + 1)
                                            + "-"
                                            + String.valueOf(selectedYear);

                                dateEditText.setText(date_selected);
                            }
                        } else if (dateFlg == FU.FLAG_OLD_AND_NEW) {
                            String date_selected;
                            if ((monthOfYear >= 0 && monthOfYear < 9)
                                    && (dayOfMonth > 0 && dayOfMonth < 10))
                                date_selected = "0"
                                        + String.valueOf(dayOfMonth) + "-0"
                                        + String.valueOf(monthOfYear + 1) + "-"
                                        + String.valueOf(selectedYear);
                            else if (monthOfYear >= 0 && monthOfYear < 9)
                                date_selected = String.valueOf(dayOfMonth)
                                        + "-0"
                                        + String.valueOf(monthOfYear + 1) + "-"
                                        + String.valueOf(selectedYear);
                            else if (dayOfMonth > 0 && dayOfMonth < 10)
                                date_selected = "0"
                                        + String.valueOf(dayOfMonth) + "-"
                                        + String.valueOf(monthOfYear + 1) + "-"
                                        + String.valueOf(selectedYear);
                            else
                                date_selected = String.valueOf(dayOfMonth)
                                        + "-" + String.valueOf(monthOfYear + 1)
                                        + "-" + String.valueOf(selectedYear);
                            dateEditText.setText(date_selected);
                        }
                    }
                }, currentYear, currentMonth, currentDay);
        datepicker.show();
    }

    public static void showDatePickerDialog(Context context, final int dateFlg,
                                            final TextView dateTextView) {
        // Displays Date picker
        final Calendar c = Calendar.getInstance();
        currentYear = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH);
        currentDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datepicker = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear,
                                          int monthOfYear, int dayOfMonth) {
                        int year = selectedYear;
                        int month = monthOfYear;
                        int day = dayOfMonth;
                        if (dateFlg == FU.FLAG_ONLY_NEW) {
//                            if ((year != currentYear)
//                                    || (month < currentMonth && year == currentYear)
//                                    || (day < currentDay && year == currentYear && month <= currentMonth)) {
//                                // Toast.makeText(appContext,
//                                // "Please select proper date.",
//                                // Toast.LENGTH_SHORT).show();
//                                dateTextView
//                                        .setText(getCurrentDateTimeStamp(""));
//
//                            } else {
                                String date_selected;
                                if ((monthOfYear >= 0 && monthOfYear < 9)
                                        && (dayOfMonth > 0 && dayOfMonth < 10))
                                    date_selected = "0"
                                            + String.valueOf(dayOfMonth) + "-0"
                                            + String.valueOf(monthOfYear + 1)
                                            + "-"
                                            + String.valueOf(selectedYear);

                                else if (monthOfYear >= 0 && monthOfYear < 9)
                                    date_selected = String.valueOf(dayOfMonth)
                                            + "-0"
                                            + String.valueOf(monthOfYear + 1)
                                            + "-"
                                            + String.valueOf(selectedYear);

                                else if (dayOfMonth > 0 && dayOfMonth < 10)
                                    date_selected = "0"
                                            + String.valueOf(dayOfMonth) + "-"
                                            + String.valueOf(monthOfYear + 1)
                                            + "-"
                                            + String.valueOf(selectedYear);

                                else
                                    date_selected = String.valueOf(dayOfMonth)
                                            + "-"
                                            + String.valueOf(monthOfYear + 1)
                                            + "-"
                                            + String.valueOf(selectedYear);

                                dateTextView.setText(date_selected);
                          //  }
                        } else if (dateFlg == FU.FLAG_OLD_AND_NEW) {
                            String date_selected;
                            if ((monthOfYear >= 0 && monthOfYear < 9)
                                    && (dayOfMonth > 0 && dayOfMonth < 10))
                                date_selected = "0"
                                        + String.valueOf(dayOfMonth) + "-0"
                                        + String.valueOf(monthOfYear + 1) + "-"
                                        + String.valueOf(selectedYear);
                            else if (monthOfYear >= 0 && monthOfYear < 9)
                                date_selected = String.valueOf(dayOfMonth)
                                        + "-0"
                                        + String.valueOf(monthOfYear + 1) + "-"
                                        + String.valueOf(selectedYear);
                            else if (dayOfMonth > 0 && dayOfMonth < 10)
                                date_selected = "0"
                                        + String.valueOf(dayOfMonth) + "-"
                                        + String.valueOf(monthOfYear + 1) + "-"
                                        + String.valueOf(selectedYear);
                            else
                                date_selected = String.valueOf(dayOfMonth)
                                        + "-" + String.valueOf(monthOfYear + 1)
                                        + "-" + String.valueOf(selectedYear);
                            dateTextView.setText(date_selected);
                        }
                    }
                }, currentYear, currentMonth, currentDay);
        datepicker.show();
    }

    public static void showDatePickerDialogYYMMDD(Context context, final int dateFlg,
                                            final TextView dateTextView) {
        // Displays Date picker
        final Calendar c = Calendar.getInstance();
        currentYear = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH);
        currentDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datepicker = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear,
                                          int monthOfYear, int dayOfMonth) {
                        int year = selectedYear;
                        int month = monthOfYear;
                        int day = dayOfMonth;
                        if (dateFlg == FU.FLAG_ONLY_NEW) {
                            if ((year != currentYear)
                                    || (month < currentMonth && year == currentYear)
                                    || (day < currentDay && year == currentYear && month <= currentMonth)) {
                                // Toast.makeText(appContext,
                                // "Please select proper date.",
                                // Toast.LENGTH_SHORT).show();
                                dateTextView
                                        .setText(getCurrentDateTimeStamp(""));

                            } else {
                                String date_selected;
                                if ((monthOfYear >= 0 && monthOfYear < 9)
                                        && (dayOfMonth > 0 && dayOfMonth < 10))
                                             date_selected = String.valueOf(selectedYear) + "-0"
                                            + String.valueOf(monthOfYear + 1)
                                            + "-"
                                            +"0"+ String.valueOf(dayOfMonth) ;

                                else if (monthOfYear >= 0 && monthOfYear < 9)
                                    date_selected = String.valueOf(selectedYear)
                                            + "-0"
                                            + String.valueOf(monthOfYear + 1)
                                            + "-"
                                            + String.valueOf(dayOfMonth);

                                else if (dayOfMonth > 0 && dayOfMonth < 10)
                                    date_selected =  String.valueOf(selectedYear)+"-"
                                            + String.valueOf(monthOfYear + 1)
                                            + "-"
                                            + "0"
                                            + String.valueOf(dayOfMonth) ;

                                else
                                    date_selected = String.valueOf(selectedYear)
                                            + "-"
                                            + String.valueOf(monthOfYear + 1)
                                            + "-"
                                            + String.valueOf(dayOfMonth);

                                dateTextView.setText(date_selected);
                            }
                        } else if (dateFlg == FU.FLAG_OLD_AND_NEW) {
                            String date_selected;
                            if ((monthOfYear >= 0 && monthOfYear < 9)
                                    && (dayOfMonth > 0 && dayOfMonth < 10))
                                date_selected = String.valueOf(selectedYear) + "-0"
                                        + String.valueOf(monthOfYear + 1)
                                        + "-"
                                        +"0"+ String.valueOf(dayOfMonth) ;

                            else if (monthOfYear >= 0 && monthOfYear < 9)
                                date_selected = String.valueOf(selectedYear)
                                        + "-0"
                                        + String.valueOf(monthOfYear + 1)
                                        + "-"
                                        + String.valueOf(dayOfMonth);

                            else if (dayOfMonth > 0 && dayOfMonth < 10)
                                date_selected =  String.valueOf(selectedYear)+"-"
                                        + String.valueOf(monthOfYear + 1)
                                        + "-"
                                        + "0"
                                        + String.valueOf(dayOfMonth) ;

                            else
                                date_selected = String.valueOf(selectedYear)
                                        + "-"
                                        + String.valueOf(monthOfYear + 1)
                                        + "-"
                                        + String.valueOf(dayOfMonth);

                            dateTextView.setText(date_selected);
                        }
                    }
                }, currentYear, currentMonth, currentDay);
        datepicker.show();
    }

    public static String convertTo24HoursFormat(String twelveHourTime)
            throws ParseException {
        // Returns date in 24 hour format
        return TWENTY_FOUR_TF.format(TWELVE_TF.parse(twelveHourTime));
    }

    public static String convertTo12HoursFormat(String twentyFourHourTime)
            throws ParseException {
        // Returns date in 24 hour format
        return TWELVE_TF.format(TWENTY_FOUR_TF.parse(twentyFourHourTime));
    }

    public static String getNextToDate(int days) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, days);
        String date_selected = "";
        int year = c.get(Calendar.YEAR);
        int monthOfYear = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        if ((monthOfYear >= 0 && monthOfYear < 9)
                && (dayOfMonth > 0 && dayOfMonth < 10))
            date_selected = String.valueOf(year) + "-0"
                    + String.valueOf(monthOfYear + 1) + "-0"
                    + String.valueOf(dayOfMonth);
        else if (monthOfYear >= 0 && monthOfYear < 9)
            date_selected = String.valueOf(year) + "-0"
                    + String.valueOf(monthOfYear + 1) + "-"
                    + String.valueOf(dayOfMonth);
        else if (dayOfMonth > 0 && dayOfMonth < 10)
            date_selected = String.valueOf(year) + "-"
                    + String.valueOf(monthOfYear + 1) + "-0"
                    + String.valueOf(dayOfMonth);
        else
            date_selected = String.valueOf(year) + "-"
                    + String.valueOf(monthOfYear + 1) + "-"
                    + String.valueOf(dayOfMonth);
        Log.i("ECS", "date_selected " + date_selected);

        return date_selected;

    }

    public static String getd_m_Y(String strYMDDate) {

        return "";
    }

    public static int roundTo5(double n) {
        return (int) Math.round(n / 5) * 5;
    }

    public static void showDatePickerDialogNew(final Context context, final int dateFlg,
                                               final TextView dateTextView) {
        // Displays Date picker
        final Calendar c = Calendar.getInstance();
        currentYear = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH);
        currentDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datepicker = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear,
                                          int monthOfYear, int dayOfMonth) {
                        int year = selectedYear;
                        int month = monthOfYear;
                        int day = dayOfMonth;

                            if ((year != currentYear)
                                    || (month < currentMonth && year == currentYear)
                                    || (day < currentDay && year == currentYear && month <= currentMonth)) {
                                LTU.TIL(context,"","Please select proper date.");
                                dateTextView
                                        .setText(getCurrentDateTimeStamp(DMY));

                            } else {
                                String date_selected;
                                if ((monthOfYear >= 0 && monthOfYear < 9)
                                        && (dayOfMonth > 0 && dayOfMonth < 10))
                                    date_selected = "0"
                                            + String.valueOf(dayOfMonth) + "-0"
                                            + String.valueOf(monthOfYear + 1)
                                            + "-"
                                            + String.valueOf(selectedYear);

                                else if (monthOfYear >= 0 && monthOfYear < 9)
                                    date_selected = String.valueOf(dayOfMonth)
                                            + "-0"
                                            + String.valueOf(monthOfYear + 1)
                                            + "-"
                                            + String.valueOf(selectedYear);

                                else if (dayOfMonth > 0 && dayOfMonth < 10)
                                    date_selected = "0"
                                            + String.valueOf(dayOfMonth) + "-"
                                            + String.valueOf(monthOfYear + 1)
                                            + "-"
                                            + String.valueOf(selectedYear);

                                else
                                    date_selected = String.valueOf(dayOfMonth)
                                            + "-"
                                            + String.valueOf(monthOfYear + 1)
                                            + "-"
                                            + String.valueOf(selectedYear);

                                dateTextView.setText(date_selected);
                            }
                        }
                }, currentYear, currentMonth, currentDay);
        datepicker.show();
    }
}
