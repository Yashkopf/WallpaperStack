package com.example.wallpaperstack.di.modules

import com.example.wallpaperstack.data.network.api.WallpaperApi
import com.example.wallpaperstack.data.repository.WallpapersRepository
import com.example.wallpaperstack.data.repository.WallpapersRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<WallpapersRepository> {
        WallpapersRepositoryImpl(get())
    }
}
