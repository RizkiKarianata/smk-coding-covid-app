package com.example.challenge2.kawalcorona

import com.example.challenge2.CovidCountryItem
import retrofit2.Call
import retrofit2.http.GET

interface CountryService {
    @GET( "/" )
    fun getCountry(): Call<List<CovidCountryItem>>
}