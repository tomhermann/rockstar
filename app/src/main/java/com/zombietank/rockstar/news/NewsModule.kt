package com.zombietank.rockstar.news

import com.zombietank.rockstar.news.data.NewsDataSource
import com.zombietank.rockstar.news.data.NewsRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val newsModule = module {
    viewModel { NewsViewModel(get()) }
    single { NewsRepository(get()) }
    single {
        Retrofit.Builder()
                .baseUrl("https://hacker-news.firebaseio.com/v0/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .addCallAdapterFactory(get())
                .build()
                .create(NewsDataSource::class.java)
    }
}
