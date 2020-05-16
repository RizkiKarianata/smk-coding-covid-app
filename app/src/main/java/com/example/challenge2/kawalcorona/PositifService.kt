package com.example.challenge2.kawalcorona

import com.example.challenge2.CovidPositifItem
import retrofit2.Call
import retrofit2.http.GET

interface PositifService {
    @GET( "/positif" )
    fun getPositif(): Call<List<CovidPositifItem>>
}