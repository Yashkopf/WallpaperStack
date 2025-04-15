package com.example.wallpaperstack.domain.usecases.getWallpaperList

import com.example.wallpaperstack.data.repository.WallpapersRepository
import com.example.wallpaperstack.domain.model.itemWallpapers.WallpaperItemInfo

class GetWallpaperInfoUseCaseImpl(
    private val repository: WallpapersRepository
): GetWallpaperInfoUseCase {

    override suspend fun invoke (id: String): Result<WallpaperItemInfo?>{
        return repository.getWallpaperInfo(id)
    }
}