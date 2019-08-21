package com.zombietank.rockstar.news.data

import io.reactivex.Observable
import io.reactivex.Single

class NewsRepository(private val newsDataSource: NewsDataSource) {

    fun getTopStories(): Single<List<NewsArticle>> = newsDataSource.topStories
        .flatMapObservable { Observable.fromIterable(it) }
        .take(50)
        .flatMapSingle { newsDataSource.getArticle(it) }
        .toList()
}
