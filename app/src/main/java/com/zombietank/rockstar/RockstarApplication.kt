package com.zombietank.rockstar

import android.app.Application
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import timber.log.Timber

class RockstarApplication : Application() {
    private val loggingTree: Timber.Tree by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(applicationModule))
        Timber.plant(loggingTree)
    }
}

val applicationModule: Module = applicationContext {
    bean { Timber.DebugTree() as timber.log.Timber.Tree }
}
