package com.zombietank.rockstar.news.data

import io.reactivex.Observable
import io.reactivex.Single

class NewsRepository(private val newsDataSource: NewsDatasource) {

    fun getTopStories(): Single<List<NewsArticle>> {
        return newsDataSource.topStories
                .flatMapObservable { it -> Observable.fromIterable(it) }
                .take(50)
                .flatMapSingle { newsDataSource.getArticle(it) }
                .toList()
    }
}
