package com.shekhar.app.ipl.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

public class CustomProgressDialog {

	static ProgressDialog progress;

	public static Dialog showProgressDialog(Context ctx) {

		progress = new ProgressDialog(ctx);
		progress.setMessage("Please wait...");

		return progress;
	}

	public static void setMessage(String message) {
		progress.setMessage(message);
	}

}