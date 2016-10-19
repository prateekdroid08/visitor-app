package com.visitorapp.bloominfotech.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hp on 10/19/2016.
 */
public class FragmentLogin extends Fragment {

    View view;


    public static FragmentLogin newInstance() {
        FragmentLogin fragmentLogin = new FragmentLogin();
        return fragmentLogin;
    }


    @Bind(R.id.system_date)
    TextView system_date;


    @Bind(R.id.system_time)
    TextView system_time;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
 /*init butterknife*/
        ButterKnife.bind(this, view);
        ((HomeActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((HomeActivity) getActivity()).mToolbarTitle.setText("Login");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
        System.out.println(format.format(calendar.getTime()));
        String strdate = format.format(calendar.getTime());
        system_date.setText("" + strdate);


        return view;
    }


    @OnClick(R.id.admin_login)
    public void methodAdminlogin(View view) {
        ((HomeActivity) getActivity()).visitorPresenter.navigateTo(FragmentAdminLogin.newInstance());

    }


    @OnClick(R.id.signIn)
    public void methodsignInScreen(View view) {

        ((HomeActivity) getActivity()).visitorPresenter.navigateTo(FragmentChooser.newInstance());
    }


    @OnClick(R.id.signOut)
    public void methodsignOutScreen(View view) {
        ((HomeActivity) getActivity()).finish();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}

