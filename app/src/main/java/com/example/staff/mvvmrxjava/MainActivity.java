package com.example.staff.mvvmrxjava;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.staff.mvvmrxjava.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.newsList.setLayoutManager(layoutManager);
        ArrayList<NewsModel> newsList = new ArrayList<>();


        NewsAdapter adapter = new NewsAdapter(newsList);
        binding.newsList.setAdapter(adapter);
        /*TextView textView = (TextView)findViewById(R.id.textView);
        textView.setOnClickListener(view -> textView.setText("This app use lambda expression"));*/

    }
}
