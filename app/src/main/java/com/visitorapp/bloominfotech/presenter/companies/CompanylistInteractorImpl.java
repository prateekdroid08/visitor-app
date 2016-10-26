package com.visitorapp.bloominfotech.presenter.companies;

import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;
import com.visitorapp.bloominfotech.web_api.ApiClient;
import com.visitorapp.bloominfotech.web_api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hp on 10/25/2016.
 */
public class CompanylistInteractorImpl implements CompanyListinteractor {
    @Override
    public void getCompanyListAPI(String companyID, int page, boolean getAll, final CompanylistListener companylistListener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        Call<ResponseCompanies> call = apiService.getComapnyAPI(companyID, page, getAll);
        call.enqueue(new Callback<ResponseCompanies>() {
            @Override
            public void onResponse(Call<ResponseCompanies> call, Response<ResponseCompanies> response) {
                // List<Movie> movies = response.body().getResults();
                companylistListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseCompanies> call, Throwable t) {
                // Log error here since request failed
                // Log.e(TAG, t.toString());
                companylistListener.onError(t.toString());
            }
        });
    }
}
