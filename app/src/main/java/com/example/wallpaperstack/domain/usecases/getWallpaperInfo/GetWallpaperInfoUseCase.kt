package com.example.wallpaperstack.domain.usecases.getWallpaperInfo

import com.example.wallpaperstack.domain.model.WallpaperInfo

interface GetWallpaperInfoUseCase {

    suspend fun invoke(wallpaperId: Int): Result<WallpaperInfo>
}