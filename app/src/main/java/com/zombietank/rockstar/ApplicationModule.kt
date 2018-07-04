package com.zombietank.rockstar

import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import retrofit2.CallAdapter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import timber.log.Timber

val applicationModule: Module = applicationContext {
    bean { Timber.DebugTree() as timber.log.Timber.Tree }
    bean { OkHttpClient() }
    bean { RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()) as CallAdapter.Factory }
}
