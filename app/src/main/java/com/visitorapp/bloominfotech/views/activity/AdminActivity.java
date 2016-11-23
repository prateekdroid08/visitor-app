package com.visitorapp.bloominfotech.views.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;

import com.visitorapp.bloominfotech.VisitorActivity;
import com.visitorapp.bloominfotech.views.fragments.FragmentAdminDetails;
import com.visitorapp.bloominfotech.views.fragments.FragmentAdminLogin;
import com.visitorapp.bloominfotech.views.fragments.FragmentLogin;

import java.util.List;

/**
 * Created by prateekarora on 23/11/16.
 */

public class AdminActivity extends VisitorActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        visitorPresenter.navigateTo(FragmentAdminLogin.newInstance());
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

    @Override
    public void onBackPressed() {

        FragmentTransaction fts = this.getSupportFragmentManager().beginTransaction();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 2) {
            fragmentManager.popBackStackImmediate();
            fts.commit();
        } else {
            if (fragmentManager.getBackStackEntryCount() == 2)
                ConfirmDialog(this);
            else
                finish();
        }

    }

    public void ConfirmDialog(Context ctx) {// this Dialog box ask user to exit or not
        AlertDialog.Builder alert_box = new AlertDialog.Builder(ctx);
//		alert_box.setIcon(R.drawable.exitt);
        alert_box.setTitle("Confirm Logout");
        alert_box.setMessage("Are you sure you want to Logout ?");
        alert_box.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                startActivity(new Intent(AdminActivity.this, HomeActivity.class));
                finish();
            }
        });
        alert_box.setNegativeButton("No", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });


        alert_box.show();
    }
}