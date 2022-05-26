package com.example.per_fact.Api;

import com.example.per_fact.Data.ScrapData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ScrapService {
    @POST("/likeplace")
    Call<ScrapData> postData(@Body ScrapData scrap);
}
