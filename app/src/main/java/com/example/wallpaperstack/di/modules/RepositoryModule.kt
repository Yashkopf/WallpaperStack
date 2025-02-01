package com.example.wallpaperstack.di.modules

import com.example.wallpaperstack.data.network.api.WallpaperApi
import com.example.wallpaperstack.data.network.mappers.WallpapersResponseToEntityMapper
import com.example.wallpaperstack.data.repository.WallpapersRepository
import com.example.wallpaperstack.data.repository.WallpapersRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(api: WallpaperApi, mapper: WallpapersResponseToEntityMapper): WallpapersRepository {
        return WallpapersRepositoryImpl(api, mapper)
    }
}