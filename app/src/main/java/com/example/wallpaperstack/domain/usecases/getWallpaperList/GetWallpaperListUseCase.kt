package com.example.wallpaperstack.domain.usecases.getWallpaperList

import androidx.paging.PagingData
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.listWallpapers.WallpaperInfo
import kotlinx.coroutines.flow.Flow

interface GetWallpaperListUseCase {

    operator fun invoke(sorting: Sorting, query: String?): Flow<PagingData<WallpaperInfo>>
}