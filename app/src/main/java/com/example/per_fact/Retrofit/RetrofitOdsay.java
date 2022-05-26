package com.example.per_fact.Retrofit;

import com.example.per_fact.Api.SearchService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitOdsay {
    private static RetrofitOdsay INSTANCE;

    public static RetrofitOdsay getRetrofit() {
        if(INSTANCE == null) {
            INSTANCE = new RetrofitOdsay();
        }
        return INSTANCE;
    }

    private RetrofitOdsay() {

    }

    public SearchService getSearch() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.odsay.com/").
                addConverterFactory(GsonConverterFactory.create()).
                build();
        return retrofit.create(SearchService.class);
    }
}