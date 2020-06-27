package com.example.challenge2.item


import com.google.gson.annotations.SerializedName

data class DetailGlobalCovidItem(
    @SerializedName("active")
    val active: Int,
    @SerializedName("admin2")
    val admin2: Any,
    @SerializedName("combinedKey")
    val combinedKey: String,
    @SerializedName("confirmed")
    val confirmed: Int,
    @SerializedName("countryRegion")
    val countryRegion: String,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("fips")
    val fips: Any,
    @SerializedName("incidentRate")
    val incidentRate: Double,
    @SerializedName("iso3")
    val iso3: String,
    @SerializedName("lastUpdate")
    val lastUpdate: Long,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("long")
    val long: Double,
    @SerializedName("peopleHospitalized")
    val peopleHospitalized: Any,
    @SerializedName("peopleTested")
    val peopleTested: Any,
    @SerializedName("provinceState")
    val provinceState: String,
    @SerializedName("recovered")
    val recovered: Int,
    @SerializedName("uid")
    val uid: Int
)