package com.visitorapp.bloominfotech.models.meeting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by prateekarora on 31/10/16.
 */
public class MeetingList {

    @SerializedName("MeetingID")
    @Expose
    private String meetingID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("CompanyID")
    @Expose
    private Object companyID;
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("EmailAddress")
    @Expose
    private String emailAddress;
    @SerializedName("DepartmentName")
    @Expose
    private Object departmentName;
    @SerializedName("isDeleted")
    @Expose
    private boolean isDeleted;
    @SerializedName("CreatedOn")
    @Expose
    private String createdOn;
    @SerializedName("CompanyDetails")
    @Expose
    private Object companyDetails;

    /**
     *
     * @return
     * The meetingID
     */
    public String getMeetingID() {
        return meetingID;
    }

    /**
     *
     * @param meetingID
     * The MeetingID
     */
    public void setMeetingID(String meetingID) {
        this.meetingID = meetingID;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The Name
     */
    public void setName(String name) {
        this.name = name;
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
     * The phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber
     * The PhoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return
     * The emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     *
     * @param emailAddress
     * The EmailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     *
     * @return
     * The departmentName
     */
    public Object getDepartmentName() {
        return departmentName;
    }

    /**
     *
     * @param departmentName
     * The DepartmentName
     */
    public void setDepartmentName(Object departmentName) {
        this.departmentName = departmentName;
    }

    /**
     *
     * @return
     * The isDeleted
     */
    public boolean isIsDeleted() {
        return isDeleted;
    }

    /**
     *
     * @param isDeleted
     * The isDeleted
     */
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
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
     * The companyDetails
     */
    public Object getCompanyDetails() {
        return companyDetails;
    }

    /**
     *
     * @param companyDetails
     * The CompanyDetails
     */
    public void setCompanyDetails(Object companyDetails) {
        this.companyDetails = companyDetails;
    }

}
