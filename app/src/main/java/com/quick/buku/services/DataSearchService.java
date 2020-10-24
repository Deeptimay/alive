package com.quick.buku.services;

import com.quick.buku.models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataSearchService {

    @GET("/api/users")
    Call<ApiResponse> getUserList();
}
