package com.example.staff.mvvmrxjava.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.staff.mvvmrxjava.apis.NewsClient;
import com.example.staff.mvvmrxjava.listner.OnUpdateList;
import com.example.staff.mvvmrxjava.models.NewsModel;
import com.example.staff.mvvmrxjava.R;
import com.example.staff.mvvmrxjava.databinding.ActivityMainBinding;
import com.example.staff.mvvmrxjava.utils.Config;
import com.example.staff.mvvmrxjava.views.adapters.NewsAdapter;
import com.example.staff.mvvmrxjava.views.helper.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements OnUpdateList{
    private static final String TAG = MainActivity.class.getSimpleName();
    private Subscription subscription;
    NewsAdapter adapter;
    ActivityMainBinding binding;
    LinearLayoutManager layoutManager;
    private int pageNum = 1;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    private boolean isNext = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Define binding with the XML
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //region Display News List
        layoutManager = new LinearLayoutManager(this);
        binding.newsList.setLayoutManager(layoutManager);

        getNewsList(Config.RECENT_ORDER, pageNum);
        //endregion
    }

    private void getNewsList(final String order, final int page){
        subscription = NewsClient.getInstance().getNewsList(order, Integer.toString(page))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<NewsModel>>() {
                    @Override public void onCompleted() {
                        Log.d(TAG, "In onCompleted()");
                    }

                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "In onError()");
                    }

                    @Override public void onNext(ArrayList<NewsModel> newsModels) {
                       addData(newsModels);
                    }
                });
    }

    public void addData(ArrayList<NewsModel>  newsModels){
        Log.d(TAG, "In onNext()");

        if (newsModels !=null && newsModels.size()> 0){

            if (pageNum == 1) {
                adapter = new NewsAdapter(newsModels);
                binding.newsList.setAdapter(adapter);
                endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(layoutManager) {
                    @Override
                    public void onLoadMore() {
                        if(isNext)
                            getNewsList(Config.RECENT_ORDER, ++pageNum);
                        else
                            binding.newsList.removeOnScrollListener(endlessRecyclerOnScrollListener);
                    }
                };
                binding.newsList.addOnScrollListener(endlessRecyclerOnScrollListener);

            }else{
                adapter.AddAnotherPageToExistingOne(newsModels);
            }

        }else{
            Log.e(TAG, "newsModels == null");
        }

    }

    @Override protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }
}
