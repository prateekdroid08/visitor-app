package com.visitorapp.bloominfotech.presenter.logout;

/**
 * Created by hp on 11/17/2016.
 */

public interface LogoutInteractor {

    void logoutMethod(String uniqueKey,String islogOutCommand,LogoutListener logoutListener);
}
