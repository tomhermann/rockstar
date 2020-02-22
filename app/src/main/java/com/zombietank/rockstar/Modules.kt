package com.zombietank.rockstar

import com.zombietank.rockstar.navigation.navigationModule
import com.zombietank.rockstar.news.newsModule
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import timber.log.Timber

object Modules {
    val all = listOf(
        applicationModule,
        newsModule,
        navigationModule
    )
}
