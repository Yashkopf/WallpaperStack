package com.example.domain.usecases.getWallpaperInfo

import com.example.domain.model.itemWallpapers.WallpaperSingleDetails

interface GetWallpaperInfoUseCase {
    suspend operator fun invoke (id: String): Result<WallpaperSingleDetails?>
}