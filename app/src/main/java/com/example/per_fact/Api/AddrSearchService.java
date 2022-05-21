package com.example.per_fact.Api;

import com.example.per_fact.Data.Location;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface AddrSearchService {
    @GET("v2/local/search/keyword.json")
    Call<Location> searchAddressList(@Query("query") String query, @Header("Authorization") String apikey);
}
