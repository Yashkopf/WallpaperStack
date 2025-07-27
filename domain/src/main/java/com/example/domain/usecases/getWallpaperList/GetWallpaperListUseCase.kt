package com.example.domain.usecases.getWallpaperList

import androidx.paging.PagingData
import com.example.domain.model.Sorting
import com.example.domain.model.listWallpapers.WallpapersListDetails
import kotlinx.coroutines.flow.Flow

interface GetWallpaperListUseCase {

    operator fun invoke(
        sorting: Sorting,
        query: String?,
        purity: String,
        categories: String): Flow<PagingData<WallpapersListDetails>>
}