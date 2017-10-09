package com.example.staff.mvvmrxjava.views.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.staff.mvvmrxjava.apis.NewsClient;
import com.example.staff.mvvmrxjava.listeners.OnUpdateNewsList;
import com.example.staff.mvvmrxjava.models.NewsListItemModel;
import com.example.staff.mvvmrxjava.R;
import com.example.staff.mvvmrxjava.databinding.ActivityMainBinding;
import com.example.staff.mvvmrxjava.utils.Config;
import com.example.staff.mvvmrxjava.views.adapters.NewsAdapter;
import com.example.staff.mvvmrxjava.views.helper.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;

import rx.Subscription;

public class MainActivity extends AppCompatActivity implements OnUpdateNewsList {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Subscription subscription;
    NewsAdapter adapter;
    ActivityMainBinding binding;
    LinearLayoutManager layoutManager;
    private int pageNum = 0;
    private boolean isNext = true;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Define binding with the XML
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //region Display News List
        layoutManager = new LinearLayoutManager(this);
        binding.newsList.setLayoutManager(layoutManager);
        // add endless Listener to news list
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                if(isNext)
                    subscription = NewsClient.getInstance(getApplicationContext())
                            .getNewsList(Config.RECENT_ORDER, ++pageNum);
                else
                    binding.newsList.removeOnScrollListener(endlessRecyclerOnScrollListener);
            }
        };

        binding.newsList.addOnScrollListener(endlessRecyclerOnScrollListener);
        //getNewsList(Config.RECENT_ORDER, pageNum);
        //endregion
    }


    @Override protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    @Override
    public void displayList(ArrayList<NewsListItemModel> newsModelArrayList) {
        Log.d(TAG, "In onNext()");

        if (newsModelArrayList !=null && newsModelArrayList.size()> 0){

            if (pageNum == 1) {
                adapter = new NewsAdapter(newsModelArrayList);
                binding.newsList.setAdapter(adapter);
            }else{
                adapter.AddAnotherPageToExistingOne(newsModelArrayList);
            }

        }else{
            isNext = false;
            Log.e(TAG, "newsModels == null OR ");
        }
    }
}
