package com.example.challenge2.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge2.database.AppDatabase
import com.example.challenge2.model.UsersModel
import com.example.challenge2.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel() : ViewModel(){
    lateinit var repository: UsersRepository
    lateinit var allUsers:LiveData<List<UsersModel>>

    fun init(context: Context){
        val usersDao = AppDatabase.getDatabase(context).usersDao()
        repository = UsersRepository(usersDao)
        allUsers = repository.allUsers
    }

    fun addData(user:UsersModel) = viewModelScope.launch(Dispatchers.IO){
        repository.insertUsers(user)
    }

    fun insertAll(users:List<UsersModel>) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteAllUsers()
        repository.insertAllUsers(users)
    }

    fun updateData(user: UsersModel) = viewModelScope.launch(Dispatchers.IO){
        repository.updateUsers(user)
    }
}