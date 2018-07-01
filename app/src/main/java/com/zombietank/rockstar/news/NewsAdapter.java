package com.zombietank.rockstar.news;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zombietank.rockstar.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsArticleViewHolder> {
    private final List<NewsArticle> newsArticles;

    public NewsAdapter(List<NewsArticle> newsArticles) {
        this.newsArticles = newsArticles;
    }

    @NonNull
    @Override
    public NewsArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new NewsArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsArticleViewHolder holder, int position) {
        holder.bind(newsArticles.get(position));
    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.news_article_row;
    }
}
