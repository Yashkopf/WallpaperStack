package com.example.wallpaperstack.di.modules

import com.example.wallpaperstack.domain.usecases.getWallpaperList.GetItemsCountUseCase
import com.example.wallpaperstack.domain.usecases.getWallpaperList.GetItemsCountUseCaseImpl
import com.example.wallpaperstack.domain.usecases.getWallpaperList.GetWallpaperInfoUseCase
import com.example.wallpaperstack.domain.usecases.getWallpaperList.GetWallpaperInfoUseCaseImpl
import com.example.wallpaperstack.domain.usecases.getWallpaperList.GetWallpaperListUseCase
import com.example.wallpaperstack.domain.usecases.getWallpaperList.GetWallpaperListUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {

    factory <GetWallpaperListUseCase>{
        GetWallpaperListUseCaseImpl(get())
    }
    factory<GetItemsCountUseCase>{
        GetItemsCountUseCaseImpl(get())
    }
    factory<GetWallpaperInfoUseCase>{
        GetWallpaperInfoUseCaseImpl(get())
    }
}