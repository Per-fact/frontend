package com.example.per_fact.Data;

import com.google.gson.annotations.SerializedName;

public class BookmarkHome {
    @SerializedName("userId")
    private int userId = 1;

    @SerializedName("homeLat")
    private double homeLat;

    @SerializedName("homeLong")
    private double homeLong;

    @SerializedName("homeAddr")
    private String homeAddr;


    public BookmarkHome  (int userId, double homeLat, double homeLong, String homeAddr){
        this.userId = userId;
        this.homeLat = homeLat;
        this.homeLong = homeLong;
        this.homeAddr = homeAddr;
    }

}

