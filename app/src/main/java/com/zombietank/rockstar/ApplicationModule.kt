package com.zombietank.rockstar

import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import timber.log.Timber

val applicationModule = module {
    single { Timber.DebugTree() as Timber.Tree }
    single { OkHttpClient() }
    single { RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()) as CallAdapter.Factory }
}
