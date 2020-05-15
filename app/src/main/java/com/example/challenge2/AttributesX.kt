package com.example.challenge2


import com.google.gson.annotations.SerializedName

data class AttributesX(
    @SerializedName("Active")
    val active: Int,
    @SerializedName("Confirmed")
    val confirmed: Int,
    @SerializedName("Country_Region")
    val countryRegion: String,
    @SerializedName("Deaths")
    val deaths: Int,
    @SerializedName("Last_Update")
    val lastUpdate: Long,
    @SerializedName("Lat")
    val lat: Double,
    @SerializedName("Long_")
    val long: Double,
    @SerializedName("OBJECTID")
    val oBJECTID: Int,
    @SerializedName("Recovered")
    val recovered: Int
)