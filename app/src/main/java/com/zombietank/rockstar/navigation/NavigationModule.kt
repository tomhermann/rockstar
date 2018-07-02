package com.zombietank.rockstar.navigation

import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val navigationModule: Module = applicationContext {
    viewModel { NavigationViewModel() }
}
