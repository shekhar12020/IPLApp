package com.shekhar.app.ipl.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shekhar.app.ipl.R;
import com.shekhar.app.ipl.dialog.CustomProgressDialog;
import com.shekhar.app.ipl.util.CommonMethod;

/**
 * Created by shekhar on 17/03/17.
 */
public class BaseFragment extends Fragment {

    private Dialog spinningDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        spinningDialog = CustomProgressDialog.showProgressDialog(getActivity());
        spinningDialog.setCancelable(false);
    }

    protected void showSnackBar(View view, String message, boolean isSuccess) {

        Snackbar snack = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        View snackBarView = snack.getView();
        if (isSuccess) {
            snackBarView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gal_color_pale_green));
        } else {
            snackBarView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gal_color_amber));
        }
        snack.show();

        CommonMethod.hideKeyboard(getActivity());
    }

    protected void showActivitySpinner() {
        if (spinningDialog != null) spinningDialog.show();
    }

    protected void dismissActivitySpinner() {
        if (spinningDialog != null) spinningDialog.dismiss();
    }

}
