package com.visitorapp.bloominfotech.web_api;

import android.content.Context;


import com.visitorapp.bloominfotech.constants.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rivendell on 05/09/16.
 */
public class ApiClient {


    private static Retrofit retrofit = null;
    static Context ctx;

    public static Retrofit getClient() {


        if (retrofit != null) {
            return retrofit;
        } else if(retrofit==null) {

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(30, TimeUnit.SECONDS);
            httpClient.readTimeout(30, TimeUnit.SECONDS);
            httpClient.retryOnConnectionFailure(true);
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .addHeader("Content-Type", "application/json").addHeader("Accept", "application/json")
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            });

            OkHttpClient client = httpClient.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL).client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
