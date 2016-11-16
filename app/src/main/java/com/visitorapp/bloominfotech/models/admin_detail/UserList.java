package com.visitorapp.bloominfotech.models.admin_detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 11/16/2016.
 */

public class UserList {

    @SerializedName("LogID")
    @Expose
    private String logID;
    @SerializedName("UserID")
    @Expose
    private String userID;
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
    private String purposeID;
    @SerializedName("CompanyID")
    @Expose
    private String companyID;
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
    private String timeSpent;
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
    private MeetingDetails meetingDetails;
    @SerializedName("UserMembers")
    @Expose
    private Object userMembers;
    @SerializedName("TimeIn")
    @Expose
    private String timeIn;
    @SerializedName("TimeOut")
    @Expose
    private String timeOut;

    /**
     * @return The logID
     */
    public String getLogID() {
        return logID;
    }

    /**
     * @param logID The LogID
     */
    public void setLogID(String logID) {
        this.logID = logID;
    }

    /**
     * @return The userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID The UserID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return The uniqueKey
     */
    public String getUniqueKey() {
        return uniqueKey;
    }

    /**
     * @param uniqueKey The UniqueKey
     */
    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    /**
     * @return The uniqueKeyLocation
     */
    public String getUniqueKeyLocation() {
        return uniqueKeyLocation;
    }

    /**
     * @param uniqueKeyLocation The UniqueKeyLocation
     */
    public void setUniqueKeyLocation(String uniqueKeyLocation) {
        this.uniqueKeyLocation = uniqueKeyLocation;
    }

    /**
     * @return The meetingID
     */
    public Object getMeetingID() {
        return meetingID;
    }

    /**
     * @param meetingID The MeetingID
     */
    public void setMeetingID(Object meetingID) {
        this.meetingID = meetingID;
    }

    /**
     * @return The purposeID
     */
    public String getPurposeID() {
        return purposeID;
    }

    /**
     * @param purposeID The PurposeID
     */
    public void setPurposeID(String purposeID) {
        this.purposeID = purposeID;
    }

    /**
     * @return The companyID
     */
    public String getCompanyID() {
        return companyID;
    }

    /**
     * @param companyID The CompanyID
     */
    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    /**
     * @return The createdOn
     */
    public String getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn The CreatedOn
     */
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * @return The leftAt
     */
    public Object getLeftAt() {
        return leftAt;
    }

    /**
     * @param leftAt The LeftAt
     */
    public void setLeftAt(Object leftAt) {
        this.leftAt = leftAt;
    }

    /**
     * @return The isCompleted
     */
    public Object getIsCompleted() {
        return isCompleted;
    }

    /**
     * @param isCompleted The isCompleted
     */
    public void setIsCompleted(Object isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * @return The isDeleted
     */
    public Object getIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted The isDeleted
     */
    public void setIsDeleted(Object isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * @return The timeSpent
     */
    public String getTimeSpent() {
        return timeSpent;
    }

    /**
     * @param timeSpent The TimeSpent
     */
    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }

    /**
     * @return The userDetails
     */
    public UserDetails getUserDetails() {
        return userDetails;
    }

    /**
     * @param userDetails The UserDetails
     */
    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    /**
     * @return The companyDetails
     */
    public CompanyDetails getCompanyDetails() {
        return companyDetails;
    }

    /**
     * @param companyDetails The CompanyDetails
     */
    public void setCompanyDetails(CompanyDetails companyDetails) {
        this.companyDetails = companyDetails;
    }

    /**
     * @return The purposeDetails
     */
    public PurposeDetails getPurposeDetails() {
        return purposeDetails;
    }

    /**
     * @param purposeDetails The PurposeDetails
     */
    public void setPurposeDetails(PurposeDetails purposeDetails) {
        this.purposeDetails = purposeDetails;
    }

    /**
     * @return The meetingDetails
     */
    public MeetingDetails getMeetingDetails() {
        return meetingDetails;
    }

    /**
     * @param meetingDetails The MeetingDetails
     */
    public void setMeetingDetails(MeetingDetails meetingDetails) {
        this.meetingDetails = meetingDetails;
    }

    /**
     * @return The userMembers
     */
    public Object getUserMembers() {
        return userMembers;
    }

    /**
     * @param userMembers The UserMembers
     */
    public void setUserMembers(Object userMembers) {
        this.userMembers = userMembers;
    }

    /**
     * @return The timeIn
     */
    public String getTimeIn() {
        return timeIn;
    }

    /**
     * @param timeIn The TimeIn
     */
    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    /**
     * @return The timeOut
     */
    public String getTimeOut() {
        return timeOut;
    }

    /**
     * @param timeOut The TimeOut
     */
    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }
}
