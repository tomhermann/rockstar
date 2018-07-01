package com.zombietank.rockstar

import com.zombietank.rockstar.dashboard.DashboardFragment
import com.zombietank.rockstar.news.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun providesMainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun providesNewsFragment(): NewsFragment

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun providesDashboardFragment(): DashboardFragment
}
