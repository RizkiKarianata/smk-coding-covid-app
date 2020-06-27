package com.example.challenge2.api

import com.example.challenge2.item.DetailGlobalCovidItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailGlobalService {

    @GET("/api/countries/{country}/confirmed/")
    fun getDetailGlobal(@Path("country") country: String): Call<List<DetailGlobalCovidItem>>

}