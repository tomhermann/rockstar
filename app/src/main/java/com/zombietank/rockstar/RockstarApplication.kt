package com.zombietank.rockstar

import android.app.Application
import com.zombietank.rockstar.logging.timberLogger
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import timber.log.Timber

class RockstarApplication : Application() {
    private val loggingTree: Timber.Tree by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            timberLogger(Level.INFO)
            androidContext(this@RockstarApplication)
            modules(Modules.all)
        }

        Timber.plant(loggingTree)
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}