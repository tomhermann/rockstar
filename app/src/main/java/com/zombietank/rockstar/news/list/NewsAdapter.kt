package com.zombietank.rockstar.news.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zombietank.rockstar.R
import com.zombietank.rockstar.news.data.NewsArticle
import java.util.*

class NewsAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<NewsArticleViewHolder>() {
    private val newsArticles: MutableList<NewsArticle> = ArrayList()

    fun setArticles(articles: List<NewsArticle>) {
        newsArticles.clear()
        newsArticles.addAll(articles)
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
