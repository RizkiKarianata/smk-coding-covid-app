package com.example.challenge2.item


import com.google.gson.annotations.SerializedName

data class Deaths(
    @SerializedName("detail")
    val detail: String,
    @SerializedName("value")
    val value: Int
)