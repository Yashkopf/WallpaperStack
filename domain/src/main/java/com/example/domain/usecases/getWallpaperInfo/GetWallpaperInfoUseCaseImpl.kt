package com.example.domain.usecases.getWallpaperInfo

import com.example.domain.model.itemWallpapers.WallpaperSingleDetails
import com.example.domain.repository.WallpapersRepository

internal class GetWallpaperInfoUseCaseImpl(
    private val repository: WallpapersRepository
): GetWallpaperInfoUseCase {

    override suspend fun invoke (id: String): Result<WallpaperSingleDetails?>{
        return repository.getWallpaperInfo(id)
    }
}