package com.example.challenge2.item


import com.google.gson.annotations.SerializedName

data class NewsArticle(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)