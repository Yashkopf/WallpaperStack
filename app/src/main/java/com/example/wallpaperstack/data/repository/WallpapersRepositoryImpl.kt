package com.example.wallpaperstack.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.wallpaperstack.data.network.api.WallpaperApi
import com.example.wallpaperstack.data.network.paging.WallpapersPagingSource
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.WallpaperInfo
import kotlinx.coroutines.flow.Flow

class WallpapersRepositoryImpl(
    private val api: WallpaperApi,
) : WallpapersRepository {

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
                WallpapersPagingSource(api, sorting, query)
            }
        ).flow
    }
}





