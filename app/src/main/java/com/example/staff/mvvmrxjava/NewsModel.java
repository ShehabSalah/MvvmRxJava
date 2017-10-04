package com.example.staff.mvvmrxjava;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by staff on 2017-10-04.
 *
 */

public class NewsModel extends BaseObservable{
    private String title;
    private String body;
    private String image;

    public NewsModel(String title, String body, String image) {
        this.title = title;
        this.body = body;
        this.image = image;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR._all);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public View.OnClickListener onItemClicked(final String title) {
        return view -> Toast.makeText(view.getContext(), "news: " + title, Toast.LENGTH_SHORT).show();
    }
}
