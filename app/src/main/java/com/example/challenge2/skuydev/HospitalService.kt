package com.example.challenge2.skuydev

import com.example.challenge2.HospitalDataItem
import retrofit2.Call
import retrofit2.http.GET

interface HospitalService {
    @GET( "rs_rujukan" )
    fun getHospital(): Call<List<HospitalDataItem>>
}