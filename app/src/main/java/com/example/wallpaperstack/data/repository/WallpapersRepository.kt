package com.example.wallpaperstack.data.repository

import com.example.wallpaperstack.domain.model.WallpaperInfo
import com.example.wallpaperstack.domain.model.Wallpapers
import javax.inject.Inject

interface WallpapersRepository  {
    suspend fun getWallpapersList(): Result<Wallpapers>

    suspend fun getWallpaperInfo(wallpaperId: Int): Result<WallpaperInfo>
}