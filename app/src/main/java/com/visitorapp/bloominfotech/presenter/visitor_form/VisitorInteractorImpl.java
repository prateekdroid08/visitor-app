package com.visitorapp.bloominfotech.presenter.visitor_form;

import com.visitorapp.bloominfotech.models.PostResponse;
import com.visitorapp.bloominfotech.models.form_response.ResponseVisitorForm;
import com.visitorapp.bloominfotech.models.meeting.MeetingResponse;
import com.visitorapp.bloominfotech.web_api.ApiClient;
import com.visitorapp.bloominfotech.web_api.ApiInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hp on 10/24/2016.
 */
public class VisitorInteractorImpl implements VisitorInteractor {

    @Override
    public void submitVisitorForm(String EmailAddress, String FirstName, String LastName, String UserMembers, String CompanyName, String Phone, String PurposeName, String MeetingID, String isPostBack, String CarNumber, final OnVisitorListener onVisitorListener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<PostResponse> call = apiService.postUserData(EmailAddress, FirstName, LastName, UserMembers, CompanyName, Phone, PurposeName, MeetingID, "True", CarNumber);

        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                onVisitorListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                onVisitorListener.onError(t.toString());
            }
        });
    }

}
