package com.visitorapp.bloominfotech.presenter.final_receipt;

import android.content.Context;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.models.form_response.ResponseVisitorForm;
import com.visitorapp.bloominfotech.presenter.visitor_form.VisitorInteractor;
import com.visitorapp.bloominfotech.presenter.visitor_form.VisitorInteractorImpl;
import com.visitorapp.bloominfotech.presenter.visitor_form.VisitorView;
import com.visitorapp.bloominfotech.utils.AppUtils;

/**
 * Created by hp on 11/7/2016.
 */

public class ReceiptPresenterImpl implements  ReceiptPresenter,ReceiptListener{

    Context context;
    ReceiptView receiptView;
    ReceiptInteractor receiptInteractor;

    public ReceiptPresenterImpl(Context context, ReceiptView receiptView) {
        this.context = context;
        this.receiptView = receiptView;
        receiptInteractor = new ReceiptInteractorImpl();
    }


    @Override
    public void onSuccess(ResponseVisitorForm responseVisitorForm) {
        receiptView.hideProgress();
        receiptView.onSuccess(responseVisitorForm);
    }

    @Override
    public void onError(String message) {
        receiptView.hideProgress();
        receiptView.onError(message);
    }

    @Override
    public void getFinalreceipt(String uniqueKey) {
        if (AppUtils.isNetworkConnected(context)) {
            receiptView.showProgress();
            receiptInteractor.getFinalreceipt(uniqueKey, this);
        } else {

            receiptView.onError(context.getString(R.string.no_internet));
        }
    }
}
