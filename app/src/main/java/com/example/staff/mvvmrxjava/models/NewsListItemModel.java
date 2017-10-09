package com.example.staff.mvvmrxjava.models;

/**
 * Created by staff on 2017-10-04.
 *
 */

public class NewsListItemModel {
    private int id;
    private String title;
    private String news_image;
    private String date;

    public NewsListItemModel(int id, String title, String news_image, String date) {
        this.id = id;
        this.title = title;
        this.news_image = news_image;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNews_image() {
        return news_image;
    }

    public void setNews_image(String news_image) {
        this.news_image = news_image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
