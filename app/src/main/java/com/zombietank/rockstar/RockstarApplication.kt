package com.zombietank.rockstar

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.zombietank.rockstar.navigation.navigationModule
import com.zombietank.rockstar.news.newsModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import org.koin.standalone.StandAloneContext.closeKoin
import org.koin.standalone.StandAloneContext.stopKoin
import timber.log.Timber

open class RockstarApplication : Application() {
    private val loggingTree: Timber.Tree by inject()

    override fun onCreate() {
        super.onCreate()
        if (isInAnalyzerProcess()) {
            return
        }

        installLeakCanary()

        startKoin(this, listOf(applicationModule, newsModule, navigationModule))
        Timber.plant(loggingTree)
    }

    open fun installLeakCanary() {
        LeakCanary.install(this)
    }

    open fun isInAnalyzerProcess() = LeakCanary.isInAnalyzerProcess(this)

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}