package com.example.staff.mvvmrxjava.view_models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.example.staff.mvvmrxjava.BR;
import com.example.staff.mvvmrxjava.models.NewsDetailsModel;
import com.squareup.picasso.Picasso;

/**
 * Created by staff on 2017-10-09.
 *
 */

public class NewsDetailsViewModel extends BaseObservable {
    private static final String TAG = NewsDetailsViewModel.class.getSimpleName();
    private NewsDetailsModel mDetails;

    public NewsDetailsViewModel(NewsDetailsModel mDetails) {
        this.mDetails = mDetails;
    }

    @Bindable
    public String getTitle(){return mDetails.getTitle();}

    public void setTitle(String title){
        mDetails.setTitle(title);
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getBody(){return mDetails.getBody();}

    public void setBody(String body){
        mDetails.setBody(body);
        notifyPropertyChanged(BR.body);
    }

    @Bindable
    public String getViews(){return mDetails.getViews();}

    @Bindable
    public String getNewsDate(){return mDetails.getNewsDate();}

    public String getImagePath(){return mDetails.getImagePath();}

    @Bindable({"news_image"})
    public static void loadImage(ImageView view, String url) {
        Log.e(TAG,"http://184.105.143.214:8080/vodafone_studio/api/"+url);
        Picasso.with(view.getContext())
                .load("http://184.105.143.214:8080/vodafone_studio/api/"+url)
                .resize(720,480)
                .centerCrop().into(view);
    }


}
