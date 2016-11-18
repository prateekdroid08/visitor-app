package com.visitorapp.bloominfotech.models.admin_detail;

/**
 * Created by prateekarora on 18/11/16.
 */

public class FilterData {

    String srchDate = "";
    String srchDateTo = "";
    String CompanyID = "";
    String MeetingID = "";

    public String getSrchDate() {
        return srchDate;
    }

    public void setSrchDate(String srchDate) {
        this.srchDate = srchDate;
    }

    public String getSrchDateTo() {
        return srchDateTo;
    }

    public void setSrchDateTo(String srchDateTo) {
        this.srchDateTo = srchDateTo;
    }

    public String getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(String companyID) {
        CompanyID = companyID;
    }

    public String getMeetingID() {
        return MeetingID;
    }

    public void setMeetingID(String meetingID) {
        MeetingID = meetingID;
    }
}
