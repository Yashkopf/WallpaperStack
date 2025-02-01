package com.example.wallpaperstack.domain.usecases.getWallpaperInfo

import com.example.wallpaperstack.data.repository.WallpapersRepository
import com.example.wallpaperstack.domain.model.WallpaperInfo

class GetWallpaperInfoUseCaseImpl(
   private val repository: WallpapersRepository
): GetWallpaperInfoUseCase {

    override suspend fun invoke(wallpaperId: Int): Result<WallpaperInfo> {
        return repository.getWallpaperInfo(wallpaperId)
    }
}