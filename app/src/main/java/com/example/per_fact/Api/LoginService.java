package com.example.per_fact.Api;

import com.example.per_fact.Data.LoginData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginService {
    @POST("/login")
    Call<LoginData> postData(@Body LoginData login);

}
