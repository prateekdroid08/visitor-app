package com.visitorapp.bloominfotech.presenter.visitor_form;

import com.visitorapp.bloominfotech.models.PostResponse;
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
    public void submitVisitorForm(HashMap hashMap, final OnVisitorListener onVisitorListener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<PostResponse> call = apiService.postUserData(hashMap);

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
