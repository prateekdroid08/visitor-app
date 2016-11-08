package com.visitorapp.bloominfotech.presenter.meeting;

import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;
import com.visitorapp.bloominfotech.models.meeting.MeetingResponse;
import com.visitorapp.bloominfotech.web_api.ApiClient;
import com.visitorapp.bloominfotech.web_api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by prateekarora on 31/10/16.
 */
public class MeetingInteractorImpl implements MeetingInteractor {

    @Override
    public void getMeetingList(String companyID, int page, boolean getAll, final OnMeetingListener onMeetingListener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MeetingResponse> call = apiService.getMeetingAPI(companyID, page, getAll);
        call.enqueue(new Callback<MeetingResponse>() {
            @Override
            public void onResponse(Call<MeetingResponse> call, Response<MeetingResponse> response) {
                onMeetingListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MeetingResponse> call, Throwable t) {
                onMeetingListener.onError(t.toString());
            }
        });
    }

}
