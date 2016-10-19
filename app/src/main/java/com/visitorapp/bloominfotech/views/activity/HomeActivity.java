package com.visitorapp.bloominfotech.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.visitorapp.bloominfotech.VisitorActivity;
import com.visitorapp.bloominfotech.views.fragments.FragmentLogin;

import java.util.List;

/**
 * Created by hp on 10/19/2016.
 */
public class HomeActivity extends VisitorActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        visitorPresenter.navigateTo(FragmentLogin.newInstance());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            FragmentLogin fragmentLogin = new FragmentLogin();

            if (fragmentLogin == getVisibleFragment())
                fragmentLogin.onActivityResult(requestCode, resultCode, data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }

}
