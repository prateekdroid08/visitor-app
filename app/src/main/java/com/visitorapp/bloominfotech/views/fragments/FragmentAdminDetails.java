package com.visitorapp.bloominfotech.views.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.adapters.AdminDetailAdapter;
import com.visitorapp.bloominfotech.adapters.CompanyAdapter;
import com.visitorapp.bloominfotech.constants.Constants;
import com.visitorapp.bloominfotech.models.admin_detail.ResponseAdminDetail;
import com.visitorapp.bloominfotech.models.admin_detail.UserList;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;
import com.visitorapp.bloominfotech.presenter.admin_detail.AdminDetailPresenter;
import com.visitorapp.bloominfotech.presenter.admin_detail.AdminDetailPresenterImpl;
import com.visitorapp.bloominfotech.presenter.admin_detail.AdminDetailView;
import com.visitorapp.bloominfotech.utils.SpacesItemDecoration;
import com.visitorapp.bloominfotech.utils.ViewUtils;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hp on 11/16/2016.
 */

public class FragmentAdminDetails extends Fragment implements AdminDetailView {

    View view;

    LinearLayoutManager mLayoutManager;

    @Bind(R.id.home_recycler_view)
    RecyclerView recyclerView;


    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.progressBar)
    CircleProgressBar progress;


    @Bind(R.id.error_message)
    TextView error_message;

    AdminDetailAdapter mAdapter;

    ArrayList<UserList> lstjob = new ArrayList<>();
    boolean IsSwipeRefreshLayoutActive = false;


    public static FragmentAdminDetails newInstance() {
        FragmentAdminDetails fragmentAdminDetails = new FragmentAdminDetails();
        return fragmentAdminDetails;
    }

    AdminDetailPresenter adminDetailPresenter;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_detail, container, false);
 /*init butterknife*/
        ButterKnife.bind(this, view);

        adminDetailPresenter = new AdminDetailPresenterImpl(getActivity(), this);


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
        mAdapter = new AdminDetailAdapter(getActivity(), lstjob);
        recyclerView.setAdapter(mAdapter);


        /*Swipe refresh layout*/
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                IsSwipeRefreshLayoutActive = true;
                Constants.page = 0;

                if (lstjob.size() > 0) {
                    lstjob.clear();
                }
                getAdminDetails();
            }


        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                loading = true;

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                // Recycle view scrolling up...


                if (dy < 0) {


                } else if (dy > 0) {
                    // Recycle view scrolling down...
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();


                    if (loading) {
                        int sum = visibleItemCount + pastVisiblesItems;
                        if (sum >= totalItemCount) {
                            loading = false;
                            Log.v("...", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
                            Constants.page++;
                            getAdminDetails();
                        }
                    }

                }
            }
        });


        getAdminDetails();

        return view;
    }

    private void getAdminDetails() {

        adminDetailPresenter.getadminDetails(Constants.sort, Constants.srchDate, Constants.srchDateTo, Constants.CompanyID, Constants.MeetingID, String.valueOf(Constants.page));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void onSuccess(ResponseAdminDetail responseAdminDetail) {
        if (mSwipeRefreshLayout != null) {
            if (mSwipeRefreshLayout.isRefreshing() == true) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
        if (responseAdminDetail != null) {
            //this.lstjob.setUserLists(responseAdminDetail.getUserLists());

            for (UserList data : responseAdminDetail.getUserLists()) {
                lstjob.add(data);
            }
            if (lstjob.size() > 0) {
                mAdapter.notifyDataSetChanged();
            } else {
                ViewUtils.showMessage(getActivity(), "No data Found.");
            }

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

    @OnClick(R.id.btn_Filter)
    public void methodAdminFilter(View view) {
        ((HomeActivity) getActivity()).visitorPresenter.navigateTo(FragmentFilter.newInstance());

    }
}


