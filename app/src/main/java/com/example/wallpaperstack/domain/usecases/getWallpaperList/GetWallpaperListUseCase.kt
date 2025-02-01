package com.example.wallpaperstack.domain.usecases.getWallpaperList

import com.example.wallpaperstack.domain.model.Wallpapers

interface GetWallpaperListUseCase {

    suspend fun invoke(): Result<Wallpapers>
}