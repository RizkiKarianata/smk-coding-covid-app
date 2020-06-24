package com.example.challenge2.api

import com.example.challenge2.item.CovidGlobal
import retrofit2.Call
import retrofit2.http.GET

interface GlobalService {
    @GET( "/api/" )
    fun getGlobal(): Call<List<CovidGlobal>>
}