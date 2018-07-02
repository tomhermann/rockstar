package com.zombietank.rockstar.news

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zombietank.rockstar.R
import com.zombietank.rockstar.news.list.NewsAdapter
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.android.architecture.ext.viewModel

class NewsFragment : Fragment() {
    private val newsViewModel by viewModel<NewsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onStart() {
        super.onStart()
        newsViewModel.loadTopStories()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsAdapter = NewsAdapter()
        newsList.adapter = newsAdapter
        newsList.layoutManager = LinearLayoutManager(context)

        newsViewModel.stories.observe(this, android.arch.lifecycle.Observer { newsData ->
            newsAdapter.setArticles(newsData)
        })
    }
}
