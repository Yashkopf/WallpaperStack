package com.example.wallpaperstack.domain.usecases.getWallpaperList

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.wallpaperstack.data.repository.WallpapersRepository
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.WallpaperInfo
import javax.inject.Inject

class GetWallpaperListUseCaseImpl @Inject constructor(
    private val repository: WallpapersRepository,
) : GetWallpaperListUseCase {

    override fun invoke(sorting: Sorting): LiveData<PagingData<WallpaperInfo>> {
        return repository.getWallpapersList(sorting)
    }
}