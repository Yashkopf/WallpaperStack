package com.example.wallpaperstack.domain.usecases.getWallpaperInfo

import com.example.wallpaperstack.data.repository.WallpapersRepository
import com.example.wallpaperstack.domain.model.itemWallpapers.WallpaperSingleDetails

class GetWallpaperInfoUseCaseImpl(
    private val repository: WallpapersRepository
): GetWallpaperInfoUseCase {

    override suspend fun invoke (id: String): Result<WallpaperSingleDetails?>{
        return repository.getWallpaperInfo(id)
    }
}