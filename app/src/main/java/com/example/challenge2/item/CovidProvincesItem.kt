package com.example.challenge2.item


import com.google.gson.annotations.SerializedName

data class CovidProvincesItem(
    @SerializedName("attributes")
    val attributes: AttributesX
)