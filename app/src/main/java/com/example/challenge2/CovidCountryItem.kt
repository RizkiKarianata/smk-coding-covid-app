package com.example.challenge2


import com.google.gson.annotations.SerializedName

data class CovidCountryItem(
    @SerializedName("attributes")
    val attributes: AttributesX
)