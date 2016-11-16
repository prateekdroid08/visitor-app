package com.visitorapp.bloominfotech.presenter.admin_login;

import com.visitorapp.bloominfotech.models.admin_login.ResponseAdminLogin;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;

/**
 * Created by hp on 10/24/2016.
 */
public interface AdminLoginListener {

    void onSuccess(ResponseAdminLogin responseAdminLogin);

    void onError(String message);
}
