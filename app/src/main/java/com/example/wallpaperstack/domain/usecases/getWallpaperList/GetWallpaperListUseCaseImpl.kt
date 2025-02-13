package com.example.wallpaperstack.domain.usecases.getWallpaperList

import androidx.paging.PagingData
import com.example.wallpaperstack.data.repository.WallpapersRepository
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.WallpaperInfo
import kotlinx.coroutines.flow.Flow

class GetWallpaperListUseCaseImpl (
    private val repository: WallpapersRepository,
) : GetWallpaperListUseCase {

    override fun invoke(sorting: Sorting): Flow<PagingData<WallpaperInfo>> {
        return repository.getWallpapersList(sorting)
    }
}