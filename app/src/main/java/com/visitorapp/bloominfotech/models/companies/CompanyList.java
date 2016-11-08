package com.visitorapp.bloominfotech.models.companies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 10/25/2016.
 */
public class CompanyList {


    @SerializedName("CompanyID")
    @Expose
    private String companyID;
    @SerializedName("CompanyName")
    @Expose
    private String companyName;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("CreatedOn")
    @Expose
    private String createdOn;

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
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     *
     * @param isDeleted
     * The isDeleted
     */
    public void setIsDeleted(Boolean isDeleted) {
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

}
