package com.zombietank.rockstar.news.data;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewsDatasource {

    @GET("topstories.json")
    Single<List<Long>> getTopStories();

    @GET("item/{itemId}.json")
    Single<NewsArticle> getArticle(@Path("itemId") long id);
}
