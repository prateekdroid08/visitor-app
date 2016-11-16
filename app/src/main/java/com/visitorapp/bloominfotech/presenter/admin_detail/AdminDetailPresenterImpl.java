package com.visitorapp.bloominfotech.presenter.admin_detail;

import android.content.Context;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.models.admin_detail.ResponseAdminDetail;
import com.visitorapp.bloominfotech.presenter.admin_login.AdminLoginInteractor;
import com.visitorapp.bloominfotech.presenter.admin_login.AdminLoginInteractorImpl;
import com.visitorapp.bloominfotech.presenter.admin_login.AdminLoginView;
import com.visitorapp.bloominfotech.utils.AppUtils;

/**
 * Created by hp on 10/24/2016.
 */
public class AdminDetailPresenterImpl implements  AdminDetailPresenter,AdminDetailListener{

    AdminDetailView adminDetailView;
    Context context;
    AdminDetailInteractor adminDetailInteractor;

    public AdminDetailPresenterImpl(Context context,   AdminDetailView adminDetailView) {
        this.context = context;
        this.adminDetailView = adminDetailView;
        adminDetailInteractor = new AdminDetailInteractorImpl();
    }

    @Override
    public void onSuccess(ResponseAdminDetail responseAdminDetail) {
        adminDetailView.hideProgressView();
        adminDetailView.onSuccess(responseAdminDetail);
    }

    @Override
    public void onError(String message) {
        adminDetailView.hideProgressView();
        adminDetailView.onError(message);
    }

    @Override
    public void getadminDetails(String sort, String srchDate, String srchDateTo, String CompanyID, String MeetingID, String page) {
        if (AppUtils.isNetworkConnected(context)) {
            adminDetailView.showProgress();
            adminDetailInteractor.getadminDetailsAPI(sort, srchDate,srchDateTo,CompanyID,MeetingID,page, this);
        } else {

            adminDetailView.onError(context.getString(R.string.no_internet));
        }
    }
}
