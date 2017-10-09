package com.example.staff.mvvmrxjava.apis;

import android.content.Context;
import android.util.Log;

import com.example.staff.mvvmrxjava.listeners.OnUpdateNewsDetails;
import com.example.staff.mvvmrxjava.listeners.OnUpdateNewsList;
import com.example.staff.mvvmrxjava.models.NewsDetailsModel;
import com.example.staff.mvvmrxjava.models.NewsListItemModel;
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
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by staff on 2017-10-05.
 *
 */

public class NewsClient {
    private static final String TAG = NewsClient.class.getSimpleName();
    private static NewsClient instance;
    private URLProvider urlProvider;
    private Context context;

    private NewsClient(Context context) {
        this.context = context;
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

    public static NewsClient getInstance(Context context) {
        if (instance == null){
            instance = new NewsClient(context);
        }
        return instance;
    }


    public Subscription getNewsList(final String order, final int page){
         return NewsClient.getInstance(context).getNewsList(order, Integer.toString(page))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<NewsListItemModel>>() {
                    @Override public void onCompleted() {
                        Log.d(TAG, "In onCompleted()");
                    }

                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "In onError()");
                    }

                    @Override public void onNext(ArrayList<NewsListItemModel> newsModels) {
                        ((OnUpdateNewsList) context).displayList(newsModels);
                    }
                });
    }

    public Subscription getNewsDetails(final int news_id){
        return NewsClient.getInstance(context).getNewsItem(Integer.toString(news_id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsDetailsModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewsDetailsModel newsDetailsModel) {
                        ((OnUpdateNewsDetails) context).displayDetail(newsDetailsModel);
                    }
                });
    }

    private Observable<ArrayList<NewsListItemModel>> getNewsList(String order, String page) {
        return urlProvider.getAllNews(order, page);
    }

    private Observable<NewsDetailsModel> getNewsItem(String news_id){
        return urlProvider.getNewsDetails(news_id);
    }

}