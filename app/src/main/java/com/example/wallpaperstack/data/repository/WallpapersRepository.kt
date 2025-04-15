package com.example.wallpaperstack.data.repository

import androidx.paging.PagingData
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.itemWallpapers.WallpaperItemInfo
import com.example.wallpaperstack.domain.model.listWallpapers.WallpaperInfo
import kotlinx.coroutines.flow.Flow

interface WallpapersRepository {

    val itemsCount: Flow<Int?>
    fun getWallpapersList(sorting: Sorting, query: String?): Flow<PagingData<WallpaperInfo>>
    suspend fun getWallpaperInfo(id: String): Result<WallpaperItemInfo?>

}