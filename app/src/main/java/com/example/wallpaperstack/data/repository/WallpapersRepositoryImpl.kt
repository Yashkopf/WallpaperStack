package com.example.wallpaperstack.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.wallpaperstack.data.mappers.toWallTest
import com.example.wallpaperstack.data.network.api.WallpaperApi
import com.example.wallpaperstack.data.network.paging.WallpapersPagingSource
import com.example.wallpaperstack.data.network.utils.safeApiCall
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.itemWallpapers.WallpaperItemInfo
import com.example.wallpaperstack.domain.model.listWallpapers.WallpaperInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class WallpapersRepositoryImpl(
    private val wallpaperApi: WallpaperApi,
) : WallpapersRepository {

    override val itemsCount = MutableStateFlow<Int?>(null)

    override fun getWallpapersList(
        sorting: Sorting,
        query: String?,
    ): Flow<PagingData<WallpaperInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 7,
                maxSize = 24,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                WallpapersPagingSource(wallpaperApi, sorting, query, onItemsCountChange = {
                    itemsCount.value = it
                })
            }
        ).flow
    }

    override suspend fun getWallpaperInfo(id: String): Result<WallpaperItemInfo?> {
        return safeApiCall {
            Result.success(wallpaperApi.getWallpaperInfo(id).body()?.toWallTest())
        }
    }
    companion object {

    }
}






