package com.mahmoud_bashir.composeapplying.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoud_bashir.composeapplying.repository.LocalRepo
import com.mahmoud_bashir.composeapplying.room.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(app:Application,val repo:LocalRepo):AndroidViewModel(app){

    init {
        getAllUsers()
    }
    val usersList:MutableLiveData<List<UserEntity>> = MutableLiveData()

    fun insertUserData(user: UserEntity) = viewModelScope.launch(Dispatchers.IO){
            repo.insertUserInfo(user)
    }

    fun getAllUsers()= viewModelScope.launch {
        repo.getAllUsersData()
            .catch { e->e.printStackTrace() }
            .collect { mlist ->
                usersList.postValue(mlist)
            }
    }
}