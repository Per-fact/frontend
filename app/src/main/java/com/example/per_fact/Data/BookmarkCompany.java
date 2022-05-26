package com.example.per_fact.Data;

import com.google.gson.annotations.SerializedName;

public class BookmarkCompany {
    @SerializedName("userId")
    private int userId = 1;

    @SerializedName("homeLat")
    private double companyLat;

    @SerializedName("homeLong")
    private double companyLong;

    @SerializedName("homeAddr")
    private String companyAddr;


    public BookmarkCompany  (int userId, double companyLat, double companyLong, String companyAddr){
        this.userId = userId;
        this.companyLat = companyLat;
        this.companyLong = companyLong;
        this.companyAddr = companyAddr;
    }
}
