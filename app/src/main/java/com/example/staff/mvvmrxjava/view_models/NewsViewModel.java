package com.example.staff.mvvmrxjava.view_models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.staff.mvvmrxjava.BR;
import com.example.staff.mvvmrxjava.models.NewsListItemModel;
import com.squareup.picasso.Picasso;

/**
 * Created by staff on 2017-10-05.
 * Here we place the business logic of the News Model
 */

public class NewsViewModel extends BaseObservable {
    private static final String TAG = NewsViewModel.class.getSimpleName();
    private NewsListItemModel mNews;

    public NewsViewModel(NewsListItemModel mNews) {
        this.mNews = mNews;
    }

    @Bindable
    public String getTitle() {
        return mNews.getTitle();
    }

    public void setTitle(String title) {
        mNews.setTitle(title);
        notifyPropertyChanged(BR.title);
    }

    public String getImageUrl() {
        return mNews.getNews_image();
    }

    @BindingAdapter({"image"})
    public static void loadImage(ImageView view, String url) {
        Log.e(TAG,"http://184.105.143.214:8080/vodafone_studio/api/"+url);
        Picasso.with(view.getContext())
                .load("http://184.105.143.214:8080/vodafone_studio/api/"+url)
                .resize(720,480)
                .centerCrop().into(view);
    }

    public View.OnClickListener onItemClicked(final String title) {
        return view -> Toast.makeText(view.getContext(), "news: " + title, Toast.LENGTH_SHORT).show();
    }


}