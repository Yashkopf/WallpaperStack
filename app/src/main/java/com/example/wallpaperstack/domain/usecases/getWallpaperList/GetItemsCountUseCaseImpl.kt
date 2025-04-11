package com.example.wallpaperstack.domain.usecases.getWallpaperList

import com.example.wallpaperstack.data.repository.WallpapersRepository
import kotlinx.coroutines.flow.Flow

class GetItemsCountUseCaseImpl(private val repository: WallpapersRepository) : GetItemsCountUseCase {
    override fun invoke(): Flow<Int?> {
        return repository.itemsCount
    }
}