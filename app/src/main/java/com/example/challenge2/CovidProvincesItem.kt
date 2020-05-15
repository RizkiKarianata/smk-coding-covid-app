package com.example.challenge2


import com.google.gson.annotations.SerializedName

data class CovidProvincesItem(
    @SerializedName("attributes")
    val attributes: Attributes
)