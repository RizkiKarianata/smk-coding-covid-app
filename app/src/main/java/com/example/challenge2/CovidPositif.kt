package com.example.challenge2


import com.google.gson.annotations.SerializedName

data class CovidPositif(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String
)