package com.shekhar.app.ipl.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtilities {
    public static String app_settings = "app_settings";
    public static String isMobile = "use_mobile";
    public static final int REGISTRATION_TIMEOUT = 2 * 1000;

    public static boolean isInternet(Context context) {
        boolean isInternet = false;

        SharedPreferences preferences = context.getSharedPreferences(app_settings, Context.MODE_PRIVATE);
        Boolean useMobile = preferences.getBoolean(isMobile, true);

        if (useMobile) {
            if (isMobileDataConnected(context) || isWifiConnected(context)) {
                isInternet = true;
            } else {
                isInternet = false;
            }
        }else{
            if(isWifiConnected(context)){
                isInternet = true;
            }else{
                isInternet = false;
            }
        }

//		ConnectivityManager con = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//		if (con.getNetworkInfo(0) != null && con.getNetworkInfo(1) != null) {
//
//			if (con.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED
//					|| con.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING
//					|| con.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED
//					|| con.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING) {
//				isInternet = true;
//
//			} else if (con.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED
//					|| con.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
//				isInternet = false;
//
//			}
//		} else if (con.getNetworkInfo(0) != null) {
//
//			if (con.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED
//					|| con.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING) {
//				isInternet = true;
//
//			} else if (con.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED) {
//				Toast.makeText(context, "No network", Toast.LENGTH_LONG).show();
//				isInternet = false;
//
//			}
//		} else if (con.getNetworkInfo(1) != null) {
//
//			if (con.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED
//					|| con.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING) {
//				isInternet = true;
//
//			} else if (con.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
//				isInternet = false;
//
//			}
//		}
        return isInternet;
    }

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return networkInfo.isConnected();
    }

    public static boolean isMobileDataConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return networkInfo.isConnected();
    }


}