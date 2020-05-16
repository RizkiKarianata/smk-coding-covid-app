package com.example.challenge2


import com.google.gson.annotations.SerializedName

data class HospitalDataItem(
    @SerializedName("Alamat")
    val alamat: String,
    @SerializedName("Jumlah_APD")
    val jumlahAPD: Any,
    @SerializedName("Jumlah_Tenaga_Medis")
    val jumlahTenagaMedis: Any,
    @SerializedName("Keterangan")
    val keterangan: String,
    @SerializedName("Nama")
    val nama: String,
    @SerializedName("ObjectId")
    val objectId: Int,
    @SerializedName("Provinsi")
    val provinsi: String,
    @SerializedName("Ruang_Isolasi_Biasa")
    val ruangIsolasiBiasa: Any,
    @SerializedName("Ruang_Isolasi_ICU")
    val ruangIsolasiICU: Any,
    @SerializedName("Ruang_Isolasi_Tekanan")
    val ruangIsolasiTekanan: Any,
    @SerializedName("Telepon")
    val telepon: String,
    @SerializedName("X")
    val x: Double,
    @SerializedName("Y")
    val y: Double
)