package com.visitorapp.bloominfotech.presenter.logout;

import com.visitorapp.bloominfotech.models.PostResponse;
import com.visitorapp.bloominfotech.models.ResponseLogout;
import com.visitorapp.bloominfotech.models.admin_login.ResponseAdminLogin;
import com.visitorapp.bloominfotech.web_api.ApiClient;
import com.visitorapp.bloominfotech.web_api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hp on 11/17/2016.
 */

public class LogoutInteractorImpl implements  LogoutInteractor{
    @Override
    public void logoutMethod(String uniqueKey, String islogOutCommand,final LogoutListener logoutListener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseLogout> call = apiService.getlogoutAPI(uniqueKey, islogOutCommand);
        call.enqueue(new Callback<ResponseLogout>() {
            @Override
            public void onResponse(Call<ResponseLogout> call, Response<ResponseLogout> response) {
                // List<Movie> movies = response.body().getResults();
                logoutListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseLogout> call, Throwable t) {
                // Log error here since request failed
                // Log.e(TAG, t.toString());
                logoutListener.onError(t.toString());
            }
        });
    }
}
