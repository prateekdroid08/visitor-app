package com.visitorapp.bloominfotech.presenter.purpose_of_visit;

import com.visitorapp.bloominfotech.models.purpose.PurposeAPIResponse;

/**
 * Created by prateekarora on 31/10/16.
 */
public interface OnPurposeListener {

    void onSuccess(PurposeAPIResponse purposeAPIResponse);

    void onError(String message);

}
