package com.zombietank.rockstar

import com.zombietank.rockstar.navigation.navigationModule
import com.zombietank.rockstar.news.newsModule

object KoinModules {

    fun get() = listOf(applicationModule, newsModule, navigationModule)
}