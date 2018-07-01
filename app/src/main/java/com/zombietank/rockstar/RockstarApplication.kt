package com.zombietank.rockstar

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class RockstarApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<RockstarApplication>? {
        return DaggerApplicationComponent.builder()
                .application(this)
                .build()
    }
}
