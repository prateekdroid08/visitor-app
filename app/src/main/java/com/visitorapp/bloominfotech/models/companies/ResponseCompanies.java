package com.visitorapp.bloominfotech.models.companies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 10/25/2016.
 */
public class ResponseCompanies {


    @SerializedName("TotalRecordsCount")
    @Expose
    private Integer totalRecordsCount;
    @SerializedName("CompanyLists")
    @Expose
    private List<CompanyList> companyLists = new ArrayList<CompanyList>();

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
     * The companyLists
     */
    public List<CompanyList> getCompanyLists() {
        return companyLists;
    }

    /**
     *
     * @param companyLists
     * The CompanyLists
     */
    public void setCompanyLists(List<CompanyList> companyLists) {
        this.companyLists = companyLists;
    }
}
