package com.example.wallpaperstack.domain.usecases.getWallpaperList

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.WallpaperInfo
import kotlinx.coroutines.flow.Flow

interface GetWallpaperListUseCase {

    operator fun invoke(sorting: Sorting): Flow<PagingData<WallpaperInfo>>
}