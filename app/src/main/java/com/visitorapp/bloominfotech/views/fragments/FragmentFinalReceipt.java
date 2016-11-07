package com.visitorapp.bloominfotech.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;
import com.visitorapp.bloominfotech.models.form_response.ResponseVisitorForm;
import com.visitorapp.bloominfotech.presenter.companies.CompanylistPresenter;
import com.visitorapp.bloominfotech.presenter.final_receipt.ReceiptPresenter;
import com.visitorapp.bloominfotech.presenter.final_receipt.ReceiptPresenterImpl;
import com.visitorapp.bloominfotech.presenter.final_receipt.ReceiptView;
import com.visitorapp.bloominfotech.utils.ProgressDialog;
import com.visitorapp.bloominfotech.utils.ViewUtils;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hp on 11/7/2016.
 */

public class FragmentFinalReceipt extends Fragment implements ReceiptView {

    View view;


    @Bind(R.id.home_recycler_view)
    RecyclerView recyclerView;


    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.progressBar)
    CircleProgressBar progress;


    @Bind(R.id.error_message)
    TextView error_message;
    ResponseVisitorForm responseVisitorForm = new ResponseVisitorForm();
    boolean IsSwipeRefreshLayoutActive = false;
    LinearLayoutManager mLayoutManager;
    ReceiptPresenter receiptPresenter;

    ProgressDialog progressDialog;


    public static FragmentFinalReceipt newInstance() {
        FragmentFinalReceipt fragmentFinalReceipt = new FragmentFinalReceipt();
        return fragmentFinalReceipt;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_final_receipt, container, false);
 /*init butterknife*/
        ButterKnife.bind(this, view);

        ((HomeActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((HomeActivity) getActivity()).mToolbarTitle.setText("");
        receiptPresenter = new ReceiptPresenterImpl(getActivity(), this);


        getvalueFromserver();

        return view;
    }

    private void getvalueFromserver() {

        receiptPresenter.getFinalreceipt("117201633131");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void onSuccess(ResponseVisitorForm responseVisitorForm) {

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
    public void hideProgress() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }
}


