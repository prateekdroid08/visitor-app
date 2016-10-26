package com.visitorapp.bloominfotech.presenter.companies;

import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;

/**
 * Created by hp on 10/25/2016.
 */
public interface CompanylistListener {

    void onSuccess(ResponseCompanies responseCompanies);

    void onError(String message);
}
