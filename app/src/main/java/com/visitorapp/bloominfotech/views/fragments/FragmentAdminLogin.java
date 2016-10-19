package com.visitorapp.bloominfotech.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import butterknife.ButterKnife;

/**
 * Created by hp on 10/19/2016.
 */
public class FragmentAdminLogin extends Fragment {

    View view;


    public static FragmentAdminLogin newInstance() {
        FragmentAdminLogin fragmentAdminLogin = new FragmentAdminLogin();
        return fragmentAdminLogin;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_login, container, false);
 /*init butterknife*/
        ButterKnife.bind(this, view);
        ((HomeActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((HomeActivity) getActivity()).mToolbarTitle.setText("Admin Login");

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}

