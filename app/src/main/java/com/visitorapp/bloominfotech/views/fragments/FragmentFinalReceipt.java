package com.visitorapp.bloominfotech.views.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.adapters.CompanyAdapter;
import com.visitorapp.bloominfotech.adapters.ReceiptAdapter;
import com.visitorapp.bloominfotech.interfaces.OnReceiptItemClickListener;
import com.visitorapp.bloominfotech.models.FinalReceiptModel;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;
import com.visitorapp.bloominfotech.models.form_response.ResponseVisitorForm;
import com.visitorapp.bloominfotech.presenter.companies.CompanylistPresenter;
import com.visitorapp.bloominfotech.presenter.final_receipt.ReceiptPresenter;
import com.visitorapp.bloominfotech.presenter.final_receipt.ReceiptPresenterImpl;
import com.visitorapp.bloominfotech.presenter.final_receipt.ReceiptView;
import com.visitorapp.bloominfotech.utils.ProgressDialog;
import com.visitorapp.bloominfotech.utils.SpacesItemDecoration;
import com.visitorapp.bloominfotech.utils.ViewUtils;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hp on 11/7/2016.
 */

public class FragmentFinalReceipt extends Fragment implements ReceiptView, OnReceiptItemClickListener {

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
    ArrayList<FinalReceiptModel> rowItems = new ArrayList<FinalReceiptModel>();

    ProgressDialog progressDialog;


    public static FragmentFinalReceipt newInstance() {
        FragmentFinalReceipt fragmentFinalReceipt = new FragmentFinalReceipt();
        return fragmentFinalReceipt;
    }

    ReceiptAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_final_receipt, container, false);
 /*init butterknife*/
        ButterKnife.bind(this, view);

        ((HomeActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((HomeActivity) getActivity()).mToolbarTitle.setText("");
        receiptPresenter = new ReceiptPresenterImpl(getActivity(), this);
   /*recycler view properties*/
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        // 3. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        } else {
            recyclerView.addItemDecoration(new SpacesItemDecoration(0));
        }

        recyclerView.setHasFixedSize(true);

        /*Swipe refresh layout*/
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                IsSwipeRefreshLayoutActive = true;
                getvalueFromserver();
            }


        });


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
        if (mSwipeRefreshLayout != null) {
            if (mSwipeRefreshLayout.isRefreshing() == true) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }

        if (responseVisitorForm != null) {


            if (rowItems.size() > 0) {
                rowItems.clear();
            }

            String nameStr = responseVisitorForm.getUserDetails().getFirstName() +" " + responseVisitorForm.getUserDetails().getLastName();
            String companuStr = responseVisitorForm.getCompanyDetails().getCompanyName();
            String purposestr = responseVisitorForm.getPurposeDetails().getPurposeName();
            String uniqueKey = responseVisitorForm.getUniqueKey();

            String createdOn = responseVisitorForm.getCreatedOn();

            String dateAsString = "";
            String timeAsString = "";

            String ImageUrl = "http://www.extenalbloominfotechinc.com/UniqueKeys/" + uniqueKey + ".jpg";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date = null;
            try {
                date = dateFormat.parse(createdOn);

                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");

                dateAsString = dateFormatter.format(date);
                timeAsString = timeFormatter.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            FinalReceiptModel item = new FinalReceiptModel(nameStr, companuStr, purposestr, uniqueKey, dateAsString, timeAsString, ImageUrl);
            rowItems.add(item);


            String otherMembers = responseVisitorForm.getUserMembers();
            List<String> items = Arrays.asList(otherMembers.split("\\s*,\\s*"));

            for (int i = 0; i < items.size(); i++) {
                item = new FinalReceiptModel(items.get(i), companuStr, purposestr, uniqueKey, dateAsString, timeAsString, ImageUrl);
                rowItems.add(item);
            }

            System.out.println("size : " + rowItems.size());
  /*setting adapter*/

            if (rowItems.size() > 0) {
                mAdapter = new ReceiptAdapter(getActivity(), rowItems, this);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
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
    public void hideProgress() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void onReceiptViewItemSelected(ArrayList<FinalReceiptModel> rowItems, int position) {

    }
}


