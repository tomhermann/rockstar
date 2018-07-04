package com.zombietank.rockstar.news

import com.zombietank.rockstar.news.data.NewsDataSource
import com.zombietank.rockstar.news.data.NewsRepository
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

val newsModule: Module = applicationContext {
    viewModel { NewsViewModel(get()) }
    bean { NewsRepository(get()) }
    bean {
        Retrofit.Builder()
                .baseUrl("https://hacker-news.firebaseio.com/v0/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .addCallAdapterFactory(get())
                .build()
                .create(NewsDataSource::class.java)
    }
}
