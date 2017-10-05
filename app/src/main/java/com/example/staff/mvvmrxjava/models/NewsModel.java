package com.example.staff.mvvmrxjava.models;

/**
 * Created by staff on 2017-10-04.
 *
 */

public class NewsModel{
    private int id;
    private String title;
    private String body;
    private String imagePath;
    private String shortLink;

    public NewsModel(String title, String body, String imagePath) {
        this.title = title;
        this.body = body;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }
}