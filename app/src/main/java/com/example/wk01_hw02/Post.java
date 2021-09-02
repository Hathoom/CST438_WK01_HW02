package com.example.wk01_hw02;

import com.google.gson.annotations.SerializedName;

// each post is a class, like getting info from JSON
public class Post {

    private int userId;

    private Integer id;

    private String title;

    @SerializedName("body")
    private String text;

    public Post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
