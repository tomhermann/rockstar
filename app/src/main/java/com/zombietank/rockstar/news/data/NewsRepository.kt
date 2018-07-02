package com.zombietank.rockstar.news.data

import io.reactivex.Observable
import io.reactivex.Single

class NewsRepository(private val newsDatasource: NewsDatasource) {


    fun getTopStories(): Single<List<NewsArticle>> {
        return newsDatasource.topStories
                .flatMapObservable { it -> Observable.fromIterable(it) }
                .flatMapSingle { newsDatasource.getArticle(it) }
                .toList()

    }
}
