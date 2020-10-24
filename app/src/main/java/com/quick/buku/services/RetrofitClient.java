package com.quick.buku.services;


import com.quick.buku.MyApplication;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String API_URL = "https://reqres.in";
    private static final Object sLock = new Object();
    private static final OkHttpClient client;
    static int cacheSize = 10 * 1024 * 1024; // 10 MB
    static Cache cache = new Cache(MyApplication.getInstance().getCacheDir(), cacheSize);
    private static DataSearchService sInstance;

    static {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .cache(cache)
                .build();
    }

    public static DataSearchService getInstance() {
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = getRetrofitInstance().create(DataSearchService.class);
            }
            return sInstance;
        }
    }

    public static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
