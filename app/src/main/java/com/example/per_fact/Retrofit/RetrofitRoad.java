package com.example.per_fact.Retrofit;

import com.example.per_fact.Api.trafficSearchService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRoad {
    private static RetrofitRoad INSTANCE;

    public static RetrofitRoad getRetrofit() {
        if(INSTANCE == null) {
            INSTANCE = new RetrofitRoad();
        }
        return INSTANCE;
    }

    private RetrofitRoad() {

    }

    public trafficSearchService getTrafficService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://naveropenapi.apigw.ntruss.com/map-direction/").
                addConverterFactory(GsonConverterFactory.create()).
                build();
        return retrofit.create(trafficSearchService.class);
    }
}
