package com.example.wallpaperstack.domain.usecases.getWallpaperInfo

import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.WallpaperInfo

interface GetWallpaperInfoUseCase {

     operator fun invoke(sorting: Sorting)
}