package com.example.challenge2.api

import com.example.challenge2.item.Article
import retrofit2.Call
import retrofit2.http.GET

interface ArticleService {
    @GET( "/v2/top-headlines?country=id&category=health&apiKey=bbee86a986db46bf80ff438b2af16712" )
    fun getArticle(): Call<List<Article>>
}