package com.zombietank.rockstar.news.data

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class NewsRepository(private val newsDataSource: NewsDataSource) {

    fun getTopStories(): Single<List<NewsArticle>> = newsDataSource.topStories
        .flatMapObservable { Observable.fromIterable(it) }
        .take(50)
        .flatMapSingle { newsDataSource.getArticle(it) }
        .toList()
}
