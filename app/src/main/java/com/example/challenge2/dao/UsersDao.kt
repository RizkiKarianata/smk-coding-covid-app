package com.example.challenge2.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.challenge2.model.UsersModel

@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<UsersModel>>

    @Query("SELECT * FROM users WHERE uid = :uid")
    fun getUser(uid: String): LiveData<UsersModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UsersModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUser(users: List<UsersModel>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: UsersModel)

    @Delete()
    suspend fun deleteUser(user: UsersModel)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}