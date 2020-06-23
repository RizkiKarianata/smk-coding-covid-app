package com.example.challenge2.item


import com.google.gson.annotations.SerializedName

data class AttributesX(
    @SerializedName("FID")
    val fID: Int,
    @SerializedName("Kasus_Meni")
    val kasusMeni: Int,
    @SerializedName("Kasus_Posi")
    val kasusPosi: Int,
    @SerializedName("Kasus_Semb")
    val kasusSemb: Int,
    @SerializedName("Kode_Provi")
    val kodeProvi: Int,
    @SerializedName("Provinsi")
    val provinsi: String
)