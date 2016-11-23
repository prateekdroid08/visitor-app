package com.visitorapp.bloominfotech.models.form_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 11/7/2016.
 */

public class UserDetails {

    @SerializedName("UserID")
    @Expose
    private Object userID;
    @SerializedName("UserName")
    @Expose
    private Object userName;
    @SerializedName("EmailAddress")
    @Expose
    private Object emailAddress;
    @SerializedName("FirstName")
    @Expose
    private String firstName = "";
    @SerializedName("LastName")
    @Expose
    private String lastName = "";
    @SerializedName("CarNumber")
    @Expose
    private Object carNumber;
    @SerializedName("Phone")
    @Expose
    private String phone = "";
    @SerializedName("DeviceDetails")
    @Expose
    private Object deviceDetails;
    @SerializedName("IPAddress")
    @Expose
    private Object iPAddress;
    @SerializedName("profilePic")
    @Expose
    private Object profilePic;
    @SerializedName("isActive")
    @Expose
    private Object isActive;
    @SerializedName("isDeleted")
    @Expose
    private Object isDeleted;
    @SerializedName("CreatedOn")
    @Expose
    private Object createdOn;
    @SerializedName("UserRoles")
    @Expose
    private Object userRoles;

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
     * The userName
     */
    public Object getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     * The UserName
     */
    public void setUserName(Object userName) {
        this.userName = userName;
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
     * The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     * The FirstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     * The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     * The LastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     * The carNumber
     */
    public Object getCarNumber() {
        return carNumber;
    }

    /**
     *
     * @param carNumber
     * The CarNumber
     */
    public void setCarNumber(Object carNumber) {
        this.carNumber = carNumber;
    }

    /**
     *
     * @return
     * The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The Phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The deviceDetails
     */
    public Object getDeviceDetails() {
        return deviceDetails;
    }

    /**
     *
     * @param deviceDetails
     * The DeviceDetails
     */
    public void setDeviceDetails(Object deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    /**
     *
     * @return
     * The iPAddress
     */
    public Object getIPAddress() {
        return iPAddress;
    }

    /**
     *
     * @param iPAddress
     * The IPAddress
     */
    public void setIPAddress(Object iPAddress) {
        this.iPAddress = iPAddress;
    }

    /**
     *
     * @return
     * The profilePic
     */
    public Object getProfilePic() {
        return profilePic;
    }

    /**
     *
     * @param profilePic
     * The profilePic
     */
    public void setProfilePic(Object profilePic) {
        this.profilePic = profilePic;
    }

    /**
     *
     * @return
     * The isActive
     */
    public Object getIsActive() {
        return isActive;
    }

    /**
     *
     * @param isActive
     * The isActive
     */
    public void setIsActive(Object isActive) {
        this.isActive = isActive;
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
     * The userRoles
     */
    public Object getUserRoles() {
        return userRoles;
    }

    /**
     *
     * @param userRoles
     * The UserRoles
     */
    public void setUserRoles(Object userRoles) {
        this.userRoles = userRoles;
    }
}
