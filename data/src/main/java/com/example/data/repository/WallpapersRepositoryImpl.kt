package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.mappers.toWallpapers
import com.example.data.network.api.WallpaperApi
import com.example.data.network.paging.WallpapersPagingSource
import com.example.data.network.utils.safeApiCall
import com.example.domain.model.Sorting
import com.example.domain.model.itemWallpapers.WallpaperSingleDetails
import com.example.domain.model.listWallpapers.WallpapersListDetails
import com.example.domain.repository.WallpapersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class WallpapersRepositoryImpl(
    private val wallpaperApi: WallpaperApi,
) : WallpapersRepository {

    override val itemsCount = MutableStateFlow<Int?>(null)

    override fun getWallpapersList(
        sorting: Sorting,
        query: String?,
        purity: String,
        categories: String,
    ): Flow<PagingData<WallpapersListDetails>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = MAX_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                WallpapersPagingSource(
                    wallpaperApi = wallpaperApi,
                    sorting = sorting,
                    query = query,
                    purity = purity,
                    categories = categories,
                    onItemsCountChange = {
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






