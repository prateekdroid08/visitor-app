package com.visitorapp.bloominfotech.models.meeting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateekarora on 31/10/16.
 */
public class MeetingResponse {

    @SerializedName("TotalCount")
    @Expose
    private int totalCount;
    @SerializedName("MeetingList")
    @Expose
    private List<MeetingList> meetingList = new ArrayList<MeetingList>();

    /**
     *
     * @return
     * The totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     *
     * @param totalCount
     * The TotalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     *
     * @return
     * The meetingList
     */
    public List<MeetingList> getMeetingList() {
        return meetingList;
    }

    /**
     *
     * @param meetingList
     * The MeetingList
     */
    public void setMeetingList(List<MeetingList> meetingList) {
        this.meetingList = meetingList;
    }

}
