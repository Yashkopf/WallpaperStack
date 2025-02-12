package com.example.wallpaperstack.di.modules

import com.example.wallpaperstack.domain.usecases.getWallpaperList.GetWallpaperListUseCase
import com.example.wallpaperstack.domain.usecases.getWallpaperList.GetWallpaperListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {


    @Binds
    abstract fun getWallpaperListUseCase(getWallpaperListUseCaseImpl: GetWallpaperListUseCaseImpl): GetWallpaperListUseCase
}