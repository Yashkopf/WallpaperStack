package com.example.wallpaperstack.di.modules

import com.example.wallpaperstack.domain.usecases.getItemsCount.GetItemsCountUseCase
import com.example.wallpaperstack.domain.usecases.getItemsCount.GetItemsCountUseCaseImpl
import com.example.wallpaperstack.domain.usecases.getWallpaperInfo.GetWallpaperInfoUseCase
import com.example.wallpaperstack.domain.usecases.getWallpaperInfo.GetWallpaperInfoUseCaseImpl
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