package com.visitorapp.bloominfotech.web_api;


import com.visitorapp.bloominfotech.models.PostResponse;
import com.visitorapp.bloominfotech.models.ResponseVistorForm;
import com.visitorapp.bloominfotech.models.admin_detail.ResponseAdminDetail;
import com.visitorapp.bloominfotech.models.admin_login.ResponseAdminLogin;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;
import com.visitorapp.bloominfotech.models.form_response.ResponseVisitorForm;
import com.visitorapp.bloominfotech.models.meeting.MeetingResponse;
import com.visitorapp.bloominfotech.models.purpose.PurposeAPIResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @GET("UserAPI")
    Call<ResponseVisitorForm> getFinalReceiptAPI(@Query("uniqueKey") String uniqueKey);

    @GET("MeetingAPI")
    Call<MeetingResponse> getMeetingAPI(@Query("CompanyID") String companyID, @Query("page") int page, @Query("getAll") boolean getAll);

    @POST("UserAPI2")
    Call<ResponseVistorForm> postUserData(@Query("isPostBack") String EmailAddress, @Query("CompanyName") String FirstName, @Query("MeetingID") String LastName, @Query("PurposeName") String UserMembers, @Query("EmailAddress") String CompanyName, @Query("FirstName") String Phone, @Query("LastName") String PurposeName, @Query("CarNumber") String MeetingID, @Query("Phone") String isPostBack, @Query("UserMembers") String CarNumber);



    @GET("AdminAPI")
    Call<ResponseAdminLogin> getAdminLogin(@Query("Email") String Email, @Query("Password") String Password);


    @GET("AdminAPI")
    Call<ResponseAdminDetail> getAdminDetail(@Query("sort") String sort, @Query("srchDate") String srchDate,@Query("srchDateTo") String srchDateTo, @Query("CompanyID") String CompanyID,@Query("MeetingID") String MeetingID, @Query("page") String page);



}
