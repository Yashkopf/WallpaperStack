package com.example.domain.usecases.getItemsCount

import com.example.domain.repository.WallpapersRepository
import kotlinx.coroutines.flow.Flow

internal class GetItemsCountUseCaseImpl(private val repository: WallpapersRepository) : GetItemsCountUseCase {
    override fun invoke(): Flow<Int?> {
        return repository.itemsCount
    }
}