package com.visitorapp.bloominfotech.interfaces;

import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;

/**
 * Created by hp on 10/25/2016.
 */
public interface OnCompanyItemClick {
    void onCompanyItemSelected(ResponseCompanies responseCompanies, int position);
}
