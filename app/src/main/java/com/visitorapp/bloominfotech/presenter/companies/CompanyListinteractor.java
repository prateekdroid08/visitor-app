package com.visitorapp.bloominfotech.presenter.companies;

/**
 * Created by hp on 10/25/2016.
 */
public interface CompanyListinteractor {

    void getCompanyListAPI(String companyID,int page,boolean getAll,CompanylistListener companylistListener);
}
