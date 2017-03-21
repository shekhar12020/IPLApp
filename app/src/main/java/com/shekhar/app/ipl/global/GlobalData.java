package com.shekhar.app.ipl.global;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by shekhar on 21/03/17.
 * Contains Application Level Objects & Configuration
 */

public class GlobalData extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Make data available for offline
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    //Application UI data
    public static GlobalData mGlobalData;

    public static GlobalData getInstance() {

        if (mGlobalData == null) {
            mGlobalData = new GlobalData();
        }
        return mGlobalData;
    }


}