package com.visitorapp.bloominfotech.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.models.admin_login.ResponseAdminLogin;
import com.visitorapp.bloominfotech.presenter.admin_detail.AdminDetailPresenter;
import com.visitorapp.bloominfotech.presenter.admin_login.AdminLoginPresenter;
import com.visitorapp.bloominfotech.presenter.admin_login.AdminLoginPresenterImpl;
import com.visitorapp.bloominfotech.presenter.admin_login.AdminLoginView;
import com.visitorapp.bloominfotech.utils.AppUtils;
import com.visitorapp.bloominfotech.utils.ProgressDialog;
import com.visitorapp.bloominfotech.utils.ViewUtils;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hp on 10/19/2016.
 */
public class FragmentAdminLogin extends Fragment implements AdminLoginView {

    View view;

    ProgressDialog progressDialog;

    public static FragmentAdminLogin newInstance() {
        FragmentAdminLogin fragmentAdminLogin = new FragmentAdminLogin();
        return fragmentAdminLogin;
    }

    AdminLoginPresenter adminDetailPresenter;

    @Bind(R.id.email)
    EditText mEmail;

    @Bind(R.id.password)
    EditText mPassword;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_login, container, false);
 /*init butterknife*/
        ButterKnife.bind(this, view);
        ((HomeActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((HomeActivity) getActivity()).mToolbarTitle.setText("Admin Login");

        adminDetailPresenter = new AdminLoginPresenterImpl(getActivity(), this);


        return view;
    }

    @OnClick(R.id.btn_login)
    public void methodAdminloginButton(View view) {
        String stremail = mEmail.getText().toString().trim();
        String strpassword = mPassword.getText().toString().trim();
        if (stremail.isEmpty()) {
            ViewUtils.showMessage(getActivity(), "Please enter Email.");

            return;
        }

        if (!AppUtils.isEmailValid(mEmail.getText().toString().trim())) {
            ViewUtils.showMessage(getActivity(), "Please enter valid email id.");
            return;
        }

        if (strpassword.isEmpty()) {
            ViewUtils.showMessage(getActivity(), "Please enter Password.");

            return;
        }

        adminDetailPresenter.getadminLogin(stremail, strpassword);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void onSuccess(ResponseAdminLogin responseAdminLogin) {
        if (responseAdminLogin != null) {
            if (responseAdminLogin.getIsUserValid() == true) {
                ((HomeActivity) getActivity()).visitorPresenter.navigateReplacingCurrent(FragmentAdminLogin.newInstance(), FragmentAdminDetails.newInstance());
            } else
                ViewUtils.showMessage(getActivity(), responseAdminLogin.getErrorIfAny());
        }
    }

    @Override
    public void onError(String message) {
        ViewUtils.showMessage(getActivity(), message);
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
    }

    @Override
    public void hideProgressView() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }
}

