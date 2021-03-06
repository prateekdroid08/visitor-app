package com.visitorapp.bloominfotech.presenter.final_receipt;

import com.visitorapp.bloominfotech.models.PostResponse;
import com.visitorapp.bloominfotech.models.form_response.ResponseVisitorForm;

/**
 * Created by hp on 11/7/2016.
 */

public interface ReceiptView {

    void onSuccess(ResponseVisitorForm responseVisitorForm);

    void onError(String message);

    void showProgress();

    void hideProgress();
}
