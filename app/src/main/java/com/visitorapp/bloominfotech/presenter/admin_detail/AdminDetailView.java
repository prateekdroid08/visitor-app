package com.visitorapp.bloominfotech.presenter.admin_detail;

import com.visitorapp.bloominfotech.models.admin_detail.ResponseAdminDetail;
import com.visitorapp.bloominfotech.models.admin_login.ResponseAdminLogin;

/**
 * Created by hp on 10/24/2016.
 */
public interface AdminDetailView {

    void onSuccess(ResponseAdminDetail responseAdminDetail);

    void onError(String message);

    void showProgress();

    void hideProgressView();
}
