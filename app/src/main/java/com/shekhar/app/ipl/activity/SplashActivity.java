package com.shekhar.app.ipl.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.shekhar.app.ipl.R;

/**
 * Created by shekhar on 04/01/16.
 */
public class SplashActivity extends BaseActivity {

    private static final int SPLASH_TIME_OUT = 1500;
    private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Used for force fully reset application (logout forcefully) set flag true for reset user
        resetApplication(false);

        switchToNextActivity();
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorPrimary;
    }

    //Validate user profile & decide where to move
    private void switchToNextActivity() {

        SharedPreferences UserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        isLogin = UserInfo.getBoolean("isLogin", false);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i;
                if (isLogin) {
                    i = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    i = new Intent(SplashActivity.this, MainActivity.class);
                }
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void resetApplication(boolean reset) {
        SharedPreferences ResetUserPreferences = this.getSharedPreferences("ResetUserPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = ResetUserPreferences.edit();
        boolean logoutUser = ResetUserPreferences.getBoolean("logoutUser", true);

        if (reset) {

            if (logoutUser) {

                editor.putBoolean("logoutUser", false).apply();

                SharedPreferences userInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                userInfo.edit().clear().apply();

                SharedPreferences gcmToken = PreferenceManager.getDefaultSharedPreferences(this);
                gcmToken.edit().clear().apply();

            }
        } else {
            try {
                editor.remove("logoutUser").apply();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
