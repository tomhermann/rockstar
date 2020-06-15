package com.zombietank.rockstar.news.data

import androidx.annotation.Keep
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@Keep
@JsonIgnoreProperties(ignoreUnknown = true)
data class NewsArticle(
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("by")
    val by: String,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("url")
    val url: String?
)
