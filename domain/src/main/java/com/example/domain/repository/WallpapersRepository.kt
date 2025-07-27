package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.Sorting
import com.example.domain.model.itemWallpapers.WallpaperSingleDetails
import com.example.domain.model.listWallpapers.WallpapersListDetails
import kotlinx.coroutines.flow.Flow

interface WallpapersRepository {

    val itemsCount: Flow<Int?>
    fun getWallpapersList(
        sorting: Sorting,
        query: String?,
        purity: String,
        categories: String,
    ): Flow<PagingData<WallpapersListDetails>>

    suspend fun getWallpaperInfo(id: String): Result<WallpaperSingleDetails?>

}