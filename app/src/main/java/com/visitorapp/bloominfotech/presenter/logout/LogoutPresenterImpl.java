package com.visitorapp.bloominfotech.presenter.logout;

import android.content.Context;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.models.PostResponse;
import com.visitorapp.bloominfotech.models.ResponseLogout;
import com.visitorapp.bloominfotech.presenter.admin_login.AdminLoginInteractor;
import com.visitorapp.bloominfotech.presenter.admin_login.AdminLoginInteractorImpl;
import com.visitorapp.bloominfotech.presenter.admin_login.AdminLoginView;
import com.visitorapp.bloominfotech.utils.AppUtils;

/**
 * Created by hp on 11/17/2016.
 */

public class LogoutPresenterImpl implements LogoutPresenter, LogoutListener {


    LogoutView logoutView;
    Context context;
    LogoutInteractor logoutInteractor;

    public LogoutPresenterImpl(Context context, LogoutView logoutView) {
        this.context = context;
        this.logoutView = logoutView;
        logoutInteractor = new LogoutInteractorImpl();
    }


    @Override
    public void onSuccess(ResponseLogout responseLogout) {
        logoutView.hideProgressView();
        logoutView.onSuccess(responseLogout);
    }

    @Override
    public void onError(String message) {
        logoutView.hideProgressView();
        logoutView.onError(message);
    }

    @Override
    public void logoutMethod(String uniqueKey, String islogOutCommand) {
        if (AppUtils.isNetworkConnected(context)) {
            logoutView.showProgress();
            logoutInteractor.logoutMethod(uniqueKey, islogOutCommand, this);
        } else {

            logoutView.onError(context.getString(R.string.no_internet));
        }
    }
}
