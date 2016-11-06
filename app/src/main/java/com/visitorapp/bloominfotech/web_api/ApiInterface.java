package com.visitorapp.bloominfotech.web_api;


import com.visitorapp.bloominfotech.models.PostResponse;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;
import com.visitorapp.bloominfotech.models.meeting.MeetingResponse;
import com.visitorapp.bloominfotech.models.purpose.PurposeAPIResponse;

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

    @GET("PurposeVisitorAPI")
    Call<PurposeAPIResponse> getPurposeAPI(@Query("PurposeID") String purposeId);

    @GET("MeetingAPI")
    Call<MeetingResponse> getMeetingAPI(@Query("CompanyID") String companyID, @Query("page") int page, @Query("getAll") boolean getAll);

    @POST("UserAPI")
    Call<PostResponse> postUserData(@Body HashMap hashMap);

}
