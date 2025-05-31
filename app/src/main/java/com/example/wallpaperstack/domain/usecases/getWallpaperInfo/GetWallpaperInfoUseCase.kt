package com.example.wallpaperstack.domain.usecases.getWallpaperInfo

import com.example.wallpaperstack.domain.model.itemWallpapers.WallpaperSingleDetails

interface GetWallpaperInfoUseCase {
    suspend operator fun invoke (id: String): Result<WallpaperSingleDetails?>
}