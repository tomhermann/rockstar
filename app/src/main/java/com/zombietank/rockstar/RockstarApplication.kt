package com.zombietank.rockstar

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.zombietank.rockstar.logging.timberLogger
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import timber.log.Timber

open class RockstarApplication : Application() {
    private val loggingTree: Timber.Tree by inject()

    override fun onCreate() {
        super.onCreate()
        if (isInAnalyzerProcess()) {
            return
        }

        installLeakCanary()

        startKoin {
            timberLogger()
            androidContext(this@RockstarApplication)
            modules(KoinModules.all)
        }

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