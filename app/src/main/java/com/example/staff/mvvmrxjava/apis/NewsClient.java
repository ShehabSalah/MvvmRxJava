package com.example.staff.mvvmrxjava.apis;

import com.example.staff.mvvmrxjava.models.NewsModel;
import com.example.staff.mvvmrxjava.utils.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by staff on 2017-10-05.
 */

public class NewsClient {
    private static NewsClient instance;
    private URLProvider urlProvider;

    public NewsClient() {
        final Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(Config.MAIN_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        urlProvider = retrofit.create(URLProvider.class);
    }

    public static NewsClient getInstance() {
        if (instance == null){
            instance = new NewsClient();
        }
        return instance;
    }

    public Observable<ArrayList<NewsModel>> getNewsList() {
        return urlProvider.getAllNews(Config.API_KEY_VAL);
    }
}