package com.example.staff.mvvmrxjava.models;

/**
 * Created by staff on 2017-10-09.
 *
 */

public class NewsDetailsModel {
    private String id;
    private String title;
    private String body;
    private String imagePath;
    private String newsDate;
    private String views;

    public NewsDetailsModel(String id, String title, String body, String imagePath, String newsDate, String views) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.imagePath = imagePath;
        this.newsDate = newsDate;
        this.views = views;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }
}
