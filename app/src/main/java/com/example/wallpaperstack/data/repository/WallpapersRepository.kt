package com.example.wallpaperstack.data.repository

import androidx.paging.PagingData
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.itemWallpapers.WallpaperSingleDetails
import com.example.wallpaperstack.domain.model.listWallpapers.WallpapersListDetails
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