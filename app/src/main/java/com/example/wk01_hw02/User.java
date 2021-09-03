package com.example.wk01_hw02;

import com.google.gson.annotations.SerializedName;

public class User {
    private Integer id;

    private String username;

    public User(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public String getuserName() {
        return username;
    }
}
