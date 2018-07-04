package com.zombietank.rockstar

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.zombietank.rockstar.navigation.navigationModule
import com.zombietank.rockstar.news.newsModule
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import timber.log.Timber
import okhttp3.OkHttpClient
import org.koin.standalone.StandAloneContext.closeKoin
import retrofit2.CallAdapter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class RockstarApplication : Application() {
    private val loggingTree: Timber.Tree by inject()

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }

        LeakCanary.install(this)

        startKoin(this, listOf(applicationModule, newsModule, navigationModule))
        Timber.plant(loggingTree)
    }

    override fun onTerminate() {
        super.onTerminate()
        closeKoin()
    }
}