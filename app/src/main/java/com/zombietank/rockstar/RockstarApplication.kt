package com.zombietank.rockstar

import android.app.Application
import com.zombietank.rockstar.news.newsModule
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import timber.log.Timber
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class RockstarApplication : Application() {
    private val loggingTree: Timber.Tree by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(applicationModule, newsModule))
        Timber.plant(loggingTree)
    }
}

val applicationModule: Module = applicationContext {
    bean { Timber.DebugTree() as timber.log.Timber.Tree }
    bean { OkHttpClient() }
    bean { RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()) as CallAdapter.Factory }
}
