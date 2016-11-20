package com.visitorapp.bloominfotech.views.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.adapters.AdminDetailAdapter;
import com.visitorapp.bloominfotech.constants.Constants;
import com.visitorapp.bloominfotech.interfaces.OnAdminDetailItemClick;
import com.visitorapp.bloominfotech.models.admin_detail.FilterData;
import com.visitorapp.bloominfotech.models.admin_detail.ResponseAdminDetail;
import com.visitorapp.bloominfotech.models.admin_detail.UserList;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;
import com.visitorapp.bloominfotech.models.eventbus.MessageEvent;
import com.visitorapp.bloominfotech.presenter.admin_detail.AdminDetailPresenter;
import com.visitorapp.bloominfotech.presenter.admin_detail.AdminDetailPresenterImpl;
import com.visitorapp.bloominfotech.presenter.admin_detail.AdminDetailView;
import com.visitorapp.bloominfotech.utils.SpacesItemDecoration;
import com.visitorapp.bloominfotech.utils.ViewUtils;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by hp on 11/16/2016.
 */

public class FragmentAdminDetails extends Fragment implements AdminDetailView, OnAdminDetailItemClick {

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
    private static AlertDialog.Builder alert_box;

    boolean hitAPIFromFilterYesOrNo = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_detail, container, false);
 /*init butterknife*/
        ButterKnife.bind(this, view);

        Constants.page = 0;

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
        mAdapter = new AdminDetailAdapter(getActivity(), lstjob, this);
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

        hitAPIFromFilterYesOrNo = false;

        adminDetailPresenter.getadminDetails(Constants.sort, filterData.getSrchDate(), filterData.getSrchDateTo()
                , filterData.getCompanyID(), filterData.getMeetingID(), String.valueOf(Constants.page));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

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
            if (hitAPIFromFilterYesOrNo == true) {
                if (responseAdminDetail.getUserLists().size() > 0) {
                    if (lstjob.size() > 0) {
                        lstjob.clear();
                    }
                }
            }


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

    @OnClick(R.id.Logout_admin)
    public void methodAdminLogout(View view) {
        ConfirmDialog(getActivity());

    }


    @OnClick(R.id.refresh)
    public void methodAdminRefresh(View view) {
        hitAPIFromFilterYesOrNo = false;
        Constants.page = 0;
        filterData = new FilterData();
        if (lstjob.size() > 0) {
            lstjob.clear();
        }
        getAdminDetails();

    }

    @Override
    public void onStart() {
        super.onStart();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().registerSticky(this);
        }
    }

    FilterData filterData = new FilterData();

    public void onEventMainThread(FilterData filterData) {
        this.filterData = filterData;
        Constants.page = 0;
   /*     if (lstjob.size() > 0) {
            lstjob.clear();
        }*/

        hitAPIFromFilterYesOrNo = true;
        adminDetailPresenter.getadminDetails(Constants.sort, filterData.getSrchDate(), filterData.getSrchDateTo(),
                filterData.getCompanyID(), filterData.getMeetingID()
                , String.valueOf(Constants.page));
    }

    @Override
    public void onAdminDetailItemClicked(ArrayList<UserList> lstEvt, int position) {

        String visitor_id = "";
        String CreatedOn = "";

        String ComanyName = "", Carnumber = "";
        String name = "";
        String PurposeName = "";
        String MeetingWith = "";
        String TimeIN = "";
        String TimeOut = "";
        String TimeSpent = "";


        if (lstEvt.get(position).getUniqueKey() != null)
            visitor_id = lstEvt.get(position).getUniqueKey();

        if (lstEvt.get(position).getCreatedOn() != null)
            CreatedOn = lstEvt.get(position).getCreatedOn();

        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
            //   SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy");
            System.out.println(format.format(calendar.getTime()));
            CreatedOn = format.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (lstEvt.get(position).getUserDetails().getFirstName() != null) {
            if (lstEvt.get(position).getUserDetails().getLastName() != null) {
                name = lstEvt.get(position).getUserDetails().getFirstName() + " " +
                        lstEvt.get(position).getUserDetails().getLastName();
            } else {
                name = lstEvt.get(position).getUserDetails().getFirstName();
            }
        }

        if (lstEvt.get(position).getCompanyDetails().getCompanyName() != null)
            ComanyName = lstEvt.get(position).getCompanyDetails().getCompanyName();

        if (lstEvt.get(position).getUserDetails().getCarNumber() != null)
            Carnumber = (String) lstEvt.get(position).getUserDetails().getCarNumber();

        if (lstEvt.get(position).getPurposeDetails().getPurposeName() != null)
            PurposeName = lstEvt.get(position).getPurposeDetails().getPurposeName();

        if (lstEvt.get(position).getMeetingDetails().getName() != null)
            MeetingWith = lstEvt.get(position).getMeetingDetails().getName();

        if (lstEvt.get(position).getTimeIn() != null)
            TimeIN = lstEvt.get(position).getTimeIn();

        if (lstEvt.get(position).getTimeOut() != null)
            TimeOut = lstEvt.get(position).getTimeOut();

        if (lstEvt.get(position).getTimeSpent() != null)
            TimeSpent = lstEvt.get(position).getTimeSpent();


        Bundle bundle = new Bundle();
        bundle.putString("visitor_id", visitor_id);
        bundle.putString("CreatedOn", CreatedOn);
        bundle.putString("ComanyName", ComanyName);
        bundle.putString("Carnumber", Carnumber);
        bundle.putString("name", name);
        bundle.putString("PurposeName", PurposeName);
        bundle.putString("MeetingWith", MeetingWith);
        bundle.putString("TimeIN", TimeIN);
        bundle.putString("TimeOut", TimeOut);
        bundle.putString("TimeSpent", TimeSpent);

        ((HomeActivity) getActivity()).visitorPresenter.navigateWithBundle(FragmentAdminFullDetails.newInstance(), bundle);


    }

    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                    // handle back button
                    ConfirmDialog(getActivity());

                    return true;

                }

                return false;
            }
        });
    }


    public void ConfirmDialog(Context ctx) {// this Dialog box ask user to exit or not
        alert_box = new AlertDialog.Builder(ctx);
//		alert_box.setIcon(R.drawable.exitt);
        alert_box.setTitle("Confirm Logout");
        alert_box.setMessage("Are you sure you want to Logout ?");
        alert_box.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                ((HomeActivity) getActivity()).visitorPresenter.navigateReplacingCurrent(FragmentAdminDetails.newInstance(), FragmentLogin.newInstance());
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

}


