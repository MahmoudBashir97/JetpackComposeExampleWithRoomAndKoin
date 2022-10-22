package com.mahmoud_bashir.composeapplying.room

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase :RoomDatabase() {
    public abstract fun userDao():UserDao
}