package com.example.challenge2.item


import com.google.gson.annotations.SerializedName

data class CovidCountryItem(
    @SerializedName("attributes")
    val attributes: Attributes
)