package com.example.data.di

import android.content.Context
import com.example.data.local.AppPreferences
import com.example.data.local.preferences.PreferencesRepositoryImpl
import com.example.domain.preferences.PreferencesRepository
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