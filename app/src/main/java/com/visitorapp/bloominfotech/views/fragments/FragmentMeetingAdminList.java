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
import com.visitorapp.bloominfotech.adapters.MeetingAdapter;
import com.visitorapp.bloominfotech.interfaces.OnMeetingItemClick;
import com.visitorapp.bloominfotech.models.eventbus.MessageEvent;
import com.visitorapp.bloominfotech.models.meeting.MeetingResponse;
import com.visitorapp.bloominfotech.presenter.meeting.MeetingPresenter;
import com.visitorapp.bloominfotech.presenter.meeting.MeetingPresenterImpl;
import com.visitorapp.bloominfotech.presenter.meeting.MeetingView;
import com.visitorapp.bloominfotech.utils.SpacesItemDecoration;
import com.visitorapp.bloominfotech.utils.ViewUtils;
import com.visitorapp.bloominfotech.views.activity.AdminActivity;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by hp on 10/25/2016.
 */
public class FragmentMeetingAdminList extends Fragment implements MeetingView, OnMeetingItemClick {

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
    MeetingPresenter meetingPresenter;
    MeetingAdapter mAdapter;
    int Pageindex = 0;

    MeetingResponse meetingResponse = new MeetingResponse();

    boolean IsSwipeRefreshLayoutActive = false;

    public static FragmentMeetingAdminList newInstance() {
        FragmentMeetingAdminList fragmentCompanyList = new FragmentMeetingAdminList();
        return fragmentCompanyList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_companies, container, false);

        /*init butterknife*/
        ButterKnife.bind(this, view);

        meetingPresenter = new MeetingPresenterImpl(getActivity(), this);

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
        mAdapter = new MeetingAdapter(getActivity(), meetingResponse, this);
        recyclerView.setAdapter(mAdapter);


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
        meetingPresenter.getMeetingList("", Pageindex, true);
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
    public void onSuccess(MeetingResponse meetingResponse) {
        if (mSwipeRefreshLayout != null) {
            if (mSwipeRefreshLayout.isRefreshing() == true) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
        if (meetingResponse != null) {
            this.meetingResponse.setMeetingList(meetingResponse.getMeetingList());
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
    public void hideProgress() {
        try {
            progress.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnMeetingItemClick(MeetingResponse meetingResponse, int position) {
        EventBus.getDefault().postSticky(new MessageEvent(getResources().getString(R.string.meeting_selected)
                + "," + meetingResponse.getMeetingList().get(position).getName() +
                "#" + meetingResponse.getMeetingList().get(position).getMeetingID()));

        ((AdminActivity) getActivity()).visitorPresenter.oneStepBack();
    }
}

