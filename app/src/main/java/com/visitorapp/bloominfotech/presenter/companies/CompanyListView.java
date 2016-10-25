package com.visitorapp.bloominfotech.presenter.companies;

import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;

/**
 * Created by hp on 10/25/2016.
 */
public interface CompanyListView {

    void onSuccess(ResponseCompanies responseCompanies);

    void onError(String message);

    void showProgress();

    void hideProgressView();
}
