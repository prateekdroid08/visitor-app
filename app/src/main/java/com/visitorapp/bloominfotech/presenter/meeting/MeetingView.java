package com.visitorapp.bloominfotech.presenter.meeting;

import com.visitorapp.bloominfotech.models.meeting.MeetingResponse;

/**
 * Created by prateekarora on 31/10/16.
 */
public interface MeetingView {
    void onSuccess(MeetingResponse meetingResponse);

    void onError(String message);

    void showProgress();

    void hideProgress();
}
