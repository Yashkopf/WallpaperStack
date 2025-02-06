package com.example.wallpaperstack.di.modules

import com.example.wallpaperstack.data.repository.WallpapersRepository
import com.example.wallpaperstack.domain.usecases.getWallpaperInfo.GetWallpaperInfoUseCase
import com.example.wallpaperstack.domain.usecases.getWallpaperInfo.GetWallpaperInfoUseCaseImpl
import com.example.wallpaperstack.domain.usecases.getWallpaperList.GetWallpaperListUseCase
import com.example.wallpaperstack.domain.usecases.getWallpaperList.GetWallpaperListUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getWallpaperInfoUseCase(repository: WallpapersRepository): GetWallpaperInfoUseCase {
        return GetWallpaperInfoUseCaseImpl(repository)
    }

    @Provides
    fun getWallpaperListUseCase(repository: WallpapersRepository): GetWallpaperListUseCase {
        return GetWallpaperListUseCaseImpl(repository)
    }
}