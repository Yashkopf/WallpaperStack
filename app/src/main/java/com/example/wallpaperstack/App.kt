package com.example.wallpaperstack

import android.app.Application
import com.example.data.di.networkModule
import com.example.data.di.preferencesModule
import com.example.presentation.di.viewModelModule
import com.example.data.di.repositoryModule
import com.example.domain.di.useCaseModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(listOf(viewModelModule, repositoryModule, networkModule,
                useCaseModule, preferencesModule
            ))
        }
    }
}