package com.visitorapp.bloominfotech.models.admin_detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 11/16/2016.
 */

public class PurposeDetails {

    @SerializedName("PurposeID")
    @Expose
    private String purposeID;
    @SerializedName("PurposeName")
    @Expose
    private String purposeName;
    @SerializedName("isDeleted")
    @Expose
    private Object isDeleted;
    @SerializedName("CreatedOn")
    @Expose
    private Object createdOn;

    /**
     *
     * @return
     * The purposeID
     */
    public String getPurposeID() {
        return purposeID;
    }

    /**
     *
     * @param purposeID
     * The PurposeID
     */
    public void setPurposeID(String purposeID) {
        this.purposeID = purposeID;
    }

    /**
     *
     * @return
     * The purposeName
     */
    public String getPurposeName() {
        return purposeName;
    }

    /**
     *
     * @param purposeName
     * The PurposeName
     */
    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
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
