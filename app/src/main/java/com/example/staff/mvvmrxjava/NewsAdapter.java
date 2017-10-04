package com.example.staff.mvvmrxjava;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.staff.mvvmrxjava.databinding.ListItemBinding;

import java.util.List;

/**
 * Created by staff on 2017-10-04.
 */

class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.BindingHolder> {

    private List<NewsModel> mNews;

    public NewsAdapter(List<NewsModel> mNews) {
        this.mNews = mNews;
    }
    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.list_item, parent, false);

        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        ListItemBinding binding = holder.binding;
        binding.setNews(mNews.get(position));
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ListItemBinding binding;

        public BindingHolder(ListItemBinding binding) {
            super(binding.newsHolder);
            this.binding = binding;
        }
    }
}
