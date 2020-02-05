package com.zombietank.rockstar.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.zombietank.rockstar.R
import com.zombietank.rockstar.news.list.NewsAdapter
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

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

        newsViewModel.stories.observe(viewLifecycleOwner, Observer { newsData ->
            newsData?.let { newsAdapter.setArticles(newsData) }
        })

        swipeRefreshContainer.setOnRefreshListener {
            newsViewModel.loadTopStories()
        }

        newsViewModel.loading.observe(viewLifecycleOwner, Observer { refreshing ->
            refreshing?.let { swipeRefreshContainer.isRefreshing = it }
        })

    }
}
