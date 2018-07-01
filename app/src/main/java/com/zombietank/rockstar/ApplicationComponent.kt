package com.zombietank.rockstar

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidBindingModule::class,
    ApplicationModule::class,
    AndroidSupportInjectionModule::class
])
interface ApplicationComponent : AndroidInjector<RockstarApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): ApplicationComponent.Builder

        fun build(): ApplicationComponent
    }
}
