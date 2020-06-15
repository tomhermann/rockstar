package com.zombietank.rockstar.news.data

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class NewsRepository(
    private val newsDataSource: NewsDataSource
) {

    fun getTopStories(count: Int = 50): Single<List<NewsArticle>> {
        require(count > 0)

        return newsDataSource.topStories
            .flatMapObservable { Observable.fromIterable(it) }
            .take(count.toLong())
            .flatMapSingle { newsDataSource.getArticle(it) }
            .toList()
    }
}
