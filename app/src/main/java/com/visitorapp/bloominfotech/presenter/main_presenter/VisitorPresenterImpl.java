package com.visitorapp.bloominfotech.presenter.main_presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.VisitorActivity;

/**
 * Created by hp on 10/15/2016.
 */
public class VisitorPresenterImpl implements VisitorPresenter{



    Context ctx;
    VisitorView visitorView;

    public VisitorPresenterImpl(Context ctx,  VisitorView visitorView) {
        this.ctx = ctx;
        this.visitorView = visitorView;

    }

    @Override
    public void navigateTo(Fragment fragment) {
        FragmentTransaction fts = ((VisitorActivity) ctx).getSupportFragmentManager().beginTransaction();
        fts.add(R.id.fragmentHolder, fragment);
        fts.addToBackStack(fragment.getClass().getSimpleName());
        fts.commit();
    }

    @Override
    public void navigateWithBundle(Fragment fragment, Bundle bundle) {
        FragmentTransaction fts = ((VisitorActivity) ctx).getSupportFragmentManager().beginTransaction();
        fts.replace(R.id.fragmentHolder, fragment);
        fragment.setArguments(bundle);
        fts.addToBackStack(fragment.getClass().getSimpleName());
        fts.commit();
    }

    @Override
    public void navigateReplacingCurrentWithBundle(Fragment currentFragment, Fragment fragmentToNavigate, Bundle bundle) {
        fragmentToNavigate.setArguments(bundle);
        FragmentTransaction fts = ((VisitorActivity) ctx).getSupportFragmentManager().beginTransaction();
        ((VisitorActivity) ctx).getSupportFragmentManager().popBackStack();
        fts.replace(R.id.fragmentHolder, fragmentToNavigate);
        fts.addToBackStack(fragmentToNavigate.getClass().getSimpleName());
        fts.remove(currentFragment).commit();
    }

    @Override
    public void oneStepBack() {
        FragmentTransaction fts = ((VisitorActivity) ctx).getSupportFragmentManager().beginTransaction();
        FragmentManager fragmentManager = ((VisitorActivity) ctx).getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() >= 2) {
            fragmentManager.popBackStackImmediate();
            fts.commit();
        } else {
            ((VisitorActivity) ctx).finish();
        }
    }

    @Override
    public void navigateReplacingCurrent(Fragment currentFragment, Fragment fragmentToNavigate) {
        FragmentTransaction fts = ((VisitorActivity) ctx).getSupportFragmentManager().beginTransaction();
        ((VisitorActivity) ctx).getSupportFragmentManager().popBackStack();
        fts.replace(R.id.fragmentHolder, fragmentToNavigate);
        fts.addToBackStack(fragmentToNavigate.getClass().getSimpleName());
        fts.remove(currentFragment).commit();
    }

    @Override
    public void navigateReplacingCurrentWithChild(Fragment currentFragment, Fragment fragmentToNavigate) {
        FragmentTransaction fts = ((VisitorActivity) ctx).getSupportFragmentManager().beginTransaction();
        ((VisitorActivity) ctx).getSupportFragmentManager().popBackStack();
        fts.replace(R.id.fragmentHolder, fragmentToNavigate);
        fts.addToBackStack(fragmentToNavigate.getClass().getSimpleName());
        fts.remove(currentFragment).commit();
    }

    @Override
    public void hideBackNavigation() {
        visitorView.hideNavigationButton();
    }

    @Override
    public void showBackNavigation() {
        visitorView.showNavigationButton();
    }

    @Override
    public void backToParent() {
        FragmentTransaction fts = ((VisitorActivity) ctx).getSupportFragmentManager().beginTransaction();
        FragmentManager fragmentManager = ((VisitorActivity) ctx).getSupportFragmentManager();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStackImmediate();
        }
        fts.commit();
    }

    @Override
    public void changeTitle(String title) {
        visitorView.changeTitle(title);
    }

}
