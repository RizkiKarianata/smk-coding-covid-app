package com.example.challenge2.api

import com.example.challenge2.item.GlobalCovidItem
import retrofit2.Call
import retrofit2.http.GET

interface GlobalService {
    @GET( "/api/confirmed/" )
    fun getGlobal(): Call<List<GlobalCovidItem>>
}