package com.visitorapp.bloominfotech.presenter.purpose_of_visit;

import com.visitorapp.bloominfotech.models.purpose.PurposeAPIResponse;
import com.visitorapp.bloominfotech.web_api.ApiClient;
import com.visitorapp.bloominfotech.web_api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by prateekarora on 31/10/16.
 */
public class PurposeInteractorImpl implements PurposeInteractor {

    @Override
    public void getAllPurpose(final OnPurposeListener onPurposeListener) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<PurposeAPIResponse> call = apiService.getPurposeAPI("");
        call.enqueue(new Callback<PurposeAPIResponse>() {
            @Override
            public void onResponse(Call<PurposeAPIResponse> call, Response<PurposeAPIResponse> response) {
                onPurposeListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PurposeAPIResponse> call, Throwable t) {
                onPurposeListener.onError(t.toString());
            }
        });
    }

}
