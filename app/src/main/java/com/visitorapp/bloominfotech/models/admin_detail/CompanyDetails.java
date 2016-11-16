package com.visitorapp.bloominfotech.models.admin_detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 11/16/2016.
 */

public class CompanyDetails {

    @SerializedName("CompanyID")
    @Expose
    private String companyID;
    @SerializedName("CompanyName")
    @Expose
    private String companyName;
    @SerializedName("isDeleted")
    @Expose
    private Object isDeleted;
    @SerializedName("CreatedOn")
    @Expose
    private Object createdOn;

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
     * The companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     *
     * @param companyName
     * The CompanyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

}
