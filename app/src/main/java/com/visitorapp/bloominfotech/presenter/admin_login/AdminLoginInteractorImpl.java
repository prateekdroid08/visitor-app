package com.visitorapp.bloominfotech.presenter.admin_login;

import com.visitorapp.bloominfotech.models.admin_login.ResponseAdminLogin;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;
import com.visitorapp.bloominfotech.web_api.ApiClient;
import com.visitorapp.bloominfotech.web_api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hp on 10/24/2016.
 */
public class AdminLoginInteractorImpl implements AdminLoginInteractor{
    @Override
    public void getadminLogin(String Email, String Password,final AdminLoginListener adminLoginListener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseAdminLogin> call = apiService.getAdminLogin(Email, Password);
        call.enqueue(new Callback<ResponseAdminLogin>() {
            @Override
            public void onResponse(Call<ResponseAdminLogin> call, Response<ResponseAdminLogin> response) {
                // List<Movie> movies = response.body().getResults();
                adminLoginListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseAdminLogin> call, Throwable t) {
                // Log error here since request failed
                // Log.e(TAG, t.toString());
                adminLoginListener.onError(t.toString());
            }
        });
    }
}
