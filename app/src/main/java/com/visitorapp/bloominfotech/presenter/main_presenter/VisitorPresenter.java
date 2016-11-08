package com.visitorapp.bloominfotech.presenter.main_presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by hp on 10/15/2016.
 */
public interface VisitorPresenter {

    void navigateTo(Fragment fragment);

    void navigateWithBundle(Fragment fragment, Bundle bundle);

    void oneStepBack();

    void navigateReplacingCurrent(Fragment currentFragment, Fragment fragmentToNavigate);

    void navigateReplacingCurrentWithChild(Fragment currentFragment, Fragment fragmentToNavigate);

    void navigateReplacingCurrentWithBundle(Fragment currentFragment, Fragment fragmentToNavigate, Bundle bundle);

    void hideBackNavigation();

    void showBackNavigation();

    void backToParent();

    void changeTitle(String title);
}
