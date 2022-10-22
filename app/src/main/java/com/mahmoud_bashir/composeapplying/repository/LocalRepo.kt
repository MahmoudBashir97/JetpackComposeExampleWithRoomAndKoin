package com.mahmoud_bashir.composeapplying.repository

import com.mahmoud_bashir.composeapplying.room.UserDao
import com.mahmoud_bashir.composeapplying.room.UserEntity
import kotlinx.coroutines.flow.Flow


class LocalRepo(private val dao:UserDao) {

     fun insertUserInfo(user:UserEntity
     )=dao.insertUser(user)

     fun getAllUsersData():Flow<List<UserEntity>> = dao.getAllUsers()
}