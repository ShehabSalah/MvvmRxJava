package com.example.staff.mvvmrxjava.apis;

import com.example.staff.mvvmrxjava.models.NewsModel;
import com.example.staff.mvvmrxjava.utils.Config;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by staff on 2017-10-05.
 */

public interface URLProvider {
    @GET(Config.NEWS_API)
    Observable<ArrayList<NewsModel>> getAllNews(@Query(Config.ORDER_PARAM) String order, @Query(Config.PAGE_PARAM) String page);
}
