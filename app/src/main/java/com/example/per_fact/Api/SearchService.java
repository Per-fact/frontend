package com.example.per_fact.Api;

import com.example.per_fact.Data.OdsayData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {
    @GET("v1/api/searchPubTransPathT")
    Call<OdsayData> getPath(@Query("apiKey") String apiKey,
                            @Query("lang") String lang,
                            @Query("SX") String SX,
                            @Query("SY") String SY,
                            @Query("EX") String EX,
                            @Query("EY") String EY);
}
