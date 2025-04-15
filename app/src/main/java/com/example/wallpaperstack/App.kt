package com.example.wallpaperstack

import android.app.Application
import com.example.wallpaperstack.di.modules.networkModule
import com.example.wallpaperstack.di.modules.repositoryModule
import com.example.wallpaperstack.di.modules.useCaseModule
import com.example.wallpaperstack.di.modules.viewModelModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(listOf(viewModelModule, repositoryModule, networkModule, useCaseModule
            ))
        }
    }
}