package com.example.challenge2.kawalcorona

import com.example.challenge2.CovidPositif
import retrofit2.Call
import retrofit2.http.GET

interface PositifService {
    @GET( "/positif" )
    fun getPositif(): Call<List<CovidPositif>>
}