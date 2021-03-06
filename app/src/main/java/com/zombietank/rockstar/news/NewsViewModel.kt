package com.zombietank.rockstar.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zombietank.rockstar.SubscriptionAwareViewModel
import com.zombietank.rockstar.news.data.NewsArticle
import com.zombietank.rockstar.news.data.NewsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber

class NewsViewModel(private val newsRepository: NewsRepository) : SubscriptionAwareViewModel() {
    private val newsData: MutableLiveData<List<NewsArticle>> = MutableLiveData()
    private val loadingNews: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loadTopStories()
    }

    fun loadTopStories() {
        newsRepository.getTopStories()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loadingNews.value = true }
            .doAfterTerminate { loadingNews.value = false }
            .subscribeBy(
                onSuccess = { newsData.value = it },
                onError = { Timber.e(it) }
            )
            .disposeOnCleared()
    }

    val stories: LiveData<List<NewsArticle>>
        get() = newsData

    val loading: LiveData<Boolean>
        get() = loadingNews
}

