package com.example.domain.usecases.getWallpaperList

import androidx.paging.PagingData
import com.example.domain.model.Sorting
import com.example.domain.model.listWallpapers.WallpapersListDetails
import com.example.domain.repository.WallpapersRepository
import kotlinx.coroutines.flow.Flow

internal class GetWallpaperListUseCaseImpl(
    private val repository: WallpapersRepository,
) : GetWallpaperListUseCase {

    override fun invoke(
        sorting: Sorting,
        query: String?,
        purity: String,
        categories: String,
    ): Flow<PagingData<WallpapersListDetails>> {
        return repository.getWallpapersList(sorting, query, purity, categories)
    }
}