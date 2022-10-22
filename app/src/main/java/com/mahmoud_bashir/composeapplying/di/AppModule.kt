package com.mahmoud_bashir.composeapplying.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.mahmoud_bashir.composeapplying.repository.LocalRepo
import com.mahmoud_bashir.composeapplying.room.UserDao
import com.mahmoud_bashir.composeapplying.room.UserDatabase
import com.mahmoud_bashir.composeapplying.ui.main.MainViewModel

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule= module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            UserDatabase::class.java,
            "user_db"
        ).build()
    }

    single<UserDao>{
        val db= get<UserDatabase>()
        db.userDao()
    }

    single { LocalRepo(get()) }
}

val mainViewModelModule= module {
    viewModel {
        MainViewModel(androidApplication(),get())
    }
}