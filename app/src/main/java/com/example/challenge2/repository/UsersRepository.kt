package com.example.challenge2.repository

import androidx.lifecycle.LiveData
import com.example.challenge2.dao.UsersDao
import com.example.challenge2.model.UsersModel

class UsersRepository(private val usersDao: UsersDao) {

    val allUsers: LiveData<List<UsersModel>> = usersDao.getAllUsers()

    suspend fun insertUsers(user: UsersModel) {
        usersDao.insertUser(user)
    }

    suspend fun getUser(uid: String){
        usersDao.getUser(uid)
    }

    suspend fun insertAllUsers(users: List<UsersModel>) {
        usersDao.insertAllUser(users)
    }

    suspend fun deleteAllUsers() {
        usersDao.deleteAllUsers()
    }

    suspend fun updateUsers(user: UsersModel) {
        usersDao.updateUser(user)
    }

    suspend fun deleteUser(user: UsersModel) {
        usersDao.deleteUser(user)
    }
}