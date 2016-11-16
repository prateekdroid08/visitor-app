package com.visitorapp.bloominfotech.presenter.admin_login;

import android.content.Context;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.models.admin_login.ResponseAdminLogin;
import com.visitorapp.bloominfotech.presenter.companies.CompanyListView;
import com.visitorapp.bloominfotech.presenter.companies.CompanyListinteractor;
import com.visitorapp.bloominfotech.presenter.companies.CompanylistInteractorImpl;
import com.visitorapp.bloominfotech.utils.AppUtils;

/**
 * Created by hp on 10/24/2016.
 */
public class AdminLoginPresenterImpl implements  AdminLoginPresenter,AdminLoginListener{


    AdminLoginView adminLoginView;
    Context context;
    AdminLoginInteractor adminLoginInteractor;

    public AdminLoginPresenterImpl(Context context, AdminLoginView adminLoginView) {
        this.context = context;
        this.adminLoginView = adminLoginView;
        adminLoginInteractor = new AdminLoginInteractorImpl();
    }

    @Override
    public void onSuccess(ResponseAdminLogin responseAdminLogin) {
        adminLoginView.hideProgressView();
        adminLoginView.onSuccess(responseAdminLogin);
    }

    @Override
    public void onError(String message) {
        adminLoginView.hideProgressView();
        adminLoginView.onError(message);
    }

    @Override
    public void getadminLogin(String Email, String Password) {
        if (AppUtils.isNetworkConnected(context)) {
            adminLoginView.showProgress();
            adminLoginInteractor.getadminLogin(Email, Password, this);
        } else {

            adminLoginView.onError(context.getString(R.string.no_internet));
        }
    }
}
