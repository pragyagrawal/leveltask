package com.example.pragyaagrawal.levelmoneytask.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    // Trailing slash is needed
    private static final String LEVELMONEY_URL = "https://2016.api.levelmoney.com/api/v2/core/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient.addInterceptor(httpLoggingInterceptor);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(LEVELMONEY_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient.build())
                    .build();
        }
        return retrofit;
    }
}
