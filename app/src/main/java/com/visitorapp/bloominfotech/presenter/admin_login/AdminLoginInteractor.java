package com.visitorapp.bloominfotech.presenter.admin_login;

/**
 * Created by hp on 10/24/2016.
 */
public interface AdminLoginInteractor {

    void getadminLogin(String Email,String Password,AdminLoginListener adminLoginListener);
}
