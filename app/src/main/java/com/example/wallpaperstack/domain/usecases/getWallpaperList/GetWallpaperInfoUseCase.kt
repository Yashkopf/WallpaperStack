package com.example.wallpaperstack.domain.usecases.getWallpaperList

import com.example.wallpaperstack.domain.model.itemWallpapers.WallpaperItemInfo

interface GetWallpaperInfoUseCase {
    suspend operator fun invoke (id: String): Result<WallpaperItemInfo?>
}