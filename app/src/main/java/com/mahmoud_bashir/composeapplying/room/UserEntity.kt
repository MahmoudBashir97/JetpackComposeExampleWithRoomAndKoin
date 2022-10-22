package com.mahmoud_bashir.composeapplying.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val name:String,
    val age:Int
    )