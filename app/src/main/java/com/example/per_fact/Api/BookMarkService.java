package com.example.per_fact.Api;

import com.example.per_fact.Data.BookmarkCompany;
import com.example.per_fact.Data.BookmarkHome;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BookMarkService {
    @POST("/bookmark/home")
    Call<BookmarkHome> addHome(@Body BookmarkHome bookmarkHome);

    @POST("bookmark/company")
    Call<BookmarkCompany> addCompany(@Body BookmarkCompany bookmarkCompany);
}
