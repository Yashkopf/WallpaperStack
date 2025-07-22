package com.example.wallpaperstack.di.modules

import android.content.Context
import com.example.wallpaperstack.data.local.AppPreferences
import com.example.wallpaperstack.data.local.preferences.PreferencesRepositoryImpl
import com.example.wallpaperstack.domain.preferences.PreferencesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferencesModule = module {

    single {
        androidContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }

    single{
        AppPreferences(get())
    }

    single<PreferencesRepository>{
        PreferencesRepositoryImpl(get())
    }
}