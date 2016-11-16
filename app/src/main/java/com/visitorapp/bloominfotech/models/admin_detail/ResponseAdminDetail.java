package com.visitorapp.bloominfotech.models.admin_detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 11/16/2016.
 */

public class ResponseAdminDetail {

    @SerializedName("TotalRecordsCount")
    @Expose
    private Integer totalRecordsCount;
    @SerializedName("UserLists")
    @Expose
    private List<UserList> userLists = new ArrayList<UserList>();

    /**
     *
     * @return
     * The totalRecordsCount
     */
    public Integer getTotalRecordsCount() {
        return totalRecordsCount;
    }

    /**
     *
     * @param totalRecordsCount
     * The TotalRecordsCount
     */
    public void setTotalRecordsCount(Integer totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }

    /**
     *
     * @return
     * The userLists
     */
    public List<UserList> getUserLists() {
        return userLists;
    }

    /**
     *
     * @param userLists
     * The UserLists
     */
    public void setUserLists(List<UserList> userLists) {
        this.userLists = userLists;
    }

}
