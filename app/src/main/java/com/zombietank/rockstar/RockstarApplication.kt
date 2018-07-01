package com.zombietank.rockstar

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

class RockstarApplication : DaggerApplication() {
    @Inject lateinit var loggingTree: Timber.Tree

    override fun onCreate() {
        super.onCreate()
        Timber.plant(loggingTree)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
                .application(this)
                .build()
    }
}
