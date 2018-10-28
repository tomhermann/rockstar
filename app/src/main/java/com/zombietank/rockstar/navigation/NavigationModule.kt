package com.zombietank.rockstar.navigation

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val navigationModule: Module = module {
    viewModel { NavigationViewModel() }
}
