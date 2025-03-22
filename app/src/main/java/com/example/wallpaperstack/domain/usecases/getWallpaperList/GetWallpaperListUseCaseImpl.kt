package com.example.wallpaperstack.domain.usecases.getWallpaperList

import androidx.paging.PagingData
import com.example.wallpaperstack.data.repository.WallpapersRepository
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.WallpaperInfo
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

class GetWallpaperListUseCaseImpl (
    private val repository: WallpapersRepository,
) : GetWallpaperListUseCase {

    override fun invoke(sorting: Sorting, query: String?): Flow<PagingData<WallpaperInfo>> {
        return repository.getWallpapersList(sorting, query)
    }
}