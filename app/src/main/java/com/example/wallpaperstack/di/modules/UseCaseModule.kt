package com.example.wallpaperstack.di.modules

import com.example.wallpaperstack.data.repository.WallpapersRepository
import com.example.wallpaperstack.domain.usecases.getWallpaperList.GetWallpaperListUseCase
import com.example.wallpaperstack.domain.usecases.getWallpaperList.GetWallpaperListUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {

    factory <GetWallpaperListUseCase>{
        GetWallpaperListUseCaseImpl(get<WallpapersRepository>())
    }
}