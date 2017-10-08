package com.example.staff.mvvmrxjava.apis;

import com.example.staff.mvvmrxjava.models.NewsModel;
import com.example.staff.mvvmrxjava.utils.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by staff on 2017-10-05.
 *
 */

public class NewsClient {
    private static NewsClient instance;
    private URLProvider urlProvider;

    private NewsClient() {
        final Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(Config.MAIN_URL)
                .client(client.build())
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

    public Observable<ArrayList<NewsModel>> getNewsList(String order, String page) {
        return urlProvider.getAllNews(order, page);
    }
}