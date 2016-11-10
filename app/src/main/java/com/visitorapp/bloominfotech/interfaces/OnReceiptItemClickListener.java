package com.visitorapp.bloominfotech.interfaces;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.visitorapp.bloominfotech.models.FinalReceiptModel;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;
import com.visitorapp.bloominfotech.models.form_response.ResponseVisitorForm;

import java.util.ArrayList;

/**
 * Created by hp on 11/8/2016.
 */

public interface OnReceiptItemClickListener {

    void onReceiptViewItemSelected(ArrayList<FinalReceiptModel> rowItems, int position, LinearLayout ll,TextView tv,LinearLayout subconatiner);
}
