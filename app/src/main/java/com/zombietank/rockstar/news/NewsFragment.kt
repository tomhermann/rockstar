package com.zombietank.rockstar.news

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zombietank.rockstar.R
import com.zombietank.rockstar.news.list.NewsAdapter
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.android.architecture.ext.sharedViewModel

class NewsFragment : Fragment() {
    private val newsViewModel by sharedViewModel<NewsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsAdapter = NewsAdapter()
        newsList.adapter = newsAdapter
        newsList.layoutManager = LinearLayoutManager(context)

        newsViewModel.stories.observe(this, Observer { newsData ->
            newsAdapter.setArticles(newsData)
            swipeRefreshContainer.isRefreshing = false
        })

        swipeRefreshContainer.setOnRefreshListener {
            newsViewModel.loadTopStories()
        }
    }
}
