package com.visitorapp.bloominfotech.presenter.companies;

import android.content.Context;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;
import com.visitorapp.bloominfotech.utils.AppUtils;

/**
 * Created by hp on 10/25/2016.
 */
public class CompanylistPresenterImpl implements CompanylistPresenter, CompanylistListener {

    CompanyListView companyListView;
    Context context;
    CompanyListinteractor companyListinteractor;

    public CompanylistPresenterImpl(Context context, CompanyListView companyListView) {
        this.context = context;
        this.companyListView = companyListView;
        companyListinteractor = new CompanylistInteractorImpl();
    }


    @Override
    public void onSuccess(ResponseCompanies responseCompanies) {
        companyListView.hideProgressView();
        companyListView.onSuccess(responseCompanies);
    }

    @Override
    public void onError(String message) {
        companyListView.hideProgressView();
        companyListView.onError(message);
    }

    @Override
    public void getCompanyList(String companyID, int page, boolean getAll) {
        if (AppUtils.isNetworkConnected(context)) {
            companyListView.showProgress();
            companyListinteractor.getCompanyListAPI(companyID, page, getAll, this);
        } else {

            companyListView.onError(context.getString(R.string.no_internet));
        }
    }
}
