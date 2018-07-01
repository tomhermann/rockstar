package com.zombietank.rockstar.news

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zombietank.rockstar.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsList.layoutManager = LinearLayoutManager(context)
        newsList.adapter = NewsAdapter(fakeNews())
    }

    private fun fakeNews(): List<NewsArticle>? {
        val fakeNews = mutableListOf<NewsArticle>()
        for (i in 1 until 100) {
            fakeNews.add(NewsArticle("Title $i", "Description $i"))
        }
        return fakeNews
    }
}
