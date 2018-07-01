package com.zombietank.rockstar.news

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.news_article_row.view.*

class NewsArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(newsArticle: NewsArticle) {
        itemView.title.text = newsArticle.title
        itemView.description.text = newsArticle.description
    }
}
