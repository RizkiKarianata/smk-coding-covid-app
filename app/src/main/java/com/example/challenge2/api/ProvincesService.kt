package com.example.challenge2.api

import com.example.challenge2.item.CovidProvincesItem
import retrofit2.Call
import retrofit2.http.GET

interface ProvincesService {
    @GET( "/indonesia/provinsi" )
    fun getProvinces(): Call<List<CovidProvincesItem>>
}