package com.visitorapp.bloominfotech.models.admin_detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 11/16/2016.
 */

public class MeetingDetails {

    @SerializedName("MeetingID")
    @Expose
    private String meetingID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("CompanyID")
    @Expose
    private String companyID;
    @SerializedName("PhoneNumber")
    @Expose
    private Object phoneNumber;
    @SerializedName("EmailAddress")
    @Expose
    private Object emailAddress;
    @SerializedName("DepartmentName")
    @Expose
    private Object departmentName;
    @SerializedName("isDeleted")
    @Expose
    private Object isDeleted;
    @SerializedName("CreatedOn")
    @Expose
    private Object createdOn;
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
    public String getCompanyID() {
        return companyID;
    }

    /**
     *
     * @param companyID
     * The CompanyID
     */
    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    /**
     *
     * @return
     * The phoneNumber
     */
    public Object getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber
     * The PhoneNumber
     */
    public void setPhoneNumber(Object phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return
     * The emailAddress
     */
    public Object getEmailAddress() {
        return emailAddress;
    }

    /**
     *
     * @param emailAddress
     * The EmailAddress
     */
    public void setEmailAddress(Object emailAddress) {
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
     * The createdOn
     */
    public Object getCreatedOn() {
        return createdOn;
    }

    /**
     *
     * @param createdOn
     * The CreatedOn
     */
    public void setCreatedOn(Object createdOn) {
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
