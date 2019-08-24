package com.zombietank.rockstar

import com.zombietank.rockstar.navigation.navigationModule
import com.zombietank.rockstar.news.newsModule

object KoinModules {
    val all = listOf(
        applicationModule,
        newsModule,
        navigationModule
    )
}