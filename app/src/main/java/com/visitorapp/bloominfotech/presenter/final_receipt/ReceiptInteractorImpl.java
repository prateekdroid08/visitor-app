package com.visitorapp.bloominfotech.presenter.final_receipt;

import com.visitorapp.bloominfotech.models.PostResponse;
import com.visitorapp.bloominfotech.models.form_response.ResponseVisitorForm;
import com.visitorapp.bloominfotech.web_api.ApiClient;
import com.visitorapp.bloominfotech.web_api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hp on 11/7/2016.
 */

public class ReceiptInteractorImpl implements  ReceiptInteractor{
    @Override
    public void getFinalreceipt(String uniqueKey,final ReceiptListener receiptListener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseVisitorForm> call = apiService.getFinalReceiptAPI(uniqueKey);

        call.enqueue(new Callback<ResponseVisitorForm>() {
            @Override
            public void onResponse(Call<ResponseVisitorForm> call, Response<ResponseVisitorForm> response) {
                receiptListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseVisitorForm> call, Throwable t) {
                receiptListener.onError(t.toString());
            }
        });
    }
}
