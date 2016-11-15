package com.visitorapp.bloominfotech.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.visitorapp.bloominfotech.R;

import java.lang.ref.WeakReference;

/**
 * Created by Rivendell on 05/09/16.
 */
public class ViewUtils {

    public static void showMessage(Context context, String message) {
        try {
            WeakReference<Context> contextWeakReference = new WeakReference<Context>(context);
            Toast.makeText(contextWeakReference.get(), message, Toast.LENGTH_SHORT).show();
        }catch(Exception e){

        }
    }


    public static void showSnakBar(View view, Context context, String message) {
        WeakReference<Context> contextWeakReference = new WeakReference<Context>(context);
        Snackbar.make(view, message,
                Snackbar.LENGTH_SHORT)
                .show();
    }

    public static void showAlert(Context context, String message) {
  /*      AlertDialog.Builder alert = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
        alert.setMessage(message);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();*/


        AlertDialog.Builder customBuilder = new AlertDialog.Builder(context);
        customBuilder.setMessage(message);

        customBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = customBuilder.create();
        dialog.show();

        Button b = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        if(b != null)
            b.setTextColor(getColorWrapper(context,R.color.colorPrimary));


    }

    public static int getColorWrapper(Context context, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(id);
        } else {
            //noinspection deprecation
            return context.getResources().getColor(id);
        }
    }


}
