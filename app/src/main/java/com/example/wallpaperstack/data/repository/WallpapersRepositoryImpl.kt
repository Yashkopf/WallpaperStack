package com.example.wallpaperstack.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.wallpaperstack.data.mappers.toWallpapers
import com.example.wallpaperstack.data.network.api.WallpaperApi
import com.example.wallpaperstack.data.network.paging.WallpapersPagingSource
import com.example.wallpaperstack.data.network.utils.safeApiCall
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.itemWallpapers.WallpaperSingleDetails
import com.example.wallpaperstack.domain.model.listWallpapers.WallpapersListDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class WallpapersRepositoryImpl(
    private val wallpaperApi: WallpaperApi,
) : WallpapersRepository {

    override val itemsCount = MutableStateFlow<Int?>(null)

    override fun getWallpapersList(
        sorting: Sorting,
        query: String?,
    ): Flow<PagingData<WallpapersListDetails>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = MAX_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                WallpapersPagingSource(wallpaperApi, sorting, query, onItemsCountChange = {
                    itemsCount.value = it
                })
            }
        ).flow
    }

    override suspend fun getWallpaperInfo(id: String): Result<WallpaperSingleDetails?> {
        return safeApiCall {
            Result.success(wallpaperApi.getWallpaperInfo(id).body()?.toWallpapers())
        }
    }

    companion object {
        const val MAX_PAGE_SIZE = 24
        const val PAGE_SIZE = 7
    }
}






