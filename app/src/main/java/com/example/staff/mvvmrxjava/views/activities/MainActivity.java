package com.example.staff.mvvmrxjava.views.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.staff.mvvmrxjava.apis.NewsClient;
import com.example.staff.mvvmrxjava.models.NewsModel;
import com.example.staff.mvvmrxjava.R;
import com.example.staff.mvvmrxjava.databinding.ActivityMainBinding;
import com.example.staff.mvvmrxjava.views.adapters.NewsAdapter;

import java.util.ArrayList;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Subscription subscription;
    NewsAdapter adapter;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Define binding with the XML
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //region Display News List
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.newsList.setLayoutManager(layoutManager);
        getNewsList();
        //endregion

    }

    private ArrayList<NewsModel> getNewsList(){
        subscription = NewsClient.getInstance()
                .getNewsList()
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
                        Log.d(TAG, "In onNext()");
                        if (newsModels!=null){
                            adapter = new NewsAdapter(newsModels);
                            binding.newsList.setAdapter(adapter);
                        }else{
                            Log.e(TAG, "newsModels == null");
                        }

                    }
                });


        return null;
    }

    @Override protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }
}
