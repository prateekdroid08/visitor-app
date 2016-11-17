package com.visitorapp.bloominfotech.presenter.logout;

import com.visitorapp.bloominfotech.models.PostResponse;
import com.visitorapp.bloominfotech.models.ResponseLogout;
import com.visitorapp.bloominfotech.models.admin_login.ResponseAdminLogin;

/**
 * Created by hp on 11/17/2016.
 */

public interface LogoutListener {

    void onSuccess(ResponseLogout responseAdminLogin);

    void onError(String message);
}
