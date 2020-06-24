package com.example.challenge2.api

import com.example.challenge2.item.NewsArticle
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET( "v2/top-headlines" )
    fun getNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsArticle>
}