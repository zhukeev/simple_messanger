package com.example.bookinglane.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    public static final String BASE_URL = "https://fcm.googleapis.com/";
    private static NetworkService mInstance;
    private Retrofit mRetrofit;

    public NetworkService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder
                client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        client.interceptors().add(interceptor);
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance(){
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public ApiService getApi() {
        return mRetrofit.create(ApiService.class);
    }
}
