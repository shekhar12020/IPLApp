package com.shekhar.app.ipl.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.gms.common.ConnectionResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by shekhar on 04/011/15.
 */
public class CommonMethod {

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isAndroidEmulator() {
        String product = Build.PRODUCT;
        boolean isEmulator = false;
        if (product != null) {
            isEmulator = product.equals("sdk") || product.contains("_sdk") || product.contains("sdk_");
        }
        return isEmulator;
    }

    public static int getDeviceScreenHeight(Activity _activity) {
        Display display = _activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;

        return height;
    }

    public static void hideKeyboard(Activity activity) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static int getNumberOfDaysBetweenTwoDate(String strDate1, String dateFormat1, String strDate2, String dateFormat2) {
        int months = 0;
        double AVERAGE_MILLIS_PER_DAY = 365.24 * 24 * 60 * 60 * 1000 / 365;

        Date date1, date2;
        SimpleDateFormat sdf1 = new SimpleDateFormat(dateFormat1, Locale.getDefault());
        SimpleDateFormat sdf2 = new SimpleDateFormat(dateFormat2, Locale.getDefault());
        SimpleDateFormat sdfYear = new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault());

        try {
            date1 = sdf1.parse(strDate1);
            date2 = sdf2.parse(strDate2);

//            int year1 = Integer.parseInt(sdfYear.format(date1));
//            int year2 = Integer.parseInt(sdfYear.format(date2));

            months = (int) ((date2.getTime() - date1.getTime()) / AVERAGE_MILLIS_PER_DAY);


        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return months;
    }

    public static int diffInDays(String strDate1, String dateFormat1, String strDate2, String dateFormat2) {

        int diffInDays = 0;
        Date date1, date2;
        SimpleDateFormat sdf1 = new SimpleDateFormat(dateFormat1, Locale.getDefault());
        SimpleDateFormat sdf2 = new SimpleDateFormat(dateFormat2, Locale.getDefault());

        try {
            date1 = sdf1.parse(strDate1);
            date2 = sdf2.parse(strDate2);
            diffInDays = (int) ((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
            DebugLog.d("diffInDays : " + diffInDays);

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return diffInDays;
    }

    public static String getNextPrevDate(String inputDate, String dateFormat, boolean isPrevDate) {
        String nextPrevDate = "";

        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());

        try {
            date = sdf.parse(inputDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            if (isPrevDate) {
                c.add(Calendar.DATE, -1);
            } else {
                c.add(Calendar.DATE, 1);
            }

            Date nextDate = c.getTime();
            nextPrevDate = sdf.format(nextDate);

            DebugLog.d("nextPrevDate : " + nextPrevDate);

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return nextPrevDate;
    }

}
