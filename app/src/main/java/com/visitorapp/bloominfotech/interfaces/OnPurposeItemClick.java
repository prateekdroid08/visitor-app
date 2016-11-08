package com.visitorapp.bloominfotech.interfaces;

import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;
import com.visitorapp.bloominfotech.models.purpose.PurposeAPIResponse;

/**
 * Created by hp on 10/25/2016.
 */
public interface OnPurposeItemClick {
    void OnPurposeItemClick(PurposeAPIResponse purposeAPIResponse, int position);
}
