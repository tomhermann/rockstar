package com.zombietank.rockstar.news.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zombietank.rockstar.R
import com.zombietank.rockstar.news.data.NewsArticle

import java.util.ArrayList

class NewsAdapter : RecyclerView.Adapter<NewsArticleViewHolder>() {
    private val newsArticles: MutableList<NewsArticle> = ArrayList()

    fun setArticles(newsArticles: List<NewsArticle>) {
        this.newsArticles.clear()
        this.newsArticles.addAll(newsArticles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return NewsArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        holder.bind(newsArticles[position])
    }

    override fun getItemCount(): Int {
        return newsArticles.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.news_article_row
    }
}
