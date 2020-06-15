package com.zombietank.rockstar

import com.zombietank.rockstar.navigation.navigationModule
import com.zombietank.rockstar.news.newsModule

object Modules {
    val all = listOf(
        applicationModule,
        newsModule,
        navigationModule
    )
}
