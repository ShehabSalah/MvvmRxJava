package com.example.staff.mvvmrxjava.listner;

import com.example.staff.mvvmrxjava.models.NewsModel;

import java.util.ArrayList;

/**
 * Created by staff on 2017-10-08.
 */

public interface OnUpdateList {

    void addData(ArrayList<NewsModel> newsModelArrayList);
}
