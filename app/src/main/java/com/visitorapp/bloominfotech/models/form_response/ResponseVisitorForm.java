package com.visitorapp.bloominfotech.models.form_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 11/7/2016.
 */

public class ResponseVisitorForm {

    @SerializedName("LogID")
    @Expose
    private Object logID;
    @SerializedName("UserID")
    @Expose
    private Object userID;
    @SerializedName("UniqueKey")
    @Expose
    private String uniqueKey;
    @SerializedName("UniqueKeyLocation")
    @Expose
    private String uniqueKeyLocation;
    @SerializedName("MeetingID")
    @Expose
    private Object meetingID;
    @SerializedName("PurposeID")
    @Expose
    private Object purposeID;
    @SerializedName("CompanyID")
    @Expose
    private Object companyID;
    @SerializedName("CreatedOn")
    @Expose
    private String createdOn;
    @SerializedName("LeftAt")
    @Expose
    private Object leftAt;
    @SerializedName("isCompleted")
    @Expose
    private Object isCompleted;
    @SerializedName("isDeleted")
    @Expose
    private Object isDeleted;
    @SerializedName("TimeSpent")
    @Expose
    private Object timeSpent;
    @SerializedName("UserDetails")
    @Expose
    private UserDetails userDetails;
    @SerializedName("CompanyDetails")
    @Expose
    private CompanyDetails companyDetails;
    @SerializedName("PurposeDetails")
    @Expose
    private PurposeDetails purposeDetails;
    @SerializedName("MeetingDetails")
    @Expose
    private Object meetingDetails;
    @SerializedName("UserMembers")
    @Expose
    private String userMembers;
    @SerializedName("TimeIn")
    @Expose
    private String timeIn;
    @SerializedName("TimeOut")
    @Expose
    private String timeOut;

    /**
     *
     * @return
     * The logID
     */
    public Object getLogID() {
        return logID;
    }

    /**
     *
     * @param logID
     * The LogID
     */
    public void setLogID(Object logID) {
        this.logID = logID;
    }

    /**
     *
     * @return
     * The userID
     */
    public Object getUserID() {
        return userID;
    }

    /**
     *
     * @param userID
     * The UserID
     */
    public void setUserID(Object userID) {
        this.userID = userID;
    }

    /**
     *
     * @return
     * The uniqueKey
     */
    public String getUniqueKey() {
        return uniqueKey;
    }

    /**
     *
     * @param uniqueKey
     * The UniqueKey
     */
    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    /**
     *
     * @return
     * The uniqueKeyLocation
     */
    public String getUniqueKeyLocation() {
        return uniqueKeyLocation;
    }

    /**
     *
     * @param uniqueKeyLocation
     * The UniqueKeyLocation
     */
    public void setUniqueKeyLocation(String uniqueKeyLocation) {
        this.uniqueKeyLocation = uniqueKeyLocation;
    }

    /**
     *
     * @return
     * The meetingID
     */
    public Object getMeetingID() {
        return meetingID;
    }

    /**
     *
     * @param meetingID
     * The MeetingID
     */
    public void setMeetingID(Object meetingID) {
        this.meetingID = meetingID;
    }

    /**
     *
     * @return
     * The purposeID
     */
    public Object getPurposeID() {
        return purposeID;
    }

    /**
     *
     * @param purposeID
     * The PurposeID
     */
    public void setPurposeID(Object purposeID) {
        this.purposeID = purposeID;
    }

    /**
     *
     * @return
     * The companyID
     */
    public Object getCompanyID() {
        return companyID;
    }

    /**
     *
     * @param companyID
     * The CompanyID
     */
    public void setCompanyID(Object companyID) {
        this.companyID = companyID;
    }

    /**
     *
     * @return
     * The createdOn
     */
    public String getCreatedOn() {
        return createdOn;
    }

    /**
     *
     * @param createdOn
     * The CreatedOn
     */
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    /**
     *
     * @return
     * The leftAt
     */
    public Object getLeftAt() {
        return leftAt;
    }

    /**
     *
     * @param leftAt
     * The LeftAt
     */
    public void setLeftAt(Object leftAt) {
        this.leftAt = leftAt;
    }

    /**
     *
     * @return
     * The isCompleted
     */
    public Object getIsCompleted() {
        return isCompleted;
    }

    /**
     *
     * @param isCompleted
     * The isCompleted
     */
    public void setIsCompleted(Object isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     *
     * @return
     * The isDeleted
     */
    public Object getIsDeleted() {
        return isDeleted;
    }

    /**
     *
     * @param isDeleted
     * The isDeleted
     */
    public void setIsDeleted(Object isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     *
     * @return
     * The timeSpent
     */
    public Object getTimeSpent() {
        return timeSpent;
    }

    /**
     *
     * @param timeSpent
     * The TimeSpent
     */
    public void setTimeSpent(Object timeSpent) {
        this.timeSpent = timeSpent;
    }

    /**
     *
     * @return
     * The userDetails
     */
    public UserDetails getUserDetails() {
        return userDetails;
    }

    /**
     *
     * @param userDetails
     * The UserDetails
     */
    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    /**
     *
     * @return
     * The companyDetails
     */
    public CompanyDetails getCompanyDetails() {
        return companyDetails;
    }

    /**
     *
     * @param companyDetails
     * The CompanyDetails
     */
    public void setCompanyDetails(CompanyDetails companyDetails) {
        this.companyDetails = companyDetails;
    }

    /**
     *
     * @return
     * The purposeDetails
     */
    public PurposeDetails getPurposeDetails() {
        return purposeDetails;
    }

    /**
     *
     * @param purposeDetails
     * The PurposeDetails
     */
    public void setPurposeDetails(PurposeDetails purposeDetails) {
        this.purposeDetails = purposeDetails;
    }

    /**
     *
     * @return
     * The meetingDetails
     */
    public Object getMeetingDetails() {
        return meetingDetails;
    }

    /**
     *
     * @param meetingDetails
     * The MeetingDetails
     */
    public void setMeetingDetails(Object meetingDetails) {
        this.meetingDetails = meetingDetails;
    }

    /**
     *
     * @return
     * The userMembers
     */
    public String getUserMembers() {
        return userMembers;
    }

    /**
     *
     * @param userMembers
     * The UserMembers
     */
    public void setUserMembers(String userMembers) {
        this.userMembers = userMembers;
    }

    /**
     *
     * @return
     * The timeIn
     */
    public String getTimeIn() {
        return timeIn;
    }

    /**
     *
     * @param timeIn
     * The TimeIn
     */
    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    /**
     *
     * @return
     * The timeOut
     */
    public String getTimeOut() {
        return timeOut;
    }

    /**
     *
     * @param timeOut
     * The TimeOut
     */
    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

}
