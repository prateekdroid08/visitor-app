package com.visitorapp.bloominfotech.models.purpose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by prateekarora on 31/10/16.
 */
public class PurposeList {

    @SerializedName("PurposeID")
    @Expose
    private String purposeID;
    @SerializedName("PurposeName")
    @Expose
    private String purposeName;
    @SerializedName("isDeleted")
    @Expose
    private boolean isDeleted;
    @SerializedName("CreatedOn")
    @Expose
    private String createdOn;

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

}
