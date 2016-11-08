package com.visitorapp.bloominfotech.presenter.meeting;

/**
 * Created by prateekarora on 31/10/16.
 */
public interface MeetingInteractor {
    void getMeetingList(String companyID,int page,boolean getAll, OnMeetingListener onMeetingListener);
}
