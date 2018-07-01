package com.zombietank.rockstar

import dagger.Module
import dagger.Provides
import timber.log.Timber

@Module
class ApplicationModule {

    @Provides
    fun providesLoggingTree(): Timber.Tree {
        return Timber.DebugTree()
    }
}
