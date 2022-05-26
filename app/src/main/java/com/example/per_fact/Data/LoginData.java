package com.example.per_fact.Data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("email")
    private String email;

    @SerializedName("passwd")
    private String passwd;

    public LoginData(String email, String passwd) {
        this.email = email;
        this.passwd = passwd;
    }
}
