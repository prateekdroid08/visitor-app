package com.visitorapp.bloominfotech.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.visitorapp.bloominfotech.R;


/**
 * Created by prateek arora on 02/07/15.
 */
public class ProgressDialog extends Dialog {
    Context ctx;

    public ProgressDialog(Context context) {
        super(context);
        this.ctx = context;
        initializeView(ctx);
    }

    public ProgressDialog(Context context, int theme) {
        super(context, theme);
        this.ctx = context;
        initializeView(ctx);
    }

    protected ProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.ctx = context;
        initializeView(ctx);
    }

    public void initializeView(Context ctx) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setCancelable(false);
        this.setContentView(R.layout.progress_dialog);

    }


}
