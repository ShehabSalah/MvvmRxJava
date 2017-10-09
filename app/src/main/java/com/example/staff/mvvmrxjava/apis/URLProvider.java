package com.example.staff.mvvmrxjava.apis;

import com.example.staff.mvvmrxjava.models.NewsDetailsModel;
import com.example.staff.mvvmrxjava.models.NewsListItemModel;
import com.example.staff.mvvmrxjava.utils.Config;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by staff on 2017-10-05.
 */

public interface URLProvider {
    @GET(Config.NEWS_API)
    Observable<ArrayList<NewsListItemModel>> getAllNews(@Query(Config.ORDER_PARAM) String order, @Query(Config.PAGE_PARAM) String page);

    @GET(Config.NEWS_API)
    Observable<NewsDetailsModel> getNewsDetails(@Query(Config.NEWS_ID_PARAM) String news_id);

}
