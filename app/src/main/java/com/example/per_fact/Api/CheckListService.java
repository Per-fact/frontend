package com.example.per_fact.Api;

import com.example.per_fact.Data.CheckListData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CheckListService {


    @POST("checklist")
    Call<CheckListData> postCheckList(@Body CheckListData checkListData);

    @GET("checklist/{id}")
    Call<CheckListData> getData(@Path("id") int id);

    @DELETE("checklist/{id}")
    Call<CheckListData> deleteData(@Path("id") int id);

    @PUT("checklist/{id}")
    Call<CheckListData> putData(@Path("id") int id);

    @GET("checklist/list/{userId}")
    Call<List<CheckListData>> getAllData(@Path("userId") int userId);
}
