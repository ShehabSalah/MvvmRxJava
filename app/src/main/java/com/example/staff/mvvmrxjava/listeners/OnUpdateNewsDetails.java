package com.example.staff.mvvmrxjava.listeners;

import com.example.staff.mvvmrxjava.models.NewsDetailsModel;
import com.example.staff.mvvmrxjava.models.NewsListItemModel;

import java.util.ArrayList;

/**
 * Created by staff on 2017-10-09.
 */

public interface OnUpdateNewsDetails {
    void displayDetail(NewsDetailsModel newsDetailsModel);
}
