package com.visitorapp.bloominfotech.views.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.models.PostResponse;
import com.visitorapp.bloominfotech.models.ResponseLogout;
import com.visitorapp.bloominfotech.presenter.logout.LogoutPresenter;
import com.visitorapp.bloominfotech.presenter.logout.LogoutPresenterImpl;
import com.visitorapp.bloominfotech.presenter.logout.LogoutView;
import com.visitorapp.bloominfotech.utils.ProgressDialog;
import com.visitorapp.bloominfotech.utils.ViewUtils;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.os.Looper.getMainLooper;

/**
 * Created by hp on 10/19/2016.
 */
public class FragmentLogin extends Fragment implements LogoutView {

    View view;


    public static FragmentLogin newInstance() {
        FragmentLogin fragmentLogin = new FragmentLogin();
        return fragmentLogin;
    }


    @Bind(R.id.system_date)
    TextView system_date;


    @Bind(R.id.system_time)
    TextView system_time;
    Handler someHandler;
    Runnable r;

    ProgressDialog progressDialog;
    private static AlertDialog.Builder alert_box;
    LogoutPresenter logoutPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
 /*init butterknife*/
        ButterKnife.bind(this, view);

        logoutPresenter = new LogoutPresenterImpl(getActivity(), this);
        ((HomeActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((HomeActivity) getActivity()).mToolbarTitle.setText("Login");
/*show date*/
        Calendar calendar = Calendar.getInstance();
       /* SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");*/
        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        System.out.println(format.format(calendar.getTime()));
        String strdate = format.format(calendar.getTime());
        system_date.setText("Date : " + strdate);
/*show time*/
        someHandler = new Handler(getMainLooper());


        r = new Runnable() {
            public void run() {
                if (system_time != null)
                    system_time.setText("Time : " + new SimpleDateFormat("HH:mm:ss", Locale.US).format(new Date()));

                someHandler.postDelayed(this, 1000);
            }
        };
        someHandler.postDelayed(r, 1000);


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


        ConfirmDialog(getActivity());


    }

    public void ConfirmDialog(Context ctx) {// this Dialog box ask user to exit or not
        alert_box = new AlertDialog.Builder(ctx);
//		alert_box.setIcon(R.drawable.exitt);
        alert_box.setTitle("Confirm Logout");
        alert_box.setMessage("Are you sure you want to Logout ?");
        alert_box.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                logout();
                dialog.dismiss();
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

    private void logout() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getActivity().setFinishOnTouchOutside(true);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.90);
        int screenHeight = (int) (metrics.heightPixels * 0.60);
        dialog.setContentView(R.layout.dialog_flag);
        dialog.getWindow().setLayout(screenWidth, WindowManager.LayoutParams.WRAP_CONTENT);
        final EditText reason = (EditText) dialog.findViewById(R.id.dialog_id);
        TextView yes_action = (TextView) dialog.findViewById(R.id.yes_dialog);
        TextView no_action = (TextView) dialog.findViewById(R.id.no_dialog);

        yes_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String mReason = reason.getText().toString().trim();

                if (mReason.isEmpty()) {
                    ViewUtils.showMessage(getActivity(), "Please enter ID.");
                } else {
                    logoutPresenter.logoutMethod(mReason, "true");
                }


            }
        });

        no_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                dialog.dismiss();
            }
        });

        dialog.show();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        someHandler.removeCallbacks(r);
    }


    @Override
    public void onSuccess(ResponseLogout postResponse) {
        if (postResponse != null) {
            //    ViewUtils.showMessage(getActivity(), postResponse.getMessage());
            if (postResponse.getStatus().equalsIgnoreCase("accepted")) {
                ViewUtils.showMessage(getActivity(), postResponse.getMessage());
                ((HomeActivity) getActivity()).finish();
            } else {
                ViewUtils.showMessage(getActivity(), postResponse.getStatus());
            }
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

