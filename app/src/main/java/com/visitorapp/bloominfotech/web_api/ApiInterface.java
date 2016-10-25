package com.visitorapp.bloominfotech.web_api;


import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Rivendell on 05/09/16.
 */
public interface ApiInterface {


    @GET("CompanyAPI")
    Call<ResponseCompanies> getComapnyAPI(@Query("companyID") String companyID, @Query("page") int page, @Query("getAll") boolean getAll);


}
