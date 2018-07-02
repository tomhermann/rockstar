package com.zombietank.rockstar.news.data

import java.net.URI

data class NewsArticle(val id: Long, val title: String, val by: String, val description: String, val url: URI)
