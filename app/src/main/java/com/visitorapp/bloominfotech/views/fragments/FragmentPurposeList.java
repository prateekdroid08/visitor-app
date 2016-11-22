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
import com.visitorapp.bloominfotech.adapters.PurposeAdapter;
import com.visitorapp.bloominfotech.constants.Constants;
import com.visitorapp.bloominfotech.interfaces.OnCompanyItemClick;
import com.visitorapp.bloominfotech.interfaces.OnPurposeItemClick;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;
import com.visitorapp.bloominfotech.models.eventbus.MessageEvent;
import com.visitorapp.bloominfotech.models.purpose.PurposeAPIResponse;
import com.visitorapp.bloominfotech.presenter.companies.CompanyListView;
import com.visitorapp.bloominfotech.presenter.companies.CompanylistPresenter;
import com.visitorapp.bloominfotech.presenter.companies.CompanylistPresenterImpl;
import com.visitorapp.bloominfotech.presenter.purpose_of_visit.PurposePresenter;
import com.visitorapp.bloominfotech.presenter.purpose_of_visit.PurposePresenterImpl;
import com.visitorapp.bloominfotech.presenter.purpose_of_visit.PurposeView;
import com.visitorapp.bloominfotech.utils.SpacesItemDecoration;
import com.visitorapp.bloominfotech.utils.ViewUtils;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by hp on 10/25/2016.
 */
public class FragmentPurposeList extends Fragment implements PurposeView, OnPurposeItemClick {

    View view;

    @Bind(R.id.home_recycler_view)
    RecyclerView recyclerView;


    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.progressBar)
    CircleProgressBar progress;


    @Bind(R.id.error_message)
    TextView error_message;

    LinearLayoutManager mLayoutManager;
    PurposePresenter purposePresenter;
    PurposeAdapter mAdapter;
    int Pageindex = 0;

    PurposeAPIResponse purposeAPIResponse = new PurposeAPIResponse();

    boolean IsSwipeRefreshLayoutActive = false;

    public static FragmentPurposeList newInstance() {
        FragmentPurposeList fragmentCompanyList = new FragmentPurposeList();
        return fragmentCompanyList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_companies, container, false);

        /*init butterknife*/
        ButterKnife.bind(this, view);

        purposePresenter = new PurposePresenterImpl(getActivity(), this);

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

        /*setting adapter*/
//        mAdapter = new PurposeAdapter(getActivity(), purposeAPIResponse, this);
//        recyclerView.setAdapter(mAdapter);


        /*Swipe refresh layout*/
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                IsSwipeRefreshLayoutActive = true;
                getPurposeListData();
            }


        });

        getPurposeListData();

        return view;
    }

    private void getPurposeListData() {
        purposePresenter.getAllPurpose();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSwipeRefreshLayout != null) {
            if (mSwipeRefreshLayout.isRefreshing() == true) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }

        ButterKnife.unbind(this);
    }


    @Override
    public void onSuccess(PurposeAPIResponse purposeAPIResponse) {
        if (mSwipeRefreshLayout != null) {
            if (mSwipeRefreshLayout.isRefreshing() == true) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
        if (purposeAPIResponse != null) {
            this.purposeAPIResponse.setPurposeList(purposeAPIResponse.getPurposeList());
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onError(String message) {
        if (mSwipeRefreshLayout != null) {
            if (mSwipeRefreshLayout.isRefreshing() == true) {
                mSwipeRefreshLayout.setRefreshing(false);
            }


        }
        ViewUtils.showMessage(getActivity(), message);
    }

    @Override
    public void showProgress() {
        if (IsSwipeRefreshLayoutActive == false) {

            try {
                progress.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void hideProgressView() {
        try {
            progress.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnPurposeItemClick(PurposeAPIResponse purposeAPIResponse, int position) {

        EventBus.getDefault().postSticky(new MessageEvent(getResources().getString(R.string.purpose_selected)
                + "," + purposeAPIResponse.getPurposeList().get(position).getPurposeName() +
                "#" + purposeAPIResponse.getPurposeList().get(position).getPurposeID()));

        ((HomeActivity) getActivity()).visitorPresenter.oneStepBack();
    }

}

