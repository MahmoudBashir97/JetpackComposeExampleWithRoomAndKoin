package com.mahmoud_bashir.composeapplying.app

import android.app.Application
import com.mahmoud_bashir.composeapplying.di.appModule
import com.mahmoud_bashir.composeapplying.di.mainViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                appModule,
                mainViewModelModule
            )
        }
    }
}