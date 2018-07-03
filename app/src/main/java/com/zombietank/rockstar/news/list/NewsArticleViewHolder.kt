package com.zombietank.rockstar.news.list

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import com.zombietank.rockstar.news.data.NewsArticle
import kotlinx.android.synthetic.main.news_article_row.view.*

class NewsArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(newsArticle: NewsArticle) {
        itemView.title.text = newsArticle.title
        itemView.author.text = newsArticle.by
        itemView.setOnClickListener {
            it.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(newsArticle.url)))
        }
    }
}
