package com.visitorapp.bloominfotech.presenter.visitor_form;

import com.visitorapp.bloominfotech.models.PostResponse;
import com.visitorapp.bloominfotech.models.ResponseVistorForm;
import com.visitorapp.bloominfotech.models.form_response.ResponseVisitorForm;

/**
 * Created by hp on 10/24/2016.
 */
public interface OnVisitorListener {
    void onSuccess(ResponseVistorForm postResponse);

    void onError(String message);
}
