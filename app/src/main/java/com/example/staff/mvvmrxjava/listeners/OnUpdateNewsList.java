package com.example.staff.mvvmrxjava.listeners;

import com.example.staff.mvvmrxjava.models.NewsListItemModel;

import java.util.ArrayList;

/**
 * Created by staff on 2017-10-08.
 *
 */

public interface OnUpdateNewsList {
    void displayList(ArrayList<NewsListItemModel> newsModelArrayList);

}