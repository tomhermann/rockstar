package com.zombietank.rockstar.news.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsDataSource {

    @get:GET("topstories.json")
    val topStories: Single<List<Long>>

    @GET("item/{itemId}.json")
    fun getArticle(@Path("itemId") id: Long): Single<NewsArticle>
}
