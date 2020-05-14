package com.example.challenge2


import com.google.gson.annotations.SerializedName

data class HomePositifItem(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String
)