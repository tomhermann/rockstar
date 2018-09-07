package com.zombietank.rockstar.news

import com.zombietank.rockstar.news.data.NewsDataSource
import com.zombietank.rockstar.news.data.NewsRepository
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val newsModule: Module = module {
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
