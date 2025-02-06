package com.example.wallpaperstack.domain.usecases.getWallpaperInfo

import com.example.wallpaperstack.data.repository.WallpapersRepository
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.WallpaperInfo
import javax.inject.Inject

class GetWallpaperInfoUseCaseImpl @Inject constructor(
    private val repository: WallpapersRepository,
) : GetWallpaperInfoUseCase {

    override fun invoke(sorting: Sorting): Unit {}
}