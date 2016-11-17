package com.visitorapp.bloominfotech.presenter.logout;

import com.visitorapp.bloominfotech.models.PostResponse;
import com.visitorapp.bloominfotech.models.ResponseLogout;
import com.visitorapp.bloominfotech.models.admin_login.ResponseAdminLogin;

/**
 * Created by hp on 11/17/2016.
 */

public interface LogoutView {

    void onSuccess(ResponseLogout postResponse);

    void onError(String message);

    void showProgress();

    void hideProgressView();
}
