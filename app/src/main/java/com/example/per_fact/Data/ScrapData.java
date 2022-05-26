package com.example.per_fact.Data;

import com.google.gson.annotations.SerializedName;

public class ScrapData {
    @SerializedName("userId")
    public int userId;

    @SerializedName("name")
    public String name;

    @SerializedName("addr")
    public String addr;

    @SerializedName("telephone")
    public String telephone;

    public ScrapData(int userId, String name, String addr, String telephone) {
        this.userId = userId;
        this.name = name;
        this.addr = addr;
        this.telephone = telephone;
    }

}
