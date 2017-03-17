package com.shekhar.app.ipl.activity;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.shekhar.app.ipl.R;
import com.shekhar.app.ipl.dialog.AlertDialogStandard;
import com.shekhar.app.ipl.dialog.CustomProgressDialog;
import com.shekhar.app.ipl.util.CommonMethod;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public abstract class BaseActivity extends AppCompatActivity {


    private Context context;
    private FragmentManager fragmentManager;
    private Dialog spinningDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getFragmentManager();
        context = BaseActivity.this;

        spinningDialog = CustomProgressDialog.showProgressDialog(context);
        spinningDialog.setCancelable(false);

        setStatusBarColor(getStatusBarColor());
    }

    protected abstract int getStatusBarColor();

    private void setStatusBarColor(int color) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            if (color != 0) window.setStatusBarColor(ContextCompat.getColor(this, color));
            else window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
    }

    protected String ReadTextFile(String filename) {

        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        String text = "";
        try {
            inputStream = assetManager.open(filename);
            text = loadTextFile(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return text;
    }

    protected String loadTextFile(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];
        int len;
        while ((len = inputStream.read(bytes)) > 0)
            byteStream.write(bytes, 0, len);
        return new String(byteStream.toByteArray(), "UTF8");
    }

    //method used to show error messages
    protected void displayMessage(String errorString) {
        if (context != null) {
            showMyDialog(context.getString(R.string.app_name), errorString, context.getString(R.string.ok_button));
        }
    }

    //show common alert dialog
    protected void showMyDialog(String title, String message, String positiveButton) {
        AlertDialogStandard newDialog = AlertDialogStandard.newInstance(title, message, "", positiveButton);
        newDialog.show(fragmentManager, "show dialog");
    }

    protected void showSnackBar(View view, String message) {
        Snackbar.make(view, Html.fromHtml("<font color=\"#ffffff\">" + message + "</font>"), Snackbar.LENGTH_LONG).show();
        CommonMethod.hideKeyboard(BaseActivity.this);
    }

    protected void showSnackBar(View view, String message, boolean isSuccess) {

        Snackbar snack = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        View snackBarView = snack.getView();
        if (isSuccess) {
            snackBarView.setBackgroundColor(ContextCompat.getColor(BaseActivity.this, R.color.gal_color_pale_green));
        } else {
            snackBarView.setBackgroundColor(ContextCompat.getColor(BaseActivity.this, R.color.gal_color_amber));
        }
        snack.show();

        CommonMethod.hideKeyboard(BaseActivity.this);
    }

    protected void showActivitySpinner() {
        if (spinningDialog != null) spinningDialog.show();
    }

    protected void showActivitySpinner(String message) {
        CustomProgressDialog.setMessage(message);
        if (spinningDialog != null) spinningDialog.show();
    }

    protected void dismissActivitySpinner() {
        if (spinningDialog != null) spinningDialog.dismiss();
    }

    //Method used to parse error response from server
    protected String trimMessage(String json, String key) {
        String trimmedString;

        try {
            JSONObject obj = new JSONObject(json);
            trimmedString = obj.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return trimmedString;
    }

}

