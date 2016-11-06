package com.visitorapp.bloominfotech.presenter.meeting;

import android.content.Context;

import com.visitorapp.bloominfotech.models.meeting.MeetingResponse;

/**
 * Created by prateekarora on 31/10/16.
 */
public class MeetingPresenterImpl implements MeetingPresenter, OnMeetingListener {

    Context context;
    MeetingView meetingView;
    MeetingInteractor meetingInteractor;

    public MeetingPresenterImpl(Context context, MeetingView meetingView) {
        this.context = context;
        this.meetingView = meetingView;
        meetingInteractor = new MeetingInteractorImpl();
    }

    @Override
    public void getMeetingList(String companyID, int page, boolean getAll) {
        meetingView.showProgress();
        meetingInteractor.getMeetingList(companyID, page, getAll, this);
    }

    @Override
    public void onSuccess(MeetingResponse meetingResponse) {
        meetingView.hideProgress();
        meetingView.onSuccess(meetingResponse);
    }

    @Override
    public void onError(String message) {
        meetingView.hideProgress();
        meetingView.onError(message);
    }
}
