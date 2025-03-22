package com.example.wallpaperstack.data.repository

import androidx.paging.PagingData
import com.example.wallpaperstack.data.network.model.WallpapersResponse
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.WallpaperInfo
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface WallpapersRepository {

    fun getWallpapersList(sorting: Sorting, query: String?): Flow<PagingData<WallpaperInfo>>

}