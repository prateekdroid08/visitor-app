package com.visitorapp.bloominfotech.presenter.admin_detail;

import com.visitorapp.bloominfotech.models.admin_detail.ResponseAdminDetail;
import com.visitorapp.bloominfotech.models.admin_login.ResponseAdminLogin;
import com.visitorapp.bloominfotech.web_api.ApiClient;
import com.visitorapp.bloominfotech.web_api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hp on 10/24/2016.
 */
public class AdminDetailInteractorImpl implements AdminDetailInteractor{
    @Override
    public void getadminDetailsAPI(String sort, String srchDate, String srchDateTo, String CompanyID, String MeetingID, String page,final AdminDetailListener adminDetailListener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseAdminDetail> call = apiService.getAdminDetail(sort, srchDate,srchDateTo,CompanyID,MeetingID,page);
        call.enqueue(new Callback<ResponseAdminDetail>() {
            @Override
            public void onResponse(Call<ResponseAdminDetail> call, Response<ResponseAdminDetail> response) {
                // List<Movie> movies = response.body().getResults();
                adminDetailListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseAdminDetail> call, Throwable t) {
                // Log error here since request failed
                // Log.e(TAG, t.toString());
                adminDetailListener.onError(t.toString());
            }
        });
    }
}
