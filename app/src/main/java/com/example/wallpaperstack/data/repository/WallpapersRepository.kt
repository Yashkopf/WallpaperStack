package com.example.wallpaperstack.data.repository

import androidx.paging.PagingData
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.WallpaperInfo
import kotlinx.coroutines.flow.Flow

interface WallpapersRepository {

    val itemsCount: Flow<Int?>
    fun getWallpapersList(sorting: Sorting, query: String?): Flow<PagingData<WallpaperInfo>>

}