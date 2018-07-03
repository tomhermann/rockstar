package com.zombietank.rockstar.news

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

import com.zombietank.rockstar.news.data.NewsArticle
import com.zombietank.rockstar.news.data.NewsRepository
import com.zombietank.rockstar.util.AbstractViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class NewsViewModel(private val newsRepository: NewsRepository) : AbstractViewModel() {
    private val newsData: MutableLiveData<List<NewsArticle>> = MutableLiveData()

    val stories: LiveData<List<NewsArticle>>
        get() = newsData

    init {
        loadTopStories()
    }

    fun loadTopStories() {
        launch {
            newsRepository.getTopStories()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = { newsData.value = it },
                            onError = { Timber.e(it) }

                    )
        }
    }
}
