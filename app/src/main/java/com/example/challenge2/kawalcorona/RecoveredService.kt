package com.example.challenge2.kawalcorona

import com.example.challenge2.CovidRecovered
import retrofit2.Call
import retrofit2.http.GET

interface RecoveredService {
    @GET( "sembuh" )
    fun getRecovered(): Call<List<CovidRecovered>>
}