package com.example.wallpaperstack.domain.usecases.getWallpaperList

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.WallpaperInfo

interface GetWallpaperListUseCase {

    operator fun invoke(sorting: Sorting): LiveData<PagingData<WallpaperInfo>>
}