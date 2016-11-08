package com.visitorapp.bloominfotech.interfaces;

import com.visitorapp.bloominfotech.models.meeting.MeetingResponse;
import com.visitorapp.bloominfotech.models.purpose.PurposeAPIResponse;

/**
 * Created by hp on 10/25/2016.
 */
public interface OnMeetingItemClick {
    void OnMeetingItemClick(MeetingResponse meetingResponse, int position);
}
