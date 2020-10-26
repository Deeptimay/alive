package com.test.aliveCore.services;

import com.test.aliveCore.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataSearchService {

    @GET("/api/users")
    Call<UserResponse> getUserList();
}