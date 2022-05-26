package com.example.per_fact.Api;

import com.example.per_fact.Data.ResultData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface trafficSearchService {
    @GET("v1/driving")
    Call<ResultData> getPath(@Header("X-NCP-APIGW-API-KEY-ID") String apiKeyID,
                             @Header("X-NCP-APIGW-API-KEY") String apiKey,
                             @Query("start") String start,
                             @Query("goal") String goal);
}
