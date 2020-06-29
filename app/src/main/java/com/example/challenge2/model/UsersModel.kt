package com.example.challenge2.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UsersModel(
    @PrimaryKey val uid : String,
    var nama : String,
    var username : String,
    var email : String,
    var telepon : String,
    var password : String,
    var alamat : String
) {
    constructor(): this("", "", "", "", "", "", "")
}