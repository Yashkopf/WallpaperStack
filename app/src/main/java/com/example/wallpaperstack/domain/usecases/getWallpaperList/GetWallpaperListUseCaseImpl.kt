package com.example.wallpaperstack.domain.usecases.getWallpaperList

import com.example.wallpaperstack.data.repository.WallpapersRepository
import com.example.wallpaperstack.domain.model.Wallpapers

class GetWallpaperListUseCaseImpl(
    private val repository: WallpapersRepository
): GetWallpaperListUseCase {

   override suspend fun invoke(): Result<Wallpapers> {
        return repository.getWallpapersList()
    }
}