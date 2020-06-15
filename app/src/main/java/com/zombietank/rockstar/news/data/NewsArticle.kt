package com.zombietank.rockstar.news.data

import com.squareup.moshi.Json

data class NewsArticle(
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "by")
    val by: String,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "url")
    val url: String?
)
