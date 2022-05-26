package com.example.per_fact.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Optional;

public class CheckListData {
    @SerializedName("userId")
    private int userId;

    @SerializedName("content")
    private String content;

    @SerializedName("status")
    private String status;

    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CheckListData(int userId, String content, String status) {
        this.userId = userId;
        this.content = content;
        this.status = status;
    }



}
