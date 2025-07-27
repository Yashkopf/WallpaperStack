package com.example.domain.di

import com.example.domain.usecases.getItemsCount.GetItemsCountUseCase
import com.example.domain.usecases.getItemsCount.GetItemsCountUseCaseImpl
import com.example.domain.usecases.getWallpaperInfo.GetWallpaperInfoUseCase
import com.example.domain.usecases.getWallpaperInfo.GetWallpaperInfoUseCaseImpl
import com.example.domain.usecases.getWallpaperList.GetWallpaperListUseCase
import com.example.domain.usecases.getWallpaperList.GetWallpaperListUseCaseImpl
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