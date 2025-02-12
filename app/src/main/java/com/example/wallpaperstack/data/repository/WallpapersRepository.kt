package com.example.wallpaperstack.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.wallpaperstack.data.network.paging.WallpapersPagingSource
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.WallpaperInfo
import com.example.wallpaperstack.domain.model.Wallpapers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface WallpapersRepository {
    fun getWallpapersList(sorting: Sorting): Flow<PagingData<WallpaperInfo>>

}