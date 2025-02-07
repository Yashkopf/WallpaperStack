package com.example.wallpaperstack.di.modules

import com.example.wallpaperstack.data.repository.WallpapersRepository
import com.example.wallpaperstack.data.repository.WallpapersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(wallpapersRepositoryImpl: WallpapersRepositoryImpl): WallpapersRepository
}