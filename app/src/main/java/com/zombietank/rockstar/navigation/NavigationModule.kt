package com.zombietank.rockstar.navigation

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val navigationModule: Module = module {
    viewModel { NavigationViewModel() }
}
