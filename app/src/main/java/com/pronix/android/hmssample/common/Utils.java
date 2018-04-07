package com.pronix.android.hmssample.common;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;

/**
 * Created by ravi on 1/11/2018.
 */

public class Utils {

    public static void showalert(Context context, String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        builder.show();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public static void hideProgress(android.app.AlertDialog progressBar)
    {
        if(progressBar.isShowing())
            progressBar.dismiss();

    }

    public static void hideProgress(Dialog dialog)
    {
        if(dialog.isShowing())
            dialog.dismiss();

    }

    public static String getQuotedString(String value)
    {
        return "'"+value+"'";
    }


}
