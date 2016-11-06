package com.visitorapp.bloominfotech.models.purpose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateekarora on 31/10/16.
 */
public class PurposeAPIResponse {

    @SerializedName("purposeList")
    @Expose
    private List<PurposeList> purposeList = new ArrayList<PurposeList>();
    @SerializedName("TotalDummyRecords")
    @Expose
    private int totalDummyRecords;

    /**
     *
     * @return
     * The purposeList
     */
    public List<PurposeList> getPurposeList() {
        return purposeList;
    }

    /**
     *
     * @param purposeList
     * The purposeList
     */
    public void setPurposeList(List<PurposeList> purposeList) {
        this.purposeList = purposeList;
    }

    /**
     *
     * @return
     * The totalDummyRecords
     */
    public int getTotalDummyRecords() {
        return totalDummyRecords;
    }

    /**
     *
     * @param totalDummyRecords
     * The TotalDummyRecords
     */
    public void setTotalDummyRecords(int totalDummyRecords) {
        this.totalDummyRecords = totalDummyRecords;
    }

}
