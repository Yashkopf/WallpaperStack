package com.example.wallpaperstack.di.modules

import com.example.wallpaperstack.data.network.api.WallpaperApi
import com.example.wallpaperstack.data.repository.WallpapersRepository
import com.example.wallpaperstack.data.repository.WallpapersRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(api: WallpaperApi): WallpapersRepository {
        return WallpapersRepositoryImpl(api)
    }
}