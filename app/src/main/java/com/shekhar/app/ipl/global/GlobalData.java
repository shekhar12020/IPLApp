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

    private String userId;
    private String profileName;
    private String profileEmail;
    private String profilePhoto;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileEmail() {
        return profileEmail;
    }

    public void setProfileEmail(String profileEmail) {
        this.profileEmail = profileEmail;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public static GlobalData getInstance() {

        if (mGlobalData == null) {
            mGlobalData = new GlobalData();
        }
        return mGlobalData;
    }


}