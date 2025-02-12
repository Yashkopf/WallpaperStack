package com.example.wallpaperstack.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.wallpaperstack.data.network.api.WallpaperApi
import com.example.wallpaperstack.data.network.paging.WallpapersPagingSource
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.WallpaperInfo
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class WallpapersRepositoryImpl @Inject constructor(
    private val api: WallpaperApi,
) : WallpapersRepository {

    override fun getWallpapersList(sorting: Sorting): Flow<PagingData<WallpaperInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 7,
                maxSize = 24,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                WallpapersPagingSource(api, sorting)
            }
        ).flow
    }
}





